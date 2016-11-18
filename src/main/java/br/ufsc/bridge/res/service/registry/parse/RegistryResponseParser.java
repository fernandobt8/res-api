package br.ufsc.bridge.res.service.registry.parse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.extern.slf4j.Slf4j;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ClassificationType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExternalIdentifierType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExtrinsicObjectType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.service.dto.registry.RegistryItem;
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
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

	public static RegistryResponse<RegistryItem> parse(AdhocQueryResponse queryResponse) {

		List<RegistryItem> documents = new ArrayList<>();

		if (queryResponse.getRegistryObjectList() != null) {
			for (JAXBElement<?> identifiable : queryResponse.getRegistryObjectList().getIdentifiable()) {
				if (!validateElement(identifiable)) {
					continue;
				}
				try {
					RegistryItem registry = parseDocument((ExtrinsicObjectType) identifiable.getValue());
					filterValues(registry);
					documents.add(registry);
				} catch (InvalidRegistryException e) {
					log.info("Failed to parse. " + e.getMessage());
					continue;
				}
			}
		}
		return new RegistryResponse<>(Boolean.TRUE, documents);
	}

	private static boolean validateElement(JAXBElement<?> identifiable) {
		if (identifiable == null) {
			log.info("Ignored registry. JAXBElement to parse is null.");
			return false;
		}
		if (identifiable.getValue() == null || !(identifiable.getValue() instanceof ExtrinsicObjectType)) {
			return false;
		}

		return true;
	}

	private static RegistryItem parseDocument(ExtrinsicObjectType extrinsicObject) throws InvalidRegistryException {

		RegistryItem registryItem = new RegistryItem();

		log.info("Parse started.");
		for (SlotType1 slot : extrinsicObject.getSlot()) {
			loadSlotTypes(registryItem, slot);
		}

		for (ExternalIdentifierType identifier : extrinsicObject.getExternalIdentifier()) {
			loadExternalIdentifierTypes(registryItem, identifier);
		}

		for (ClassificationType classification : extrinsicObject.getClassification()) {
			loadClassificationTypesLotacao(registryItem, classification);
		}
		log.info("Parse finished.");
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
				registryItem.setServiceStartTime(RDateUtil.isoXDSbToDate(value));
				break;
			case AUTHOR_INSTITUTION:
				log.debug("Parse slot " + slot.getName() + ".");
				registryItem.setCnesUnidadeSaude(value);
				registryItem.setNomeUnidadeSaude(value);
				break;
			case AUTHOR_PERSON:
				log.debug("Parse slot " + slot.getName() + ".");
				registryItem.setCnsProfissional(value);
				registryItem.setNomeProfissional(value);
				break;
			case AUTHOR_SPECIALTY:
				log.debug("Parse slot " + slot.getName() + ".");
				registryItem.setCbo(value);
				break;
			}
		}
	}

	private static void filterValues(RegistryItem registry) throws InvalidRegistryException {
		StringBuilder message = new StringBuilder();
		boolean hasError = false;

		if (StringUtils.isBlank(registry.getRepositoryUniqueId())) {
			message.append(REPOSITORY_UNIQUE_ID + ": '" + registry.getRepositoryUniqueId() + "' ");
			hasError = true;
		}
		if (StringUtils.isBlank(registry.getDocumentUniqueId())) {
			message.append(UUID_DOCUMENT_UNIQUE_ID + ": '" + registry.getDocumentUniqueId() + "' ");
			hasError = true;
		}
		if (registry.getServiceStartTime() == null) {
			message.append(SERVICE_START_TIME + ": '" + registry.getServiceStartTime() + "' ");
			hasError = true;
		}

		try {
			registry.setCnesUnidadeSaude(filterCnesUnidadeSaude(registry.getCnesUnidadeSaude()));
		} catch (Exception e) {
			message.append(AUTHOR_INSTITUTION + ": '" + registry.getCnesUnidadeSaude() + "' ");
			hasError = true;
		}

		registry.setNomeUnidadeSaude(filterNomeUnidadeSaude(registry.getNomeUnidadeSaude()));

		try {
			registry.setCnsProfissional(filtertCnsProfissional(registry.getCnsProfissional()));
		} catch (Exception e) {
			message.append(AUTHOR_PERSON + ": '" + registry.getCnsProfissional() + "' ");
			hasError = true;
		}
		registry.setNomeProfissional(filtertNomeProfissional(registry.getNomeProfissional()));

		try {
			registry.setCbo(filtertNumberValue(registry.getCbo()));
		} catch (Exception e) {
			message.append(AUTHOR_SPECIALTY + ": '" + registry.getCbo() + "' ");
			hasError = true;
		}

		if (hasError) {
			throw new InvalidRegistryException("Invalid values for: " + message);
		}

	}

	private static String filterCnesUnidadeSaude(String cnesUnidadeSaude) throws Exception {
		if (StringUtils.isNotBlank(cnesUnidadeSaude) && cnesUnidadeSaude.contains("^")) {
			String cnes = cnesUnidadeSaude.substring(cnesUnidadeSaude.lastIndexOf("^") + 1, cnesUnidadeSaude.length());
			if (validateOnlyNumbers(cnes)) {
				return cnes;
			}
		}
		throw new Exception();
	}

	private static String filterNomeUnidadeSaude(String nomeUnidadeSaude) {
		if (StringUtils.isNotBlank(nomeUnidadeSaude) && nomeUnidadeSaude.contains("^")) {
			nomeUnidadeSaude = nomeUnidadeSaude.substring(0, nomeUnidadeSaude.indexOf("^"));
			return StringUtils.isNotBlank(nomeUnidadeSaude) ? nomeUnidadeSaude : null;
		}
		return null;
	}

	private static String filtertCnsProfissional(String cnsProfissional) throws Exception {
		if (StringUtils.isNotBlank(cnsProfissional) && cnsProfissional.contains("^")) {
			String cns = cnsProfissional.substring(0, cnsProfissional.indexOf("^"));

			if (validateOnlyNumbers(cns)) {
				return cns;
			}
		}

		throw new Exception();
	}

	private static String filtertNomeProfissional(String nomeProfissional) {
		if (StringUtils.isNotBlank(nomeProfissional) && nomeProfissional.contains("^")) {

			String[] values = nomeProfissional.split("\\^");

			String nome = values.length >= 3 ? values[2] : "";
			String segundoNome = values.length >= 4 ? values[3] : "";
			String ultimoNome = values.length >= 2 ? values[1] : "";
			nomeProfissional = nome + " " + segundoNome + " " + ultimoNome;
			return StringUtils.isNotBlank(nomeProfissional) ? nomeProfissional.trim() : null;
		}
		return null;
	}

	private static String filtertNumberValue(String value) throws Exception {
		if (StringUtils.isNotBlank(value) && value.contains("^") && validateOnlyNumbers(value.substring(0, value.indexOf("^")))) {
			return value.substring(0, value.indexOf("^"));
		} else {
			throw new Exception();
		}
	}

	private static boolean validateOnlyNumbers(String value) {
		return StringUtils.isNotBlank(value) && value.matches("\\d*");
	}

}
