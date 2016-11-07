package br.ufsc.bridge.res.registry.parse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.extern.slf4j.Slf4j;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ClassificationType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExternalIdentifierType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExtrinsicObjectType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import br.ufsc.bridge.res.dto.registry.RegistryItem;
import br.ufsc.bridge.res.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.util.RDateUtil;

@Slf4j
public class RegistryResponseParser {

	private static final String AUTHOR_SPECIALTY = "authorSpecialty";
	private static final String AUTHOR_PERSON = "authorPerson";
	private static final String AUTHOR_INSTITUTION = "authorInstitution";
	private static final String SERVICE_START_TIME = "serviceStartTime";
	private static final String REPOSITORY_UNIQUE_ID = "repositoryUniqueId";
	private static final String UUID_CLASSIFICATION_SCHEME_LOTACAO = "urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d";
	private static final String UUID_DOCUMENT_UNIQUE_ID = "urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab";

	public static RegistryResponse parse(AdhocQueryResponse queryResponse) {

		List<RegistryItem> documents = new ArrayList<RegistryItem>();

		if (queryResponse.getRegistryObjectList() != null) {
			for (JAXBElement<?> identifiable : queryResponse.getRegistryObjectList().getIdentifiable()) {
				Object value = identifiable.getValue();
				if (value != null && value instanceof ExtrinsicObjectType) {

					log.debug("Parse started.");
					try {
						documents.add(parseDocument(value));
					} catch (InvalidRegistryException e) {
						log.debug("Failed to parse.", e);
						continue;
					}
					log.debug("Parse finished.");

				}
			}
		}
		return new RegistryResponse(documents);
	}

	private static RegistryItem parseDocument(Object value) throws InvalidRegistryException {

		RegistryItem registryItem = new RegistryItem();
		ExtrinsicObjectType extrinsicObject = (ExtrinsicObjectType) value;

		log.debug("Parse slots repositoryUniqueId e serviceStartTime.");
		for (SlotType1 slot : extrinsicObject.getSlot()) {
			loadSlotTypes(registryItem, slot);
		}

		log.debug("Parse slots ExternalIdentifier documentUniqueId.");
		for (ExternalIdentifierType identifier : extrinsicObject.getExternalIdentifier()) {
			loadExternalIdentifierTypes(registryItem, identifier);
		}

		log.debug("Parse slots lotacao.");
		for (ClassificationType classification : extrinsicObject.getClassification()) {
			loadClassificationTypesLotacao(registryItem, classification);
		}
		return registryItem;
	}

	private static void loadClassificationTypesLotacao(RegistryItem registryItem, ClassificationType classification) throws InvalidRegistryException {
		if (UUID_CLASSIFICATION_SCHEME_LOTACAO.equals(classification.getClassificationScheme())) {
			for (SlotType1 slot : classification.getSlot()) {
				loadSlotTypes(registryItem, slot);
			}
		}
	}

	private static void loadExternalIdentifierTypes(RegistryItem registryItem, ExternalIdentifierType identifier) throws InvalidRegistryException {
		if (UUID_DOCUMENT_UNIQUE_ID.equals(identifier.getIdentificationScheme())) {
			validateNotBlank(identifier.getValue(), UUID_DOCUMENT_UNIQUE_ID);
			registryItem.setDocumentUniqueId(identifier.getValue());
		}
	}

	private static void loadSlotTypes(RegistryItem registryItem, SlotType1 slot) throws InvalidRegistryException {

		if (slot.getValueList() != null) {
			List<String> values = slot.getValueList().getValue();
			switch (slot.getName()) {
			case REPOSITORY_UNIQUE_ID:
				validateNotBlank(values.get(0), REPOSITORY_UNIQUE_ID);
				registryItem.setRepositoryUniqueId(values.get(0));
				break;
			case SERVICE_START_TIME:
				validateNotBlank(values.get(0), SERVICE_START_TIME);
				registryItem.setServiceStartTime(RDateUtil.fromISO(values.get(0)));
				break;
			case AUTHOR_INSTITUTION:
				validateOnlyNumbers(values.get(0), AUTHOR_INSTITUTION);
				registryItem.setCnesUnidadeSaude(values.get(0));
				break;
			case AUTHOR_PERSON:
				validateOnlyNumbers(values.get(0), AUTHOR_PERSON);
				registryItem.setCnsProfissional(values.get(0));
				break;
			case AUTHOR_SPECIALTY:
				validateOnlyNumbers(values.get(0), AUTHOR_SPECIALTY);
				registryItem.setCbo(values.get(0));
				break;
			}
		}
	}

	private static void validateOnlyNumbers(String value, String slotDescription) throws InvalidRegistryException {
		validateNotBlank(value, slotDescription);
		if (!value.matches("\\d*")) {
			throw new InvalidRegistryException("Invalid values for " + slotDescription + ": '" + value + "'");
		}
	}

	private static void validateNotBlank(String value, String description) throws InvalidRegistryException {
		if (value == null || value.trim().isEmpty()) {
			throw new InvalidRegistryException("Invalid values for " + description + ": '" + value + "'");
		}
	}

}
