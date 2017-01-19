package br.ufsc.bridge.res.registry;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import br.ufsc.bridge.res.service.dto.header.Credential;
import br.ufsc.bridge.res.service.dto.header.RegistryHeader;
import br.ufsc.bridge.res.service.dto.registry.AdhocQueryResponseXPath;
import br.ufsc.bridge.res.service.dto.registry.RegistryFilter;
import br.ufsc.bridge.res.service.dto.registry.RegistryItem;
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.service.registry.RegistryService;
import br.ufsc.bridge.res.service.registry.parse.RegistryResponseParser;
import br.ufsc.bridge.res.util.RDateUtil;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;

public class TestRegistryService {

	String PATH_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/registry/";

	@Test
	public void testXmlGetRegistriesRef() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestXmlRegistriesRef.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		RegistryHeader registryHeader = new RegistryHeader(new Credential("123", "123"));
		RegistryService registryService = new RegistryService(new Credential("123", "123"));

		RegistryFilter filter = new RegistryFilter();
		filter.setCnsCidadao("898004405760294");

		AdhocQueryRequest adhocQueryRequest = registryService.buildRequest(filter, "ObjectRef");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		registryHeader.create(adhocQueryRequest).writeTo(outputStream);
		String xml = new String(outputStream.toByteArray(), "UTF-8");

		System.out.println(xml);
		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""), xml);
	}

	@Test
	public void testRegistryResponseRef() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestRegistryResponseRef.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		RegistryService registryService = new RegistryService(new Credential("123", "123"));

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
		RegistryService registryService = new RegistryService(new Credential("123", "123"));

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

		RegistryHeader registryHeader = new RegistryHeader(new Credential("123", "123"));
		RegistryService registryService = new RegistryService(new Credential("123", "123"));

		RegistryFilter filter = new RegistryFilter();
		filter.setEntryUUIDs(this.getItemsRegistryResponse());

		AdhocQueryRequest adhocQueryRequest = registryService.buildRequest(filter, "LeafClass");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		registryHeader.create(adhocQueryRequest).writeTo(outputStream);
		String xml = new String(outputStream.toByteArray(), "UTF-8");

		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""), xml);

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
		Assert.assertEquals(this.getRegistryResponseHeaderParserExpected(), registryResponse);
	}

	private RegistryResponse<RegistryItem> getRegistryResponseHeaderParserExpected() {
		List<RegistryItem> documents = new LinkedList<>();
		RegistryItem item = new RegistryItem();
		item.setRepositoryUniqueId("2.16.840.1.113883.3.711.2.1.4.5.11601");
		item.setDocumentUniqueId("urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab");
		item.setServiceStartTime(RDateUtil.isoXDSbToDate("200412230800"));
		item.setCbo(this.PATH_TEST_RESOURCE);
		item.setCnsProfissional(this.PATH_TEST_RESOURCE);
		item.setNomeProfissional(this.PATH_TEST_RESOURCE);
		item.setNomeUnidadeSaude(this.PATH_TEST_RESOURCE);
		item.setCnesUnidadeSaude(this.PATH_TEST_RESOURCE);
		item.setRepositoryURL(this.PATH_TEST_RESOURCE);
		documents.add(item);

		item = new RegistryItem();
		item.setRepositoryUniqueId("2.16.840.1.113883.3.711.2.1.4.5.11601");
		item.setDocumentUniqueId(this.PATH_TEST_RESOURCE);
		item.setServiceStartTime(null);
		item.setCbo(this.PATH_TEST_RESOURCE);
		item.setCnsProfissional(this.PATH_TEST_RESOURCE);
		item.setNomeProfissional(this.PATH_TEST_RESOURCE);
		item.setNomeUnidadeSaude(this.PATH_TEST_RESOURCE);
		item.setCnesUnidadeSaude(this.PATH_TEST_RESOURCE);
		item.setRepositoryURL(this.PATH_TEST_RESOURCE);
		documents.add(item);

		item = new RegistryItem();
		item.setRepositoryUniqueId("2.16.840.1.113883.3.711.2.1.4.5.11601");
		item.setDocumentUniqueId(this.PATH_TEST_RESOURCE);
		item.setServiceStartTime(null);
		item.setCbo(this.PATH_TEST_RESOURCE);
		item.setCnsProfissional(this.PATH_TEST_RESOURCE);
		item.setNomeProfissional(this.PATH_TEST_RESOURCE);
		item.setNomeUnidadeSaude(this.PATH_TEST_RESOURCE);
		item.setCnesUnidadeSaude(this.PATH_TEST_RESOURCE);
		item.setRepositoryURL(this.PATH_TEST_RESOURCE);
		documents.add(item);
		return new RegistryResponse<>(documents);
	}

	private List<String> getItemsRegistryResponse() {
		List<String> items = new LinkedList<>();
		items.add("urn:uuid:d9e0169f-5bd9-4e77-abff-c9d356b29136");
		items.add("urn:uuid:db36ce52-0878-43d7-8fda-e7381aa9e758");
		items.add("urn:uuid:58ecea05-3fdc-40e7-bf66-8364df3211f2");

		// items.add("urn:uuid:db36ce52-0878-43d7-8fda-e7381aa9e758");
		// items.add("urn:uuid:58ecea05-3fdc-40e7-bf66-8364df3211f2");
		// items.add("urn:uuid:b088f094-f6c3-48e6-89de-c4114b4ce8db");
		// items.add("urn:uuid:6daaecec-1dfa-4ec7-9bae-fb2e9fea8a4f");
		// items.add("urn:uuid:21f34b2e-0c75-457c-919f-bff660322670");
		// items.add("urn:uuid:eec12fa0-5baf-4ad4-b642-ee014534b17b");
		// items.add("urn:uuid:ca434afa-8356-447c-a7fe-e5f7ec32cea5");
		// items.add("urn:uuid:056ce0bb-94c0-4ac7-82b6-8e629883b934");
		// items.add("urn:uuid:9374d6f8-32f4-444b-b7f2-e80ed85d4be7");
		// items.add("urn:uuid:f4547d62-7a22-4d66-bf04-b1025a637bde");
		// items.add("urn:uuid:8d337bdd-ce7d-4c3a-bd7b-d7b497fe0c0a");
		// items.add("urn:uuid:ba0f0ee0-3b03-47c4-b1ed-a5605cb32d40");
		// items.add("urn:uuid:9602e10a-fe25-4871-a79e-e2a3a9c557c1");
		// items.add("urn:uuid:38012795-848d-4ae3-b3fb-f7c77a34a60c");
		// items.add("urn:uuid:dcddce06-6a0f-4949-a7a4-b7bb2a116d1c");
		// items.add("urn:uuid:41d39d86-0acd-4650-8f4d-e5d9247daa24");
		// items.add("urn:uuid:c8d7396a-8e65-40ac-b0a1-f4d05cf0d342");
		// items.add("urn:uuid:c33b8855-82ae-4c64-9e7d-dc4006838a75");
		// items.add("urn:uuid:382136e0-b14c-4e1d-8ab7-f82acb6a91f8");
		// items.add("urn:uuid:d8845e08-5577-4941-8f53-a1bcc7f51cd0");
		// items.add("urn:uuid:16bfe5df-23e4-4915-8607-c7a4e807c3b0");
		// items.add("urn:uuid:b48e7918-2811-4ab1-b6e5-e88d668e38df");
		// items.add("urn:uuid:12f574b7-2c48-4515-a196-f15e54960cdf");
		// items.add("urn:uuid:527370c9-b206-4147-933a-e448793e7dc9");
		// items.add("urn:uuid:328b37c5-4b97-4058-9957-f5c42f43eb9f");
		// items.add("urn:uuid:a0fc5a44-d7f9-4123-a056-d9e2eebe4904");
		// items.add("urn:uuid:183c8e70-8cdf-4289-be61-b88009824b54");
		// items.add("urn:uuid:f60ed5db-e8cb-467b-9ba0-cf13e500c5d9");
		// items.add("urn:uuid:fbaf23d0-37dd-4657-b682-91d72432d94d");
		// items.add("urn:uuid:b10b9bef-3911-4489-a61c-ed9269043e30");
		// items.add("urn:uuid:f3b0d481-425c-4442-893e-e8a8812bc0cd");
		// items.add("urn:uuid:09774307-5402-45b4-8908-d089dd18f978");
		// items.add("urn:uuid:d5408114-7c8f-410b-93f8-f60599057026");
		// items.add("urn:uuid:b9bc271e-953a-403e-9ece-d5515e3aedef");
		// items.add("urn:uuid:dab971b3-e813-4baf-bf2a-b74abf6680c0");
		// items.add("urn:uuid:752c811d-cb9b-4059-aa28-fb52294ab766");
		// items.add("urn:uuid:b0fe4150-75ea-4eb8-a1e7-9d28a37efa61");
		// items.add("urn:uuid:ed8484f4-3428-4e68-93f4-a463965e250c");
		// items.add("urn:uuid:7bdcba34-6146-423b-a66b-fa1d57b7304a");
		// items.add("urn:uuid:662fa7d9-0081-43bf-aba9-91cd4e61d623");
		// items.add("urn:uuid:38eef1c3-28c9-4467-8470-a550355ccc2f");
		// items.add("urn:uuid:69223bc9-99b2-419f-957d-abbcb977ce01");
		// items.add("urn:uuid:9b017ba6-a31b-4b4b-844c-9d7643b9f5ba");
		// items.add("urn:uuid:3d2ad2f6-aae2-488d-b781-9f00d9e07585");
		// items.add("urn:uuid:de820a17-e285-4917-b3b7-822b659433ea");
		// items.add("urn:uuid:f1e47c19-f856-4ac4-ab24-bd00feb1f277");
		// items.add("urn:uuid:3f7bc4eb-63aa-4c64-989c-b1cf916f2086");
		// items.add("urn:uuid:73abf71f-5602-45ef-8e0f-94b5c3218097");
		return items;
	}
}
