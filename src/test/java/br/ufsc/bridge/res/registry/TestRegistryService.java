package br.ufsc.bridge.res.registry;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import br.ufsc.bridge.res.service.ResSoapMessageBuilder;
import br.ufsc.bridge.res.service.dto.registry.AdhocQueryResponseXPath;
import br.ufsc.bridge.res.service.dto.registry.RegistryFilter;
import br.ufsc.bridge.res.service.dto.registry.RegistryItem;
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.service.registry.RegistryService;
import br.ufsc.bridge.res.service.registry.parse.RegistryResponseParser;
import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.soap.http.SoapCredential;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;

public class TestRegistryService {

	String PATH_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/registry/";

	@Test
	public void testXmlGetRegistriesRef() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestXmlRegistriesRef.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		ResSoapMessageBuilder messageBuilder = new ResSoapMessageBuilder(new SoapCredential("123", "123"),
				"https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RegistryP",
				"urn:ihe:iti:2007:RegistryStoredQuery");
		RegistryService registryService = new RegistryService(new SoapCredential("123", "123"), null);

		RegistryFilter filter = new RegistryFilter();
		filter.setPatientId("898004405760294^^^&2.16.840.1.113883.13.236&ISO");

		AdhocQueryRequest adhocQueryRequest = registryService.buildRequest(filter, "ObjectRef");

		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""),
				IOUtils.toString(messageBuilder.createMessage(adhocQueryRequest)));
	}

	@Test
	public void testRegistryResponseRef() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestRegistryResponseRef.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		RegistryService registryService = new RegistryService(new SoapCredential("123", "123"), null);

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resourceAsStream);
		AdhocQueryResponseXPath queryResponse = new AdhocQueryResponseXPath(document);

		RegistryResponse<String> registryResponse = registryService.getRegistryResponse(queryResponse);

		Assert.assertEquals(true, queryResponse.isSuccess());
		Assert.assertEquals(this.getItemsRegistryResponse(), registryResponse.getItems());
	}

	@Test
	public void testRegistryResponseRefEmpty() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestRegistryResponseRefEmpty.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		RegistryService registryService = new RegistryService(new SoapCredential("123", "123"), null);

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resourceAsStream);
		AdhocQueryResponseXPath queryResponse = new AdhocQueryResponseXPath(document);

		RegistryResponse<String> registryResponse = registryService.getRegistryResponse(queryResponse);

		Assert.assertEquals(true, queryResponse.isSuccess());
		Assert.assertEquals(true, registryResponse.getItems().isEmpty());
	}

	@Test
	public void testXmlGetRegistriesHeader() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestXmlGetRegistriesHeader.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		ResSoapMessageBuilder messageBuilder = new ResSoapMessageBuilder(new SoapCredential("123", "123"),
				"https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RegistryP",
				"urn:ihe:iti:2007:RegistryStoredQuery");
		RegistryService registryService = new RegistryService(new SoapCredential("123", "123"), null);

		RegistryFilter filter = new RegistryFilter();
		filter.setEntryUUIDs(this.getItemsRegistryResponse());

		AdhocQueryRequest adhocQueryRequest = registryService.buildRequest(filter, "LeafClass");

		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""),
				IOUtils.toString(messageBuilder.createMessage(adhocQueryRequest)));

	}

	@Test
	public void testRegistryResponseHeaderParserEmpty() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestRegistryResponseHeaderParserEmpty.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resourceAsStream);
		AdhocQueryResponseXPath queryResponse = new AdhocQueryResponseXPath(document);

		RegistryResponse<RegistryItem> registryResponse = RegistryResponseParser.parse(queryResponse);

		Assert.assertEquals(true, queryResponse.isSuccess());
		Assert.assertEquals(true, registryResponse.getItems().isEmpty());
	}

	@Test
	public void testRegistryResponseHeaderParser() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestRegistryResponseHeaderParser.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resourceAsStream);
		AdhocQueryResponseXPath queryResponse = new AdhocQueryResponseXPath(document);

		RegistryResponse<RegistryItem> registryResponse = RegistryResponseParser.parse(queryResponse);

		Assert.assertEquals(true, queryResponse.isSuccess());
		Assert.assertEquals(this.getRegistryResponseHeaderParserExpected().getItems(), registryResponse.getItems());
	}

	@Test
	public void testXmlAllFilter() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestXmlAllFilter.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		ResSoapMessageBuilder messageBuilder = new ResSoapMessageBuilder(new SoapCredential("123", "123"),
				"https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RegistryP",
				"urn:ihe:iti:2007:RegistryStoredQuery");
		RegistryService registryService = new RegistryService(new SoapCredential("123", "123"), null);

		RegistryFilter filter = new RegistryFilter();
		filter.setDataInicial(RDateUtil.isoXDSbToDate("20141202100000"));
		filter.setDataFim(RDateUtil.isoXDSbToDate("20161202100000"));
		filter.setEntryUUIDs(this.getItemsRegistryResponse());
		filter.setPatientId("898004405760294^^^&2.16.840.1.113883.13.236&ISO");

		AdhocQueryRequest adhocQueryRequest = registryService.buildRequest(filter, "LeafClass");

		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""),
				IOUtils.toString(messageBuilder.createMessage(adhocQueryRequest)));
	}

	private RegistryResponse<RegistryItem> getRegistryResponseHeaderParserExpected() {
		List<RegistryItem> documents = new ArrayList<>();
		RegistryItem item = new RegistryItem();
		item.setRepositoryUniqueId("2.16.840.1.113883.3.711.2.1.4.5.11601");
		item.setDocumentUniqueId("1.42.20130403134532.123.1480701062655.2");
		item.setServiceStartTime(RDateUtil.isoXDSbToDate("20161202100000"));
		item.setCbo("1224");
		item.setCnsProfissional("970675180580008");
		item.setNomeProfissional(null);
		item.setNomeUnidadeSaude("UBSF Uberlândia");
		item.setCnesUnidadeSaude("2000180");
		item.setRepositoryURL("https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RepositoryPS");
		documents.add(item);

		item = new RegistryItem();
		item.setRepositoryUniqueId("2.16.840.1.113883.3.711.2.1.4.5.11602");
		item.setDocumentUniqueId("1.42.20130403134532.123.1478642031821.42454");
		item.setServiceStartTime(RDateUtil.isoXDSbToDate("20161202200000"));
		item.setCbo("1225");
		item.setCnsProfissional("7093454095409");
		item.setNomeProfissional(null);
		item.setNomeUnidadeSaude("UBS Santa Rita");
		item.setCnesUnidadeSaude("2000181");
		item.setRepositoryURL("https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RepositoryPA");
		documents.add(item);

		return new RegistryResponse<>(documents);
	}

	private List<String> getItemsRegistryResponse() {
		List<String> items = new LinkedList<>();
		items.add("urn:uuid:38eef1c3-28c9-4467-8470-a550355ccc2f");
		items.add("urn:uuid:69223bc9-99b2-419f-957d-abbcb977ce01");
		items.add("urn:uuid:9b017ba6-a31b-4b4b-844c-9d7643b9f5ba");
		return items;
	}
}
