package br.ufsc.bridge.res.service.repository;

import static br.ufsc.bridge.res.http.ResSoapHttpClientSingleton.resHttpClient;
import static br.ufsc.bridge.res.service.dto.registry.AdhocQueryResponseXPath.isSuccess;

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

import br.ufsc.bridge.res.http.ResSoapHttpHeaders;
import br.ufsc.bridge.res.service.ResSoapMessageBuilder;
import br.ufsc.bridge.res.service.dto.RegistryErrorListXPath;
import br.ufsc.bridge.res.service.dto.repository.RepositoryDocumentItem;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.service.exception.ResConsentPolicyException;
import br.ufsc.bridge.res.service.exception.ResException;
import br.ufsc.bridge.res.service.exception.ResHttpConnectionException;
import br.ufsc.bridge.res.service.exception.ResServerErrorException;
import br.ufsc.bridge.res.service.exception.ResServiceFatalException;
import br.ufsc.bridge.res.service.exception.ResUrlsException;
import br.ufsc.bridge.res.service.exception.ResXDSbException;
import br.ufsc.bridge.res.service.repository.parser.DocumentParser;
import br.ufsc.bridge.res.service.repository.parser.SubmissionSetParser;
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

	private final String provideAction = "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b";
	private final String retriveAction = "urn:ihe:iti:2007:RetrieveDocumentSet";

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
				byte[] message = new ResSoapMessageBuilder(this.c, entry.getKey(), this.retriveAction).createMessage(entry.getValue());
				SoapHttpRequest request = new SoapHttpRequest(entry.getKey(), this.retriveAction, "soapId", message)
						.addHeader(ResSoapHttpHeaders.CNS_PROFISSIONAL, filter.getCnsProfissional())
						.addHeader(ResSoapHttpHeaders.CBO, filter.getCboProfissional())
						.addHeader(ResSoapHttpHeaders.CNES, filter.getCnesProfissional());

				SoapHttpResponse response = resHttpClient().request(request);

				Document soap = response.getSoap();
				XPathFactoryAssist xPathResponse = new XPathFactoryAssist(soap);
				if (isSuccess(xPathResponse.getString("//RegistryResponse/@status"))) {
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

				byte[] message = new ResSoapMessageBuilder(this.c, entryUrldocs.getKey(), this.provideAction).createMessage(provideRegister);
				SoapHttpRequest request = new SoapHttpRequest(entryUrldocs.getKey(), this.provideAction, "soapId", message, isDocuments)
						.addHeader(ResSoapHttpHeaders.CNS_PROFISSIONAL, dto.getCnsProfissional())
						.addHeader(ResSoapHttpHeaders.CBO, dto.getCboProfissional())
						.addHeader(ResSoapHttpHeaders.CNES, dto.getCnesUnidadeSaude());
				SoapHttpResponse response = resHttpClient().request(request);

				Document soap = response.getSoap();
				if (!isSuccess(new XPathFactoryAssist(soap).getString("//RegistryResponse/@status"))) {
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
