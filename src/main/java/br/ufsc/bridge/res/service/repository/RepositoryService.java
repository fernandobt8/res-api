package br.ufsc.bridge.res.service.repository;

import static br.ufsc.bridge.res.service.dto.registry.AdhocQueryResponseXPath.isSuccess;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;

import br.ufsc.bridge.res.http.ResHttpClient;
import br.ufsc.bridge.res.http.exception.ResHttpConnectionException;
import br.ufsc.bridge.res.http.exception.ResHttpRequestResponseException;
import br.ufsc.bridge.res.service.dto.RegistryErrorListXPath;
import br.ufsc.bridge.res.service.dto.header.Credential;
import br.ufsc.bridge.res.service.dto.header.RepositoryHeader;
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

	public RepositoryService(Credential c) throws ResServiceFatalException {
		this.submissionSetParser = new SubmissionSetParser();

		this.documentParser = new DocumentParser();

		this.printerResponseError = new ResLogError();

		this.httpClientProvide = new ResHttpClient(new RepositoryHeader(c), "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b");
		try {
			this.httpClientProvide.setUrl("https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RepositoryPS");
		} catch (MalformedURLException e) {
			throw new ResServiceFatalException("Invalid Repository URL", e);
		}

		this.httpClientRetrive = new ResHttpClient(new RepositoryHeader(c), "urn:ihe:iti:2007:RetrieveDocumentSet");
	}

	public RepositoryResponseDTO getDocuments(RepositoryFilter filter) throws ResServiceSevereException, ResServiceFatalException {
		RetrieveDocumentSetRequestType retrieveDocumentRequest = new RetrieveDocumentSetRequestType();

		for (DocumentItemFilter document : filter.getDocuments()) {
			DocumentRequest documentRequest = new RetrieveDocumentSetRequestType.DocumentRequest();
			documentRequest.setRepositoryUniqueId(document.getRepositoryUniqueId());
			documentRequest.setDocumentUniqueId(document.getDocumentUniqueId());
			retrieveDocumentRequest.getDocumentRequest().add(documentRequest);
		}

		try {
			this.httpClientRetrive.setUrl(filter.getRepositoryURL());
			Document response = this.httpClientRetrive.send(retrieveDocumentRequest);

			XPathFactoryAssist xPathResponse = new XPathFactoryAssist(response);
			if (isSuccess(xPathResponse.getString("//RegistryResponse/@status"))) {
				RepositoryResponseDTO responseDTO = new RepositoryResponseDTO(true);
				for (XPathFactoryAssist xPathDocument : xPathResponse.iterable("//Body//DocumentResponse")) {
					DocumentItem documentItem = new DocumentItem();
					documentItem.setRepositoryUniqueId(xPathDocument.getString("./RepositoryUniqueId"));
					documentItem.setDocumentUniqueId(xPathDocument.getString("./DocumentUniqueId"));
					documentItem.setDocument(new String(Base64.decodeBase64(xPathDocument.getString("./Document")), "UTF-8"));
					responseDTO.getDocuments().add(documentItem);
				}
				return responseDTO;
			} else {
				this.printerResponseError.parserException(new RegistryErrorListXPath(response));
				return null;
			}
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
