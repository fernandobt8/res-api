package br.ufsc.bridge.res.service.repository;

import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.service.dto.header.Credential;
import br.ufsc.bridge.res.service.dto.header.RepositoryHeader;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryResponseDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositoryResponseDTO.DocumentItem;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.service.repository.parser.DocumentParser;
import br.ufsc.bridge.res.service.repository.parser.SubmissionSetParser;
import br.ufsc.bridge.res.util.ResLogError;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.DocumentRepositoryService;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType.DocumentRequest;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType.DocumentResponse;
import oasis.names.tc.ebxml_regrep.xsd.lcm._3.SubmitObjectsRequest;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectListType;
import oasis.names.tc.ebxml_regrep.xsd.rs._3.RegistryResponseType;

@Slf4j
public class RepositoryService {

	private static final String SUCCESS = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success";

	private DocumentRepositoryPortType portSoap12;
	private SubmissionSetParser submissionSetParser;
	private DocumentParser documentParser;
	private ResLogError printerResponseError;

	public RepositoryService(Credential c) {
		this.submissionSetParser = new SubmissionSetParser();

		this.documentParser = new DocumentParser();

		this.printerResponseError = new ResLogError();

		DocumentRepositoryService repositoryService = new DocumentRepositoryService();
		repositoryService.setHandlerResolver(new RepositoryHeader(c));

		this.portSoap12 = repositoryService.getDocumentRepositoryPortSoap12();
	}

	public RepositoryResponseDTO getDocuments(RepositoryFilter filter) {
		RetrieveDocumentSetRequestType retrieveDocumentRequest = new RetrieveDocumentSetRequestType();

		for (DocumentItemFilter document : filter.getDocuments()) {
			DocumentRequest documentRequest = new RetrieveDocumentSetRequestType.DocumentRequest();
			documentRequest.setRepositoryUniqueId(document.getRepositoryUniqueId());
			documentRequest.setDocumentUniqueId(document.getDocumentUniqueId());
			retrieveDocumentRequest.getDocumentRequest().add(documentRequest);
		}

		RetrieveDocumentSetResponseType response = null;
		try {
			response = this.portSoap12.documentRepositoryRetrieveDocumentSet(retrieveDocumentRequest);
		} catch (Exception e) {
			log.error("erro no request", e);
			return new RepositoryResponseDTO(false);
		}

		if (response.getRegistryResponse().getStatus().equals(SUCCESS)) {
			RepositoryResponseDTO responseDTO = new RepositoryResponseDTO(true);
			for (DocumentResponse documentResponse : response.getDocumentResponse()) {
				DocumentItem documentItem = new DocumentItem();
				documentItem.setRepositoryUniqueId(documentResponse.getRepositoryUniqueId());
				documentItem.setDocucumentUniqueId(documentResponse.getDocumentUniqueId());
				try {
					documentItem.setDocument(new String(documentResponse.getDocument(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					log.error("UTF-8 unsupported", e);
				}
				responseDTO.getDocuments().add(documentItem);
			}
			return responseDTO;
		} else {
			this.printerResponseError.printLogError(response.getRegistryResponse().getRegistryErrorList());
			return new RepositoryResponseDTO(false);
		}
	}

	public boolean save(RepositorySaveDTO dto) {
		ProvideAndRegisterDocumentSetRequestType provideRegister = new ProvideAndRegisterDocumentSetRequestType();

		SubmitObjectsRequest objectRequest = new SubmitObjectsRequest();
		provideRegister.setSubmitObjectsRequest(objectRequest);

		objectRequest.setRegistryObjectList(new RegistryObjectListType());

		this.submissionSetParser.parser(dto, provideRegister);

		for (RepositorySaveDocumentDTO documentDTO : dto.getDocuments()) {
			this.documentParser.parser(provideRegister, documentDTO);
		}

		RegistryResponseType response = null;
		try {
			response = this.portSoap12.documentRepositoryProvideAndRegisterDocumentSetB(provideRegister);
		} catch (Exception e) {
			log.error("erro no request", e);
			return false;
		}

		if (response.getStatus().equals(SUCCESS)) {
			return true;
		} else {
			this.printerResponseError.printLogError(response.getRegistryErrorList());
			return false;
		}
	}
}
