package br.ufsc.bridge.res.service.registry.parse;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.service.dto.registry.AdhocQueryResponseXPath;
import br.ufsc.bridge.res.service.dto.registry.RegistryItem;
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.service.exception.ResInvalidRegistryException;
import br.ufsc.bridge.res.util.XDSbUtil;
import br.ufsc.bridge.res.util.XPathFactoryAssist;

@Slf4j
public class RegistryResponseParser {

	private static final String AUTHOR_SPECIALTY = "authorSpecialty";
	private static final String AUTHOR_PERSON = "authorPerson";
	private static final String AUTHOR_INSTITUTION = "authorInstitution";
	private static final String SERVICE_START_TIME = "serviceStartTime";
	private static final String REPOSITORY_UNIQUE_ID = "repositoryUniqueId";
	private static final String UUID_DOCUMENT_UNIQUE_ID = "urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab";

	public static RegistryResponse<RegistryItem> parse(AdhocQueryResponseXPath queryResponse) {
		List<RegistryItem> documents = new ArrayList<>();
		for (XPathFactoryAssist header : queryResponse.getHeaders()) {
			try {
				RegistryItem registryItem = new RegistryItem();
				registryItem.setRepositoryUniqueId(queryResponse.getHeaderRepositoryUniqueId(header));
				registryItem.setServiceStartTime(queryResponse.getHeaderServiceStartTime(header));
				registryItem.setCnesUnidadeSaude(queryResponse.getHeaderUnidadeSaude(header));
				registryItem.setNomeUnidadeSaude(registryItem.getCnesUnidadeSaude());
				registryItem.setCnsProfissional(queryResponse.getHeaderAuthor(header));
				registryItem.setNomeProfissional(registryItem.getCnsProfissional());
				registryItem.setCbo(queryResponse.getHeaderCbo(header));
				registryItem.setRepositoryURL(queryResponse.getHeaderRepositoryURL(header));
				registryItem.setDocumentUniqueId(queryResponse.getDocumentUniqueId(header));

				filterValues(registryItem);
				documents.add(registryItem);
			} catch (ResInvalidRegistryException e) {
				log.info("Failed to parse. " + e.getMessage());
			} catch (XPathExpressionException e) {
				log.info("Failed to parse. " + e.getMessage());
			}
		}
		return new RegistryResponse<>(documents);
	}

	private static void filterValues(RegistryItem registry) throws ResInvalidRegistryException {
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
		registry.setNomeProfissional(XDSbUtil.xdsbNameToName(registry.getNomeProfissional()));
		try {
			registry.setCbo(filtertNumberValue(registry.getCbo()));
		} catch (Exception e) {
			message.append(AUTHOR_SPECIALTY + ": '" + registry.getCbo() + "' ");
			hasError = true;
		}
		if (hasError) {
			throw new ResInvalidRegistryException("Invalid values for: " + message);
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
