package br.ufsc.bridge.res.service.soap.repository.parser;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.service.soap.builder.IdentifiableTypeBuilder;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.XDSbUtil;

import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType.Document;
import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType.Include;

public class DocumentParser {

	private AtomicLong atomicLong;

	public DocumentParser() {
		this.atomicLong = new AtomicLong();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void parser(ProvideAndRegisterDocumentSetRequestType provideRegister, RepositorySaveDocumentDTO dto) {
		String docId = "doc" + this.atomicLong.incrementAndGet();

		Document document = new Document();
		document.setId(docId);
		document.setInclude(new Include(StringUtils.prependIfMissing(dto.getDocumentId(), "cid:")));
		provideRegister.getDocument().add(document);

		List<JAXBElement<?>> identifiables = (List) provideRegister.getSubmitObjectsRequest().getRegistryObjectList().getIdentifiable();

		String[] nomeProf = XDSbUtil.nameToXDSbName(dto.getNomeProfissional());
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
			//mimeType
			.mimeType("text/xml")
			.objectType("urn:uuid:7edca82f-054d-47f2-a032-9b2a5b5186c1")
			//creationTime
			.buildSlot()
				.name("creationTime").value(RDateUtil.dateToISOXDSb(new Date()))
			.addSlotEnd()
			//languageCode
			.buildSlot()
				.name("languageCode").value("pt-BR")
			.addSlotEnd()
			//serviceStartTime
			.buildSlot()
				.name("serviceStartTime").value(RDateUtil.dateToISOXDSb(dto.getDataInicioAtendimento()))
			.addSlotEnd()
			//serviceStopTime
			.buildSlot()
				.name("serviceStopTime").value(RDateUtil.dateToISOXDSb(dto.getDataFimAtendimento()))
			.addSlotEnd()
			//sourcePatientId
			.buildSlot()
				.name("sourcePatientId").value(dto.getSourcePatientId() )
			.addSlotEnd()
			//URI
			.buildSlot()
				.name("URI").value(dto.getUrl())
			.addSlotEnd()
			//author
			.buildClassification()
				.classificationScheme("urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation("")
				.buildSlot()
					.name("authorPerson").value(dto.getCnsProfissional() + "^" + nomeProf[2] + "^" + nomeProf[0] + "^" + nomeProf[1] + "^^^^^&1.2.840.113619.6.197&ISO")
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
				.nodeRepresentation(dto.getClassCode().getCode())
				.buildSlot()
					.name("codingScheme").value(dto.getClassCode().getCodingScheme())
				.addSlotEnd()
				.buildInternationalString()
					.value("Template Document Schema")
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
				.nodeRepresentation(dto.getFormatCode().getCode())
				.buildSlot()
					.name("codingScheme").value(dto.getFormatCode().getCodingScheme())
				.addSlotEnd()
				.buildInternationalString()
					.value("Template Data Document")
				.addInternationalStringEnd()
			.addClassificationEnd()
			//healthcareFacilityTypeCode
			.buildClassification()
				.classificationScheme("urn:uuid:f33fb8ac-18af-42cc-ae0e-ed0b0bdb91e1")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation(dto.getHealthcareFacilityTypeCode().getCode())
				.buildSlot()
					.name("codingScheme").value(dto.getHealthcareFacilityTypeCode().getCodingScheme())
				.addSlotEnd()
				.buildInternationalString()
					.value("POSTO DE SAUDE")
				.addInternationalStringEnd()
			.addClassificationEnd()
			//practiceSettingCode(CBO)
			.buildClassification()
				.classificationScheme("urn:uuid:cccf5598-8b07-4b77-a05e-ae952c785ead")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation(dto.getPracticeSettingCode().getCode())
				.buildSlot()
					.name("codingScheme").value(dto.getPracticeSettingCode().getCodingScheme())
				.addSlotEnd()
				.buildInternationalString()
					.value(dto.getDescricaoCboProfissional())
				.addInternationalStringEnd()
			.addClassificationEnd()
			//typeCode
			.buildClassification()
				.classificationScheme("urn:uuid:f0306f51-975f-434e-a61c-c59651d33983")
				.id("cl" + this.atomicLong.incrementAndGet())
				.nodeRepresentation(dto.getTypeCode().getCode())
				.buildSlot()
					.name("codingScheme").value(dto.getTypeCode().getCodingScheme())
				.addSlotEnd()
				.buildInternationalString()
					.value("Consultation Note")
				.addInternationalStringEnd()
			.addClassificationEnd()
			//patientId
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:58a6f841-87b3-4a3e-92fd-a8ffeff98427")
				.value(dto.getPatientId())
				.id("ei" + this.atomicLong.incrementAndGet())
				.buildInternationalString()
					.value("XDSDocumentEntry.patientId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
			//uniqueId
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab")
				.value(dto.getDocumentId())
				.id("ei" + this.atomicLong.incrementAndGet())
				.buildInternationalString()
					.value("XDSDocumentEntry.uniqueId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
		.addExtrinsicObjectEnd();
		//eventCodeList (sigtap) exemplo sem
		//legalAuthenticator (CNS) exemplo sem
		//@formatter:on
	}
}
