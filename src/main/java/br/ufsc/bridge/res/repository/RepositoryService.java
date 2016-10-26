package br.ufsc.bridge.res.repository;

import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.dto.header.Credential;
import br.ufsc.bridge.res.dto.header.RepositoryHeader;
import br.ufsc.bridge.res.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.dto.repository.RepositoryResponseDTO;
import br.ufsc.bridge.res.dto.repository.RepositoryResponseDTO.DocumentItem;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.DocumentRepositoryService;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetRequestType.DocumentRequest;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType.DocumentResponse;

@Slf4j
public class RepositoryService {

	private static final String SUCCESS = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success";

	private DocumentRepositoryPortType portSoap12;

	public RepositoryService(Credential c) {
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

		RetrieveDocumentSetResponseType response = this.portSoap12.documentRepositoryRetrieveDocumentSet(retrieveDocumentRequest);

		RepositoryResponseDTO responseDTO = new RepositoryResponseDTO();

		if (response.getRegistryResponse().getStatus().equals(SUCCESS)) {
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
		} else {
			log.error("erro no request");
		}

		return responseDTO;
	}
}
