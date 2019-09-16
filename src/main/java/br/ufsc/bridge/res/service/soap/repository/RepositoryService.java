package br.ufsc.bridge.res.service.soap.repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

import br.ufsc.bridge.res.http.ResSoapHttpClient;
import br.ufsc.bridge.res.http.ResSoapHttpHeaders;
import br.ufsc.bridge.res.service.soap.ResSoapMessageBuilder;
import br.ufsc.bridge.res.service.soap.exception.ResConsentPolicyException;
import br.ufsc.bridge.res.service.soap.exception.ResException;
import br.ufsc.bridge.res.service.soap.exception.ResHttpConnectionException;
import br.ufsc.bridge.res.service.soap.exception.ResServerErrorException;
import br.ufsc.bridge.res.service.soap.exception.ResServiceFatalException;
import br.ufsc.bridge.res.service.soap.exception.ResUrlsException;
import br.ufsc.bridge.res.service.soap.exception.ResXDSbException;
import br.ufsc.bridge.res.service.soap.registry.RegistryErrorListXPath;
import br.ufsc.bridge.res.service.soap.registry.dto.AdhocQueryResponseXPath;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositoryDocumentItem;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositoryFilter;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositorySaveDTO;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.service.soap.repository.parser.DocumentParser;
import br.ufsc.bridge.res.service.soap.repository.parser.SubmissionSetParser;
import br.ufsc.bridge.res.util.ResLogError;
import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.SoapHttpRequest;
import br.ufsc.bridge.soap.http.SoapHttpResponse;
import br.ufsc.bridge.soap.http.exception.SoapCreateMessageException;
import br.ufsc.bridge.soap.http.exception.SoapHttpConnectionException;
import br.ufsc.bridge.soap.http.exception.SoapHttpResponseException;
import br.ufsc.bridge.soap.http.exception.SoapReadMessageException;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType.DocumentRequest;
import oasis.names.tc.ebxml_regrep.xsd.lcm._3.SubmitObjectsRequest;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectListType;

public class RepositoryService {

	private static final String PROVIDE_ACTION = "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b";
	private static final String RETRIEVE_ACTION = "urn:ihe:iti:2007:RetrieveDocumentSet";

	private SubmissionSetParser submissionSetParser;
	private DocumentParser documentParser;
	private ResLogError printerResponseError;
	private SoapCredential c;

	public RepositoryService(SoapCredential c) {
		this.c = c;
		this.submissionSetParser = new SubmissionSetParser();
		this.documentParser = new DocumentParser();
		this.printerResponseError = new ResLogError();
	}

	public List<RepositoryDocumentItem> getDocuments(RepositoryFilter filter)
			throws ResHttpConnectionException, ResServiceFatalException, ResServerErrorException, ResConsentPolicyException {
		Map<String, RetrieveDocumentSetRequestType> requests = new HashMap<>();

		for (DocumentItemFilter document : filter.getDocuments()) {
			this.addDocumentRequest(requests, document);
		}

		try {
			List<RepositoryDocumentItem> responseDtos = new ArrayList<>();
			for (Entry<String, RetrieveDocumentSetRequestType> entry : requests.entrySet()) {
				byte[] message = new ResSoapMessageBuilder(this.c, entry.getKey(), RETRIEVE_ACTION).createMessage(entry.getValue());
				SoapHttpRequest request = new SoapHttpRequest(entry.getKey(), RETRIEVE_ACTION, "soapId", message)
						.addHeader(ResSoapHttpHeaders.CNS_PROFISSIONAL, filter.getCnsProfissional())
						.addHeader(ResSoapHttpHeaders.CBO, filter.getCboProfissional())
						.addHeader(ResSoapHttpHeaders.CNES, filter.getCnesProfissional());

				SoapHttpResponse response = ResSoapHttpClient.request(request);

				Document soap = response.getSoap();
				XPathFactoryAssist xPathResponse = new XPathFactoryAssist(soap);
				if (AdhocQueryResponseXPath.isSuccess(xPathResponse.getString("//RegistryResponse/@status"))) {
					for (XPathFactoryAssist xPathDocument : xPathResponse.iterable("//Body//DocumentResponse")) {
						RepositoryDocumentItem documentItem = new RepositoryDocumentItem();
						documentItem.setRepositoryUniqueId(xPathDocument.getString("./RepositoryUniqueId"));
						documentItem.setDocumentUniqueId(xPathDocument.getString("./DocumentUniqueId"));
						documentItem.setDocument(IOUtils.toString(response.getPart(xPathDocument, "./Document"), "UTF-8"));
						responseDtos.add(documentItem);
					}
				} else {
					this.printerResponseError.parserException(new RegistryErrorListXPath(soap));
					return null;
				}
			}
			return responseDtos;
		} catch (SoapHttpConnectionException e) {
			throw new ResHttpConnectionException(e);
		} catch (SoapCreateMessageException | SoapHttpResponseException | SoapReadMessageException | ResXDSbException | IOException e) {
			throw new ResServiceFatalException(e);
		} catch (XPathExpressionException e) {
			throw new ResServiceFatalException("Error parsing \"DocumentResponse\"", e);
		}
	}

	private void addDocumentRequest(Map<String, RetrieveDocumentSetRequestType> requests, DocumentItemFilter document) {
		RetrieveDocumentSetRequestType requestType = requests.get(document.getRepositoryURL());
		if (requestType == null) {
			requestType = new RetrieveDocumentSetRequestType();
			requests.put(document.getRepositoryURL(), requestType);
		}
		DocumentRequest documentRequest = new RetrieveDocumentSetRequestType.DocumentRequest();
		documentRequest.setRepositoryUniqueId(document.getRepositoryUniqueId());
		documentRequest.setDocumentUniqueId(document.getDocumentUniqueId());
		requestType.getDocumentRequest().add(documentRequest);
	}

	public void save(RepositorySaveDTO dto) throws ResException {
		Map<String, List<RepositorySaveDocumentDTO>> urlDocs = new HashMap<>();
		for (RepositorySaveDocumentDTO documentDTO : dto.getDocuments()) {
			if (!urlDocs.containsKey(documentDTO.getUrl())) {
				urlDocs.put(documentDTO.getUrl(), new ArrayList<RepositorySaveDocumentDTO>());
			}
			urlDocs.get(documentDTO.getUrl()).add(documentDTO);
		}

		Map<String, ResException> urlErrors = new HashMap<>(urlDocs.size());
		for (Entry<String, List<RepositorySaveDocumentDTO>> entryUrldocs : urlDocs.entrySet()) {
			ProvideAndRegisterDocumentSetRequestType provideRegister = new ProvideAndRegisterDocumentSetRequestType();
			provideRegister.setSubmitObjectsRequest(new SubmitObjectsRequest());
			provideRegister.getSubmitObjectsRequest().setRegistryObjectList(new RegistryObjectListType());
			this.submissionSetParser.parser(dto, provideRegister);

			Map<String, byte[]> isDocuments = new HashMap<>();
			try {
				for (RepositorySaveDocumentDTO documentDTO : entryUrldocs.getValue()) {
					this.documentParser.parser(provideRegister, documentDTO);
					isDocuments.put(documentDTO.getDocumentId(), documentDTO.getDocument().getBytes("UTF-8"));
				}

				byte[] message = new ResSoapMessageBuilder(this.c, entryUrldocs.getKey(), PROVIDE_ACTION).createMessage(provideRegister);
				SoapHttpRequest request = new SoapHttpRequest(entryUrldocs.getKey(), PROVIDE_ACTION, "soapId", message, isDocuments)
						.addHeader(ResSoapHttpHeaders.CNS_PROFISSIONAL, dto.getCnsProfissional())
						.addHeader(ResSoapHttpHeaders.CBO, dto.getCboProfissional())
						.addHeader(ResSoapHttpHeaders.CNES, dto.getCnesUnidadeSaude());
				SoapHttpResponse response = ResSoapHttpClient.request(request);

				Document soap = response.getSoap();
				if (!AdhocQueryResponseXPath.isSuccess(new XPathFactoryAssist(soap).getString("//RegistryResponse/@status"))) {
					this.printerResponseError.parserException(new RegistryErrorListXPath(soap));
				}
			} catch (SoapHttpConnectionException e) {
				urlErrors.put(entryUrldocs.getKey(), new ResHttpConnectionException(e));
			} catch (SoapCreateMessageException | SoapHttpResponseException | SoapReadMessageException | ResXDSbException | UnsupportedEncodingException e) {
				urlErrors.put(entryUrldocs.getKey(), new ResServiceFatalException(e));
			} catch (XPathExpressionException e) {
				urlErrors.put(entryUrldocs.getKey(), new ResServiceFatalException("Error parsing \"RegistryResponse\"", e));
			} catch (ResConsentPolicyException | ResServerErrorException | ResServiceFatalException e) {
				urlErrors.put(entryUrldocs.getKey(), e);
			}
		}
		if (urlErrors.size() == 1) {
			throw urlErrors.values().iterator().next();
		} else if (urlErrors.size() > 1) {
			throw new ResUrlsException(urlErrors);
		}
	}
}
