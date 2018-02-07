package br.ufsc.bridge.res.service.repository;

import static br.ufsc.bridge.res.http.ResSoapHttpClientSingleton.resHttpClient;
import static br.ufsc.bridge.res.service.dto.registry.AdhocQueryResponseXPath.isSuccess;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;

import br.ufsc.bridge.res.service.dto.RegistryErrorListXPath;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryResponseDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositoryResponseDTO.DocumentItem;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveResponseDTO;
import br.ufsc.bridge.res.service.exception.ResServiceFatalException;
import br.ufsc.bridge.res.service.exception.ResServiceSevereException;
import br.ufsc.bridge.res.service.exception.ResXDSbException;
import br.ufsc.bridge.res.service.repository.parser.DocumentParser;
import br.ufsc.bridge.res.service.repository.parser.SubmissionSetParser;
import br.ufsc.bridge.res.util.ResLogError;
import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.SoapHttpResponse;
import br.ufsc.bridge.soap.http.SoapMessageBuilder;
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

	private SoapMessageBuilder soapMessageSender;

	public RepositoryService(SoapCredential c) throws ResServiceFatalException {
		this.submissionSetParser = new SubmissionSetParser();

		this.documentParser = new DocumentParser();

		this.printerResponseError = new ResLogError();

		this.soapMessageSender = new SoapMessageBuilder(c, resHttpClient());
	}

	public RepositoryResponseDTO getDocuments(RepositoryFilter filter) throws ResServiceSevereException, ResServiceFatalException {
		Map<String, RetrieveDocumentSetRequestType> requests = new HashMap<>();

		for (DocumentItemFilter document : filter.getDocuments()) {
			this.addDocumentRequest(requests, document);
		}

		try {
			RepositoryResponseDTO responseDTO = new RepositoryResponseDTO(true);
			for (Entry<String, RetrieveDocumentSetRequestType> entry : requests.entrySet()) {
				SoapHttpResponse response = this.soapMessageSender.sendMessage(entry.getKey(), this.retriveAction, entry.getValue());

				Document soap = response.getSoap();
				XPathFactoryAssist xPathResponse = new XPathFactoryAssist(soap);
				if (isSuccess(xPathResponse.getString("//RegistryResponse/@status"))) {
					for (XPathFactoryAssist xPathDocument : xPathResponse.iterable("//Body//DocumentResponse")) {
						DocumentItem documentItem = new DocumentItem();
						documentItem.setRepositoryUniqueId(xPathDocument.getString("./RepositoryUniqueId"));
						documentItem.setDocumentUniqueId(xPathDocument.getString("./DocumentUniqueId"));
						documentItem.setDocument(IOUtils.toString(response.getPart(xPathDocument, "./Document"), "UTF-8"));
						responseDTO.getDocuments().add(documentItem);
					}
				} else {
					this.printerResponseError.parserException(new RegistryErrorListXPath(soap));
					return null;
				}
			}
			return responseDTO;
		} catch (SoapHttpConnectionException e) {
			throw new ResServiceSevereException(e);
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

	public List<RepositorySaveResponseDTO> save(RepositorySaveDTO dto) {
		Map<String, List<RepositorySaveDocumentDTO>> urlDocs = new HashMap<>();
		for (RepositorySaveDocumentDTO documentDTO : dto.getDocuments()) {
			if (!urlDocs.containsKey(documentDTO.getUrl())) {
				urlDocs.put(documentDTO.getUrl(), new ArrayList<RepositorySaveDocumentDTO>());
			}
			urlDocs.get(documentDTO.getUrl()).add(documentDTO);
		}

		List<RepositorySaveResponseDTO> saveResponseDto = new ArrayList<>();
		for (Entry<String, List<RepositorySaveDocumentDTO>> entryUrldocs : urlDocs.entrySet()) {
			ProvideAndRegisterDocumentSetRequestType provideRegister = new ProvideAndRegisterDocumentSetRequestType();
			provideRegister.setSubmitObjectsRequest(new SubmitObjectsRequest());
			provideRegister.getSubmitObjectsRequest().setRegistryObjectList(new RegistryObjectListType());
			this.submissionSetParser.parser(dto, provideRegister);

			Map<String, InputStream> isDocuments = new HashMap<>();
			try {
				for (RepositorySaveDocumentDTO documentDTO : entryUrldocs.getValue()) {
					this.documentParser.parser(provideRegister, documentDTO);
					isDocuments.put(documentDTO.getDocumentId(), new ByteArrayInputStream(documentDTO.getDocument().getBytes("UTF-8")));
				}

				SoapHttpResponse response = this.soapMessageSender.sendMessage(entryUrldocs.getKey(), this.provideAction, provideRegister, isDocuments);

				Document soap = response.getSoap();
				if (isSuccess(new XPathFactoryAssist(soap).getString("//RegistryResponse/@status"))) {
					saveResponseDto.add(new RepositorySaveResponseDTO(entryUrldocs.getKey()));
					saveResponseDto.add(new RepositorySaveResponseDTO(entryUrldocs.getKey()));
				} else {
					this.printerResponseError.parserException(new RegistryErrorListXPath(soap));
				}
			} catch (SoapHttpConnectionException e) {
				saveResponseDto.add(new RepositorySaveResponseDTO(entryUrldocs.getKey(), false, new ResServiceSevereException(e)));
			} catch (SoapCreateMessageException | SoapHttpResponseException | SoapReadMessageException | ResXDSbException | UnsupportedEncodingException e) {
				saveResponseDto.add(new RepositorySaveResponseDTO(entryUrldocs.getKey(), false, new ResServiceFatalException(e)));
			} catch (XPathExpressionException e) {
				saveResponseDto.add(new RepositorySaveResponseDTO(entryUrldocs.getKey(), false, new ResServiceFatalException("Error parsing \"RegistryResponse\"", e)));
			} catch (ResServiceSevereException e) {
				saveResponseDto.add(new RepositorySaveResponseDTO(entryUrldocs.getKey(), false, e));
			}
		}
		return saveResponseDto;
	}
}
