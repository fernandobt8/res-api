package br.ufsc.bridge.res.service.repository;

import static br.ufsc.bridge.res.service.dto.registry.AdhocQueryResponseXPath.isSuccess;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;

import br.ufsc.bridge.res.http.ResHttpClient;
import br.ufsc.bridge.res.http.exception.ResHttpConnectionException;
import br.ufsc.bridge.res.http.exception.ResHttpRequestResponseException;
import br.ufsc.bridge.res.service.dto.RegistryErrorListXPath;
import br.ufsc.bridge.res.service.dto.header.Credential;
import br.ufsc.bridge.res.service.dto.header.RegistryHeader;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryResponseDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositoryResponseDTO.DocumentItem;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.service.exception.ResServiceFatalException;
import br.ufsc.bridge.res.service.exception.ResServiceSevereException;
import br.ufsc.bridge.res.service.exception.ResXDSbException;
import br.ufsc.bridge.res.service.repository.parser.DocumentParser;
import br.ufsc.bridge.res.service.repository.parser.SubmissionSetParser;
import br.ufsc.bridge.res.util.ResLogError;
import br.ufsc.bridge.res.util.XPathFactoryAssist;

import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType.DocumentRequest;
import oasis.names.tc.ebxml_regrep.xsd.lcm._3.SubmitObjectsRequest;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectListType;

public class RepositoryService {

	private SubmissionSetParser submissionSetParser;
	private DocumentParser documentParser;
	private ResLogError printerResponseError;
	private ResHttpClient httpClientProvide;
	private ResHttpClient httpClientRetrive;

	public RepositoryService(Credential c, String url) throws ResServiceFatalException {
		this.submissionSetParser = new SubmissionSetParser();

		this.documentParser = new DocumentParser();

		this.printerResponseError = new ResLogError();

		this.httpClientProvide = new ResHttpClient(new RegistryHeader(c), "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b");
		try {
			this.httpClientProvide.setUrl(url);
		} catch (MalformedURLException e) {
			throw new ResServiceFatalException("Invalid Repository URL", e);
		}

		this.httpClientRetrive = new ResHttpClient(new RegistryHeader(c), "urn:ihe:iti:2007:RetrieveDocumentSet");
	}

	public RepositoryResponseDTO getDocuments(RepositoryFilter filter) throws ResServiceSevereException, ResServiceFatalException {
		Map<String, RetrieveDocumentSetRequestType> requests = new HashMap<>();

		for (DocumentItemFilter document : filter.getDocuments()) {
			this.addDocumentRequest(requests, document);
		}

		try {
			RepositoryResponseDTO responseDTO = new RepositoryResponseDTO(true);
			for (Entry<String, RetrieveDocumentSetRequestType> entry : requests.entrySet()) {
				this.httpClientRetrive.setUrl(entry.getKey());
				Document response = this.httpClientRetrive.send(entry.getValue());

				XPathFactoryAssist xPathResponse = new XPathFactoryAssist(response);
				if (isSuccess(xPathResponse.getString("//RegistryResponse/@status"))) {
					for (XPathFactoryAssist xPathDocument : xPathResponse.iterable("//Body//DocumentResponse")) {
						DocumentItem documentItem = new DocumentItem();
						documentItem.setRepositoryUniqueId(xPathDocument.getString("./RepositoryUniqueId"));
						documentItem.setDocumentUniqueId(xPathDocument.getString("./DocumentUniqueId"));
						// FIXME: gambiarra para contornar MTOM/XOP
						documentItem.setDocument(new String(Base64.decodeBase64(xPathDocument.getString("./Document")), "ISO-8859-1"));
						responseDTO.getDocuments().add(documentItem);
					}
				} else {
					this.printerResponseError.parserException(new RegistryErrorListXPath(response));
					return null;
				}
			}
			return responseDTO;
		} catch (ResHttpConnectionException e) {
			throw new ResServiceSevereException(e);
		} catch (ResHttpRequestResponseException | ResXDSbException e) {
			throw new ResServiceFatalException(e);
		} catch (MalformedURLException e) {
			throw new ResServiceFatalException("Invalid Repository URL", e);
		} catch (XPathExpressionException e) {
			throw new ResServiceFatalException("Error parsing \"DocumentResponse\"", e);
		} catch (UnsupportedEncodingException e) {
			throw new ResServiceFatalException("Error coverting openEHR document from base64", e);
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

	// FIXME: tirar url do construtor e usar url de dentro do RepositorySaveDocumentDTO
	public void save(RepositorySaveDTO dto) throws ResServiceSevereException, ResServiceFatalException {
		ProvideAndRegisterDocumentSetRequestType provideRegister = new ProvideAndRegisterDocumentSetRequestType();

		SubmitObjectsRequest objectRequest = new SubmitObjectsRequest();
		provideRegister.setSubmitObjectsRequest(objectRequest);

		objectRequest.setRegistryObjectList(new RegistryObjectListType());

		this.submissionSetParser.parser(dto, provideRegister);

		for (RepositorySaveDocumentDTO documentDTO : dto.getDocuments()) {
			this.documentParser.parser(provideRegister, documentDTO);
		}

		Document response;
		try {
			response = this.httpClientProvide.send(provideRegister);
			if (!isSuccess(new XPathFactoryAssist(response).getString("//RegistryResponse/@status"))) {
				this.printerResponseError.parserException(new RegistryErrorListXPath(response));
			}
		} catch (ResHttpConnectionException e) {
			throw new ResServiceSevereException(e);
		} catch (ResHttpRequestResponseException | ResXDSbException e) {
			throw new ResServiceFatalException(e);
		} catch (XPathExpressionException e) {
			throw new ResServiceFatalException("Error parsing \"RegistryResponse\"", e);
		}
	}
}
