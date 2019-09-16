package br.ufsc.bridge.res.service.soap.repository.parser;

import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;

import br.ufsc.bridge.res.service.soap.builder.IdentifiableTypeBuilder;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositorySaveDTO;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.XDSbUtil;

import ihe.iti.xds_b._2007.ProvideAndRegisterDocumentSetRequestType;

public class SubmissionSetParser {

	public static String SUBMISSIONSET_ID = "ss1";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void parser(RepositorySaveDTO dto, ProvideAndRegisterDocumentSetRequestType provideRegister) {
		List<JAXBElement<?>> identifiables = (List) provideRegister.getSubmitObjectsRequest().getRegistryObjectList().getIdentifiable();

		String creationTime = RDateUtil.dateToISOXDSb(new Date());
		String[] nomeProf = XDSbUtil.nameToXDSbName(dto.getNomeProfissional());
		//@formatter:off
		new IdentifiableTypeBuilder(identifiables)
		.buildClassification()
			.id("sscl1")
			.classificationNode("urn:uuid:a54d6aa5-d40d-43f9-88c5-b4633d873bdd")
			.classifiedObject(SUBMISSIONSET_ID)
		.addClassificationEnd()
		.buildRegistryPackage()
			.id(SUBMISSIONSET_ID)
			//submissionTime
			.buildSlot()
				.name("submissionTime").value(creationTime)
			.addSlotEnd()
			//author
			.buildClassification()
				.classificationScheme("urn:uuid:a7058bb9-b4e4-4307-ba5b-e3f0ab85e12d")
				.id("sscl2")
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
			//contentTypeCode
			.buildClassification()
				.classificationScheme("urn:uuid:aa543740-bdda-424e-8c96-df4873be8500")
				.id("sscl3")
				.nodeRepresentation(dto.getContentTypeCode().getCode())
				.buildSlot()
					.name("codingScheme").value(dto.getContentTypeCode().getCodingScheme())
				.addSlotEnd()
				.buildInternationalString()
					.value("Summarization of Episode Note")
				.addInternationalStringEnd()
			.addClassificationEnd()
			//patientId
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:6b5aea1a-874d-4603-a4bc-96a0a7b38446")
				.value(dto.getPatientId())
				.id("ssei1")
				.buildInternationalString()
					.value("XDSSubmissionSet.patientId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
			//sourceId
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:554ac39e-e3fe-47fe-b233-965d2a147832")
				.value(dto.getIdInstalacao())
				.id("ssei2")
				.buildInternationalString()
					.value("XDSSubmissionSet.sourceId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
			//uniqueId
			.buildExternalIdentifier()
				.identificationScheme("urn:uuid:96fdda7c-d067-4183-912e-bf5ee74998a8")
				.value(dto.getSubmissionSetId())
				.id("ssei3")
				.buildInternationalString()
					.value("XDSSubmissionSet.uniqueId")
				.addInternationalStringEnd()
			.addExternalIdentifierEnd()
		.addRegistryPackageEnd();
		//intendedRecipient
		//@formatter:on
	}
}
