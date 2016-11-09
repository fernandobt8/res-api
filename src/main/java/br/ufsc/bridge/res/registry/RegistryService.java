package br.ufsc.bridge.res.registry;

import java.util.List;

import javax.xml.bind.JAXBElement;

import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;
import br.ufsc.bridge.res.dto.header.Credential;
import br.ufsc.bridge.res.dto.header.RegistryHeader;
import br.ufsc.bridge.res.dto.registry.RegistryFilter;
import br.ufsc.bridge.res.dto.registry.RegistryItem;
import br.ufsc.bridge.res.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.util.RDateUtil;

import gov.nist.registry.ws.serviceclasses.Xdsregistryb;
import gov.nist.registry.ws.serviceclasses.XdsregistrybPortType;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.query._3.ResponseOptionType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.AdhocQueryType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ClassificationType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExternalIdentifierType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExtrinsicObjectType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;

@Slf4j
public class RegistryService {

	private static final String SUCCESS = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Success";
	// private static final String FAILURE = "urn:oasis:names:tc:ebxml-regrep:ResponseStatusType:Failure";

	private XdsregistrybPortType endpoint;

	public RegistryService(Credential c) {
		Xdsregistryb xdsregistryb = new Xdsregistryb();
		xdsregistryb.setHandlerResolver(new RegistryHeader(c));

		this.endpoint = xdsregistryb.getXdsregistrybHttpSoap12Endpoint();
	}

	public RegistryResponse getRegistries(RegistryFilter filter) {
		AdhocQueryResponse queryResponse = this.endpoint.adhocQueryRequest(this.buildRequest(filter));

		RegistryResponse registryResponse = new RegistryResponse();
		if (queryResponse.getStatus().equals(SUCCESS)) {
			for (JAXBElement<?> identifiable : queryResponse.getRegistryObjectList().getIdentifiable()) {
				Object value = identifiable.getValue();
				if (value instanceof ExtrinsicObjectType) {
					RegistryItem registryItem = new RegistryItem();
					ExtrinsicObjectType extrinsicObject = (ExtrinsicObjectType) value;

					for (SlotType1 slot : extrinsicObject.getSlot()) {
						if (slot.getName().equals("repositoryUniqueId")) {
							registryItem.setRepositoryUniqueId(slot.getValueList().getValue().get(0));
						}
						if (slot.getName().equals("serviceStartTime")) {
							registryItem.setServiceStartTime(RDateUtil.fromISO(slot.getValueList().getValue().get(0)));
						}
					}
					for (ExternalIdentifierType identifier : extrinsicObject.getExternalIdentifier()) {
						if (identifier.getIdentificationScheme().equals("urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab")) {
							registryItem.setDocumentUniqueId(identifier.getValue());
						}
					}
					for (ClassificationType classification : extrinsicObject.getClassification()) {
						if (classification.getClassificationScheme().equals("urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d")) {
							for (SlotType1 slot : classification.getSlot()) {
								List<String> values = slot.getValueList().getValue();
								if (!values.isEmpty()) {
									switch (slot.getName()) {
									case "authorInstitution":
										registryItem.setUnidadeSaude(values.get(0));
										break;
									case "authorPerson":
										registryItem.setNomeProfissional(values.get(0));
										break;
									case "authorSpecialty":
										registryItem.setCbo(values.get(0));
										break;
									}
								}

							}
						}
					}

					registryResponse.getItems().add(registryItem);
				}
			}
		} else {
			log.error("erro no request");
		}
		return registryResponse;
	}

	private AdhocQueryRequest buildRequest(RegistryFilter filter) {
		ResponseOptionType responseOptionType = new ResponseOptionType();
		responseOptionType.setReturnComposedObjects(true);
		responseOptionType.setReturnType("LeafClass");

		AdhocQueryType queryType = new AdhocQueryType();
		queryType.setId("urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d");

		//@formatter:off
		new SlotTypeBuilderWrapper<>(queryType.getSlot())
				.name("$XDSDocumentEntryPatientId")
				.value("'" + filter.getCnsCidadao() + "^^^&2.16.840.1.113883.13.236&ISO'")
			.addSlot()
				.name("$XDSDocumentEntryCreationTimeFrom")
				.value(RDateUtil.fromDate(filter.getDataInicial()))
			.addSlotIf(filter.hasDataInicial())
				.name("$XDSDocumentEntryCreationTimeTo")
				.value(RDateUtil.fromDate(filter.getDataFim()))
			.addSlotIf(filter.hasDataFim())
				.name("$XDSDocumentEntryStatus")
				.value("('urn:oasis:names:tc:ebxml-regrep:StatusType:Approved')")
			.addSlot();
		//@formatter:on

		AdhocQueryRequest adhocQueryRequest = new AdhocQueryRequest();
		adhocQueryRequest.setResponseOption(responseOptionType);
		adhocQueryRequest.setAdhocQuery(queryType);
		return adhocQueryRequest;
	}
}
