package br.ufsc.bridge.res.service.registry;

import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import br.ufsc.bridge.res.http.ResHttpClient;
import br.ufsc.bridge.res.http.exception.ResHttpConnectionException;
import br.ufsc.bridge.res.http.exception.ResHttpRequestResponseException;
import br.ufsc.bridge.res.service.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;
import br.ufsc.bridge.res.service.dto.RegistryErrorListXPath;
import br.ufsc.bridge.res.service.dto.header.Credential;
import br.ufsc.bridge.res.service.dto.header.RegistryHeader;
import br.ufsc.bridge.res.service.dto.registry.AdhocQueryResponseXPath;
import br.ufsc.bridge.res.service.dto.registry.RegistryFilter;
import br.ufsc.bridge.res.service.dto.registry.RegistryItem;
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.service.exception.ResServiceFatalException;
import br.ufsc.bridge.res.service.exception.ResServiceSevereException;
import br.ufsc.bridge.res.service.exception.ResXDSbException;
import br.ufsc.bridge.res.service.registry.parse.RegistryResponseParser;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.ResLogError;
import br.ufsc.bridge.res.util.XPathFactoryAssist;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.ResponseOptionType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.AdhocQueryType;

public class RegistryService {

	private ResLogError printerResponseError;
	private ResHttpClient httpClient;

	public RegistryService(Credential c) throws ResServiceFatalException {
		this.httpClient = new ResHttpClient(new RegistryHeader(c), "urn:ihe:iti:2007:ns:AdhocQueryRequestRequest");
		try {
			this.httpClient.setUrl("https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RegistryPS");
		} catch (MalformedURLException e) {
			throw new ResServiceFatalException("Invalid Registry URL", e);
		}
		this.printerResponseError = new ResLogError();
	}

	public RegistryResponse<RegistryItem> getRegistriesHeader(RegistryFilter filter) throws ResServiceSevereException, ResServiceFatalException {
		AdhocQueryResponseXPath queryResponse = null;
		Document response;
		try {
			response = this.httpClient.send(this.buildRequest(filter, "LeafClass"));
			queryResponse = new AdhocQueryResponseXPath(response);

			if (queryResponse.isSuccess()) {
				return RegistryResponseParser.parse(queryResponse);
			} else {
				this.printerResponseError.parserException(new RegistryErrorListXPath(response));
				return null;
			}
		} catch (ResHttpConnectionException e) {
			throw new ResServiceSevereException(e);
		} catch (XPathExpressionException e) {
			throw new ResServiceFatalException("Error parsing \"AdhocQueryResponse\"", e);
		} catch (ResHttpRequestResponseException | ResXDSbException e) {
			throw new ResServiceFatalException(e);
		}
	}

	public RegistryResponse<String> getRegistriesRef(RegistryFilter filter) throws ResServiceSevereException, ResServiceFatalException {
		AdhocQueryResponseXPath queryResponse = null;
		Document response;
		try {
			response = this.httpClient.send(this.buildRequest(filter, "ObjectRef"));
			queryResponse = new AdhocQueryResponseXPath(response);

			if (queryResponse.isSuccess()) {
				return this.getRegistryResponse(queryResponse);
			} else {
				this.printerResponseError.parserException(new RegistryErrorListXPath(response));
				return null;
			}
		} catch (ResHttpConnectionException e) {
			throw new ResServiceSevereException(e);
		} catch (XPathExpressionException e) {
			throw new ResServiceFatalException("Error parsing \"AdhocQueryResponse\"", e);
		} catch (ResHttpRequestResponseException | ResXDSbException e) {
			throw new ResServiceFatalException(e);
		}
	}

	public RegistryResponse<String> getRegistryResponse(AdhocQueryResponseXPath queryResponse) throws XPathExpressionException {
		ArrayList<String> uuids = new ArrayList<>();
		for (XPathFactoryAssist refs : queryResponse.getObjectRefs()) {
			uuids.add(refs.getString("./@id"));
		}
		return new RegistryResponse<>(uuids);
	}

	public AdhocQueryRequest buildRequest(RegistryFilter filter, String tipoBusca) {
		ResponseOptionType responseOptionType = new ResponseOptionType();
		responseOptionType.setReturnComposedObjects(true);
		responseOptionType.setReturnType(tipoBusca);

		AdhocQueryType queryType = new AdhocQueryType();
		if (filter.hasEntryUUID()) {
			queryType.setId("urn:uuid:5c4f972b-d56b-40ac-a5fc-c8ca9b40b9d4");
		} else {
			queryType.setId("urn:uuid:14d4debf-8f97-4251-9a74-a90016b0af0d");
		}

		//@formatter:off
		new SlotTypeBuilderWrapper<>(queryType.getSlot())
				.name("$XDSDocumentEntryPatientId")
				.value("'" + filter.getCnsCidadao() + "^^^&2.16.840.1.113883.13.236&ISO'")
			.addSlotIf(filter.hasCnsCidadao())
				.name("$XDSDocumentEntryCreationTimeFrom")
				.value(RDateUtil.dateToISOXDSb(filter.getDataInicial()))
			.addSlotIf(filter.hasDataInicial())
				.name("$XDSDocumentEntryCreationTimeTo")
				.value(RDateUtil.dateToISOXDSb(filter.getDataFim()))
			.addSlotIf(filter.hasDataFim())
				.name("$XDSDocumentEntryEntryUUID")
				.value(filter.getEntryUUIDs(), true)
			.addSlotIf(filter.hasEntryUUID())
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
