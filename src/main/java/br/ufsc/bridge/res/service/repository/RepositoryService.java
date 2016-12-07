package br.ufsc.bridge.res.service.repository;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.dab.exception.ResABXMLParserException;
import br.ufsc.bridge.res.dab.exception.ResABXMLWriterException;
import br.ufsc.bridge.res.http.ResHttpClient;
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
	public static final String SERVICO_RES_INDISPONIVEL = "Serviço RES-nacional não disponínel. Tente novamente.";

	private SubmissionSetParser submissionSetParser;
	private DocumentParser documentParser;
	private ResLogError printerResponseError;
	private ResHttpClient httpClientProvide;
	private ResHttpClient httpClientRetrive;

	public RepositoryService(Credential c) {
		this.submissionSetParser = new SubmissionSetParser();

		this.documentParser = new DocumentParser();

		this.printerResponseError = new ResLogError();

		this.httpClientProvide = new ResHttpClient(new RepositoryHeader(c), "urn:ihe:iti:2007:ProvideAndRegisterDocumentSet-b");
		try {
			this.httpClientProvide.setUrl("https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RepositoryPS");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		this.httpClientRetrive = new ResHttpClient(new RepositoryHeader(c), "urn:ihe:iti:2007:RetrieveDocumentSet");

	}

	public RepositoryResponseDTO getDocuments(RepositoryFilter filter) throws ResABXMLParserException {
		RetrieveDocumentSetRequestType retrieveDocumentRequest = new RetrieveDocumentSetRequestType();

		for (DocumentItemFilter document : filter.getDocuments()) {
			DocumentRequest documentRequest = new RetrieveDocumentSetRequestType.DocumentRequest();
			documentRequest.setRepositoryUniqueId(document.getRepositoryUniqueId());
			documentRequest.setDocumentUniqueId(document.getDocumentUniqueId());
			retrieveDocumentRequest.getDocumentRequest().add(documentRequest);
		}

		RetrieveDocumentSetResponseType response = null;
		try {
			this.httpClientRetrive.setUrl(filter.getRepositoryURL());
			response = this.httpClientRetrive.send(retrieveDocumentRequest, RetrieveDocumentSetResponseType.class);
		} catch (Exception e) {
			throw new ResABXMLParserException(SERVICO_RES_INDISPONIVEL, e);
		}

		if (response.getRegistryResponse().getStatus().equals(SUCCESS)) {
			RepositoryResponseDTO responseDTO = new RepositoryResponseDTO(true);
			for (DocumentResponse documentResponse : response.getDocumentResponse()) {
				DocumentItem documentItem = new DocumentItem();
				documentItem.setRepositoryUniqueId(documentResponse.getRepositoryUniqueId());
				documentItem.setDocumentUniqueId(documentResponse.getDocumentUniqueId());
				try {
					documentItem.setDocument(new String(documentResponse.getDocument(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					throw new ResABXMLParserException(e);
				}
				responseDTO.getDocuments().add(documentItem);
			}
			return responseDTO;
		} else {
			this.printerResponseError.printLogError(response.getRegistryResponse().getRegistryErrorList());
			throw new ResABXMLParserException(response.getRegistryResponse().getRegistryErrorList());
		}
	}

	public void save(RepositorySaveDTO dto) throws ResABXMLWriterException {
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
			response = this.httpClientProvide.send(provideRegister, RegistryResponseType.class);
		} catch (Exception e) {
			throw new ResABXMLWriterException(SERVICO_RES_INDISPONIVEL);
		}

		if (!response.getStatus().equals(SUCCESS)) {
			this.printerResponseError.printLogError(response.getRegistryErrorList());
			throw new ResABXMLWriterException(response.getRegistryErrorList());
		}
	}
}
