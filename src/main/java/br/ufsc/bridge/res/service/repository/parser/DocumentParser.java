package br.ufsc.bridge.res.service.repository.parser;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.bind.JAXBElement;

import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.service.builder.IdentifiableTypeBuilder;
import br.ufsc.bridge.res.service.dto.repository.RepositoryRegisterDocumentDTO;
import br.ufsc.bridge.res.util.RDateUtil;

import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType.Document;

@Slf4j
public class DocumentParser {

	private AtomicLong atomicLong;

	public DocumentParser() {
		this.atomicLong = new AtomicLong();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void parser(ProvideAndRegisterDocumentSetRequestType provideRegister, RepositoryRegisterDocumentDTO dto) {
		String docId = "doc" + this.atomicLong.incrementAndGet();

		Document document = new Document();
		document.setId(docId);
		try {
			document.setValue(dto.getDocument().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("UTF-8 não suportado", e);
			return;
		}
		provideRegister.getDocument().add(document);

		List<JAXBElement<?>> identifiables = (List) provideRegister.getSubmitObjectsRequest().getRegistryObjectList().getIdentifiable();

		//@formatter:off
		new IdentifiableTypeBuilder(identifiables)
		.buildAssociation()
			.id("as" + this.atomicLong.incrementAndGet())
			.associationType("urn:oasis:names:tc:ebxml-regrep:AssociationType:HasMember")
			.sourceObject(SubmissionSetParser.SUBMISSIONSET_ID).targetObject(docId)
			.buildSlot()
				.name("SubmissionSetStatus").value("Original")
			.addSlotEnd()
		.addAssociationEnd()
		.buildExtrinsicObject()
			.id(docId)
			.mimeType("text/xml")
			.objectType("urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1")
			.buildSlot()
				.name("creationTime").value(RDateUtil.dateToISOXDSb(new Date()))
			.addSlotEnd()
			.buildSlot()
				.name("languageCode").value("pt-BR")
			.addSlotEnd()
			.buildSlot()
				.name("serviceStartTime").value(RDateUtil.dateToISOXDSb(dto.getDataInicioAtendimento()))
			.addSlotEnd()
			.buildSlot()
				.name("serviceStopTime").value(RDateUtil.dateToISOXDSb(dto.getDataFimAtendimento()))
			.addSlotEnd()
			.buildSlot()
				.name("sourcePatientId").value(dto.getCnsPaciente() + "^^^&2.16.840.1.113883.13.236.123456&ISO")
			.addSlotEnd()
			.buildSlot()
				.name("URI").value("https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RepositoryPS")
			.addSlotEnd()
			.buildClassification()
				.classificationScheme("urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation("")
				.buildSlot()
					.name("authorPerson").value(dto.getCnsPaciente() + "^^^^^^^^&1.2.840.113619.6.197&ISO")
				.addSlot()
				    .name("authorInstitution").value(dto.getNomeUnidadeSaude() + "^^^^^&1.2.3.4.5.678&ISO^^^^" + dto.getCnesUnidadeSaude())
			    .addSlot()
				    .name("authorRole").value("")
				.addSlot()
				    .name("authorSpecialty").value(dto.getCboProfissional() + "^^^&1.2.840..113619.6.197&ISO")
				.addSlotEnd()
			.addClassificationEnd()
			//classCode
			.buildClassification()
				.classificationScheme("urn:uuid:41a5887f-8865-4c09-adf7-e362475b143a")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation("34117-2")
				.buildSlot()
					.name("codingScheme").value("LOINC")
				.addSlotEnd()
				.buildInternationalString()
					.value("History and Physical Note")
				.addInternationalStringEnd()
			.addClassificationEnd()
			//confidentialityCode
			.buildClassification()
				.classificationScheme("urn:uuid:f4f85eac-e6cb-4883-b524-f2705394840f")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation("N")
				.buildSlot()
					.name("codingScheme").value("2.16.840.1.113883.5.25")
				.addSlotEnd()
				.buildInternationalString()
					.value("Normal")
				.addInternationalStringEnd()
			.addClassificationEnd()
			//formatCode
			.buildClassification()
				.classificationScheme("urn:uuid:a09d5840-386c-46f2-b5ad-9c3699a4309d")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation("tdd")
				.buildSlot()
					.name("codingScheme").value("openEHR")
				.addSlotEnd()
				.buildInternationalString()
					.value("Template Data Document")
				.addInternationalStringEnd()
			.addClassificationEnd()
			//healthcareFacilityTypeCode
			.buildClassification()
				.classificationScheme("urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation("01")
				.buildSlot()
					.name("codingScheme").value("CNES-TIPOS-ESTAB")
				.addSlotEnd()
				.buildInternationalString()
					.value("POSTO DE SAUDE")
				.addInternationalStringEnd()
			.addClassificationEnd()
			//practiceSettingCode
			.buildClassification()
				.classificationScheme("urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation(dto.getCboProfissional())
				.buildSlot()
					.name("codingScheme").value("CBO")
				.addSlotEnd()
				.buildInternationalString()
					.value(dto.getDescricaoCboProfissional())
				.addInternationalStringEnd()
			.addClassificationEnd()
			//typeCode
			.buildClassification()
				.classificationScheme("urn:uuid:f0306f51-975f-434e-a61c-c59651d33983")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation("11488-4")
				.buildSlot()
					.name("codingScheme").value("LOINC")
				.addSlotEnd()
				.buildInternationalString()
					.value("Consultation Note")
				.addInternationalStringEnd()
			.addClassificationEnd()
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:58a6f841-87b3-4a3e-92fd-a8ffeff98427")
				.value(dto.getCnsPaciente() + "^^^&2.16.840.1.113883.13.236&ISO")
				.id("ei" + this.atomicLong.incrementAndGet())
				.buildInternationalString()
					.value("XDSDocumentEntry.patientId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab")
				.value(dto.getDocumentId())
				.id("ei" + this.atomicLong.incrementAndGet())
				.buildInternationalString()
					.value("XDSDocumentEntry.uniqueId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
		.addExtrinsicObjectEnd();
		//@formatter:on
	}
}
