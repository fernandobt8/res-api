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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProvideAndRegisterDocumentSetRequestType save() {
		ProvideAndRegisterDocumentSetRequestType provideRegister = new ProvideAndRegisterDocumentSetRequestType();

		SubmitObjectsRequest objectRequest = new SubmitObjectsRequest();
		provideRegister.setSubmitObjectsRequest(objectRequest);

		objectRequest.setRegistryObjectList(new RegistryObjectListType());
		List<JAXBElement<?>> identifiables = (List) objectRequest.getRegistryObjectList().getIdentifiable();

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
				.id("cl2")
				.buildSlot()
					.name("authorPerson").value(SS1)
				.addSlot()
				    .name("authorInstitution").value(SS1)
				.addSlot()
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
				.addSlotEnd()
				.buildInternationalString()
					.value("History and Physical")
				.addInternationalStringEnd()
			.addClassificationEnd()
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:6b5aea1a-874d-4603-a4bc-96a0a7b38446")
				.value("898004405760294^^^&2.16.840.1.113883.13.236&ISO")
				.id("el1")
				.buildInternationalString()
					.value("XDSSubmissionSet.patientId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:554ac39e-e3fe-47fe-b233-965d2a147832")
				.value("1.3.6.1.4.1.21367.2010.1.2")
				.id("el2")
				.buildInternationalString()
					.value("XDSSubmissionSet.sourceId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:96fdda7c-d067-4183-912e-bf5ee74998a8")
				.value("1.42.20130403134532.123.1475256277529.1")
				.id("el3")
				.buildInternationalString()
					.value("XDSSubmissionSet.uniqueId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
		.addRegistryPackageEnd()
		.buildAssociation()
			.id("as1")
			.associationType("urn:oasis:names:tc:ebxml-regrep:AssociationType:HasMember")
			.sourceObject("ss1").targetObject("doc1")
			.buildSlot()
				.name("SubmissionSetStatus").value("Original")
			.addSlotEnd()
		.addAssociationEnd()
		.buildExtrinsicObject()
			.id("doc1")
			.mimeType("text/xml")
			.objectType("urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1")
			.buildSlot()
				.name("creationTime").value(SS1)
			.addSlotEnd()
			.buildSlot()
				.name("languageCode").value(SS1)
			.addSlotEnd()
			.buildSlot()
				.name("serviceStartTime").value(SS1)
			.addSlotEnd()
			.buildSlot()
				.name("serviceStopTime").value(SS1)
			.addSlotEnd()
			.buildSlot()
				.name("sourcePatientId").value(SS1)
			.addSlotEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d")
				.id("cl4")
				.buildSlot()
					.name("authorPerson").value(SS1)
				.addSlot()
				    .name("authorInstitution").value(SS1)
				.addSlot()
				    .name("authorSpecialty").value(SS1)
				.addSlotEnd()
			.addClassificationEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a")
				.id("cl5")
				.nodeRepresentation("History and Physical")
				.buildSlot()
					.name("codingScheme")
					.value("Connect-a-thon classCodes")
				.addSlotEnd()
				.buildInternationalString()
					.value("History and Physical")
				.addInternationalStringEnd()
			.addClassificationEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f")
				.id("cl6")
				.nodeRepresentation("1.3.6.1.4.1.21367.2006.7.101")
				.buildSlot()
					.name("codingScheme")
					.value("Connect-a-thon confidentialityCodes")
				.addSlotEnd()
				.buildInternationalString()
					.value("Clinical-Staff")
				.addInternationalStringEnd()
			.addClassificationEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d")
				.id("cl7")
				.nodeRepresentation("tdd")
				.buildSlot()
					.name("codingScheme")
					.value("Connect-a-thon formatCodes")
				.addSlotEnd()
				.buildInternationalString()
					.value("tdd")
				.addInternationalStringEnd()
			.addClassificationEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1")
				.id("cl8")
				.nodeRepresentation("Outpatient")
				.buildSlot()
					.name("codingScheme")
					.value("Connect-a-thon healthcareFacilityTypeCodes")
				.addSlotEnd()
				.buildInternationalString()
					.value("Outpatient")
				.addInternationalStringEnd()
			.addClassificationEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead")
				.id("cl9")
				.nodeRepresentation("General Medicine")
				.buildSlot()
					.name("codingScheme")
					.value("Connect-a-thon practiceSettingCodes")
				.addSlotEnd()
				.buildInternationalString()
					.value("General Medicine")
				.addInternationalStringEnd()
			.addClassificationEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:f0306f51-975f-434e-a61c-c59651d33983")
				.id("cl10")
				.nodeRepresentation("eRCO_Vaccination_Record")
				.buildSlot()
					.name("codingScheme")
					.value("LOINC")
				.addSlotEnd()
				.buildInternationalString()
					.value("eRCO_Vaccination_Record")
				.addInternationalStringEnd()
			.addClassificationEnd()
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:58a6f841-87b3-4a3e-92fd-a8ffeff98427")
				.value("898004405760294^^^&amp;2.16.840.1.113883.13.236&amp;ISO")
				.id("ei4")
				.buildInternationalString()
					.value("XDSDocumentEntry.patientId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab")
				.value("1.42.20130403134532.123.1475256277528.2")
				.id("ei5")
				.buildInternationalString()
					.value("XDSDocumentEntry.uniqueId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
		.addExtrinsicObjectEnd();




		//@formatter:on
		Document document = new Document();
		document.setId("doc1");
		document.setValue("aa".getBytes());

		provideRegister.getDocument().add(document);

		RegistryResponseType registryResponseType = this.portSoap12.documentRepositoryProvideAndRegisterDocumentSetB(provideRegister);

		return provideRegister;
	}
}
