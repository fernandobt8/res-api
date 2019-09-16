package br.ufsc.bridge.res.service.soap.registry;

import java.util.ArrayList;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import br.ufsc.bridge.res.http.ResSoapHttpClient;
import br.ufsc.bridge.res.http.ResSoapHttpHeaders;
import br.ufsc.bridge.res.service.soap.ResSoapMessageBuilder;
import br.ufsc.bridge.res.service.soap.builder.SlotTypeBuilder.SlotTypeBuilderWrapper;
import br.ufsc.bridge.res.service.soap.exception.ResConsentPolicyException;
import br.ufsc.bridge.res.service.soap.exception.ResHttpConnectionException;
import br.ufsc.bridge.res.service.soap.exception.ResServerErrorException;
import br.ufsc.bridge.res.service.soap.exception.ResServiceFatalException;
import br.ufsc.bridge.res.service.soap.exception.ResXDSbException;
import br.ufsc.bridge.res.service.soap.registry.dto.AdhocQueryResponseXPath;
import br.ufsc.bridge.res.service.soap.registry.dto.RegistryFilter;
import br.ufsc.bridge.res.service.soap.registry.dto.RegistryItem;
import br.ufsc.bridge.res.service.soap.registry.dto.RegistryResponse;
import br.ufsc.bridge.res.service.soap.registry.parse.RegistryResponseParser;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.ResLogError;
import br.ufsc.bridge.soap.http.SoapCredential;
import br.ufsc.bridge.soap.http.SoapHttpRequest;
import br.ufsc.bridge.soap.http.exception.SoapCreateMessageException;
import br.ufsc.bridge.soap.http.exception.SoapHttpConnectionException;
import br.ufsc.bridge.soap.http.exception.SoapHttpResponseException;
import br.ufsc.bridge.soap.http.exception.SoapReadMessageException;
import br.ufsc.bridge.soap.xpath.XPathFactoryAssist;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;
import oasis.names.tc.ebxml_regrep.xsd.query._3.ResponseOptionType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.AdhocQueryType;

public class RegistryService {

	private static final String ACTION = "urn:ihe:iti:2007:RegistryStoredQuery";

	private ResLogError printerResponseError;
	private ResSoapMessageBuilder soapMessageSender;

	public RegistryService(SoapCredential c, String url) {
		this.soapMessageSender = new ResSoapMessageBuilder(c, url, ACTION);
		this.printerResponseError = new ResLogError();
	}

	public RegistryResponse<RegistryItem> getRegistriesHeader(RegistryFilter filter)
			throws ResHttpConnectionException, ResServiceFatalException, ResServerErrorException, ResConsentPolicyException {
		try {
			byte[] message = this.soapMessageSender.createMessage(this.buildRequest(filter, "LeafClass"));
			SoapHttpRequest httpRequest = new SoapHttpRequest(this.soapMessageSender.getUrl(), ACTION, message)
					.addHeader(ResSoapHttpHeaders.CNS_PROFISSIONAL, filter.getCnsProfissional())
					.addHeader(ResSoapHttpHeaders.CBO, filter.getCboProfissional())
					.addHeader(ResSoapHttpHeaders.CNES, filter.getCnesProfissional());

			Document soap = ResSoapHttpClient.request(httpRequest).getSoap();

			AdhocQueryResponseXPath queryResponse = new AdhocQueryResponseXPath(soap);
			if (queryResponse.isSuccess()) {
				return RegistryResponseParser.parse(queryResponse);
			} else {
				this.printerResponseError.parserException(new RegistryErrorListXPath(soap));
				return null;
			}
		} catch (SoapHttpConnectionException e) {
			throw new ResHttpConnectionException(e);
		} catch (XPathExpressionException e) {
			throw new ResServiceFatalException("Error parsing \"AdhocQueryResponse\"", e);
		} catch (SoapCreateMessageException | SoapHttpResponseException | SoapReadMessageException | ResXDSbException e) {
			throw new ResServiceFatalException(e);
		}
	}

	public RegistryResponse<String> getRegistriesRef(RegistryFilter filter)
			throws ResHttpConnectionException, ResServiceFatalException, ResServerErrorException, ResConsentPolicyException {
		try {
			byte[] message = this.soapMessageSender.createMessage(this.buildRequest(filter, "ObjectRef"));
			SoapHttpRequest httpRequest = new SoapHttpRequest(this.soapMessageSender.getUrl(), RegistryService.ACTION, message)
					.addHeader(ResSoapHttpHeaders.CNS_PROFISSIONAL, filter.getCnsProfissional())
					.addHeader(ResSoapHttpHeaders.CBO, filter.getCboProfissional())
					.addHeader(ResSoapHttpHeaders.CNES, filter.getCnesProfissional());

			Document soap = ResSoapHttpClient.request(httpRequest).getSoap();

			AdhocQueryResponseXPath queryResponse = new AdhocQueryResponseXPath(soap);

			if (queryResponse.isSuccess()) {
				return this.getRegistryResponse(queryResponse);
			} else {
				this.printerResponseError.parserException(new RegistryErrorListXPath(soap));
				return null;
			}
		} catch (SoapHttpConnectionException e) {
			throw new ResHttpConnectionException(e);
		} catch (XPathExpressionException e) {
			throw new ResServiceFatalException("Error parsing \"AdhocQueryResponse\"", e);
		} catch (SoapCreateMessageException | SoapHttpResponseException | SoapReadMessageException | ResXDSbException e) {
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
				.value("'" + filter.getPatientId() + "'")
			.addSlotIf(filter.hasPatientId())
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
