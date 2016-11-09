package br.ufsc.bridge.res.repository;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.builder.IdentifiableTypeBuilder;
import br.ufsc.bridge.res.dto.header.Credential;
import br.ufsc.bridge.res.dto.header.RepositoryHeader;
import br.ufsc.bridge.res.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.dto.repository.RepositoryResponseDTO;
import br.ufsc.bridge.res.dto.repository.RepositoryResponseDTO.DocumentItem;

import ihe.iti.xds_b._2007.DocumentRepositoryPortType;
import ihe.iti.xds_b._2007.DocumentRepositoryService;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType.Document;
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
	private static final String SS1 = "ss1";

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

	public ProvideAndRegisterDocumentSetRequestType save() {
		ProvideAndRegisterDocumentSetRequestType provideRegister = new ProvideAndRegisterDocumentSetRequestType();

		SubmitObjectsRequest objectRequest = new SubmitObjectsRequest();
		provideRegister.setSubmitObjectsRequest(objectRequest);

		objectRequest.setRegistryObjectList(new RegistryObjectListType());
		List<JAXBElement> identifiables = (List) objectRequest.getRegistryObjectList().getIdentifiable();

		//@formatter:off
		new IdentifiableTypeBuilder(identifiables)
		.buildRegistryPackage()
			.buildClassification()
				.id("cl1")
				.classificationNode("urn:uuid:a54d6aa5-d40d-43f9-88c5-b4633d873bdd")
			.addClassificationEnd()
			.buildSlot()
				.name("submissionTime")
				.value("20161004110000")
			.addSlotEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:a7058bb9-b4e4-4307-ba5b-e3f0ab85e12d")
				.classifiedObject(SS1)
				.id("cl2")
				.buildSlot()
					.name("authorPerson").value(SS1)
				.add()
				    .name("authorInstitution").value(SS1)
				.add()
				    .name("authorSpecialty").value(SS1)
				.addSlotEnd()
			.addClassificationEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:aa543740-bdda-424e-8c96-df4873be8500")
				.id("cl3")
				.nodeRepresentation("History and Physical")
				.buildSlot()
					.name("codingScheme")
					.value("Connect-a-thon contentTypeCodes")
				.addSlotEnd() //Name
			.addClassificationEnd();




		//@formatter:on
		Document document = new Document();
		document.setId("doc1");
		document.setValue("aa".getBytes());

		provideRegister.getDocument().add(document);

		RegistryResponseType registryResponseType = this.portSoap12.documentRepositoryProvideAndRegisterDocumentSetB(provideRegister);

		return provideRegister;
	}
}
