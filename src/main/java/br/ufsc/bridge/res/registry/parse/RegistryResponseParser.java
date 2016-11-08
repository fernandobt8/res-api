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
				if (!validateElement(identifiable)) {
					continue;
				}
				try {
					RegistryItem registry = parseDocument((ExtrinsicObjectType) identifiable.getValue());
					validateRegistry(registry);
					documents.add(registry);
				} catch (InvalidRegistryException e) {
					log.debug("Failed to parse.", e);
					continue;
				}
			}
		}
		return new RegistryResponse(documents);
	}

	private static boolean validateElement(JAXBElement<?> identifiable) {
		if (identifiable == null) {
			log.debug("Ignored registry. JAXBElement to parse is null.");
			return false;
		}
		if (identifiable.getValue() == null || !(identifiable.getValue() instanceof ExtrinsicObjectType)) {
			return false;
		}

		return true;
	}

	private static RegistryItem parseDocument(ExtrinsicObjectType extrinsicObject) throws InvalidRegistryException {

		RegistryItem registryItem = new RegistryItem();

		log.debug("Parse started.");
		for (SlotType1 slot : extrinsicObject.getSlot()) {
			loadSlotTypes(registryItem, slot);
		}

		for (ExternalIdentifierType identifier : extrinsicObject.getExternalIdentifier()) {
			loadExternalIdentifierTypes(registryItem, identifier);
		}

		for (ClassificationType classification : extrinsicObject.getClassification()) {
			loadClassificationTypesLotacao(registryItem, classification);
		}
		log.debug("Parse finished.");
		return registryItem;
	}

	private static void loadClassificationTypesLotacao(RegistryItem registryItem, ClassificationType classification) {
		if (UUID_CLASSIFICATION_SCHEME_LOTACAO.equals(classification.getClassificationScheme())) {
			for (SlotType1 slot : classification.getSlot()) {
				loadSlotTypes(registryItem, slot);
			}
		}
	}

	private static void loadExternalIdentifierTypes(RegistryItem registryItem, ExternalIdentifierType identifier) {
		if (UUID_DOCUMENT_UNIQUE_ID.equals(identifier.getIdentificationScheme())) {
			log.debug("Parse IdentificationScheme " + UUID_DOCUMENT_UNIQUE_ID + ".");
			registryItem.setDocumentUniqueId(identifier.getValue());
		}
	}

	private static void loadSlotTypes(RegistryItem registryItem, SlotType1 slot) {

		if (slot.getValueList() != null && slot.getName() != null) {
			String value = slot.getValueList().getValue().isEmpty() ? null : slot.getValueList().getValue().get(0);
			switch (slot.getName()) {
			case REPOSITORY_UNIQUE_ID:
				log.debug("Parse slot " + slot.getName() + ".");
				registryItem.setRepositoryUniqueId(value);
				break;
			case SERVICE_START_TIME:
				log.debug("Parse slot " + slot.getName() + ".");
				registryItem.setServiceStartTime(RDateUtil.fromISO(value));
				break;
			case AUTHOR_INSTITUTION:
				log.debug("Parse slot " + slot.getName() + ".");
				registryItem.setCnesUnidadeSaude(value);
				break;
			case AUTHOR_PERSON:
				log.debug("Parse slot " + slot.getName() + ".");
				registryItem.setCnsProfissional(value);
				break;
			case AUTHOR_SPECIALTY:
				log.debug("Parse slot " + slot.getName() + ".");
				registryItem.setCbo(value);
				break;
			}
		}
	}

	private static void validateRegistry(RegistryItem registry) throws InvalidRegistryException {
		validateNotBlank(registry.getRepositoryUniqueId(), REPOSITORY_UNIQUE_ID);
		validateNotBlank(registry.getDocumentUniqueId(), UUID_DOCUMENT_UNIQUE_ID);
		validateNotNull(registry.getServiceStartTime(), SERVICE_START_TIME);
		validateOnlyNumbers(registry.getCnesUnidadeSaude(), AUTHOR_INSTITUTION);
		validateOnlyNumbers(registry.getCnsProfissional(), AUTHOR_PERSON);
		validateOnlyNumbers(registry.getCbo(), AUTHOR_SPECIALTY);
	}

	private static void validateOnlyNumbers(String value, String slotDescription) throws InvalidRegistryException {
		validateNotBlank(value, slotDescription);
		if (!value.matches("\\d*")) {
			throw new InvalidRegistryException("Invalid values for " + slotDescription + ": '" + value + "'");
		}
	}

	private static void validateNotBlank(String value, String description) throws InvalidRegistryException {
		validateNotNull(value, description);
		if (value.trim().isEmpty()) {
			throw new InvalidRegistryException("Invalid values for " + description + ": '" + value + "'");
		}
	}

	private static void validateNotNull(Object value, String description) throws InvalidRegistryException {
		if (value == null) {
			throw new InvalidRegistryException("Invalid values for " + description + ": '" + value + "'");
		}
	}

}
