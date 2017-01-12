package br.ufsc.bridge.res.registry;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
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
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.service.registry.RegistryService;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;

public class TestRegistryService {

	String PATH_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/registry/";

	@Test
	public void testXmlGetRegistriesRef() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestXmlRegistriesHeader.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		RegistryHeader registryHeader = new RegistryHeader(new Credential("123", "123"));
		RegistryService registryService = new RegistryService(new Credential("123", "123"));

		RegistryFilter filter = new RegistryFilter();
		filter.setCnsCidadao("898004405760294");

		AdhocQueryRequest adhocQueryRequest = registryService.buildRequest(filter, "ObjectRef");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		registryHeader.create(adhocQueryRequest).writeTo(outputStream);
		String xml = new String(outputStream.toByteArray(), "UTF-8");

		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""), xml);
	}

	@Test
	public void testRegistryResponse() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestRegistryResponse.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);
		RegistryService registryService = new RegistryService(new Credential("123", "123"));

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resourceAsStream);
		AdhocQueryResponseXPath queryResponse = new AdhocQueryResponseXPath(document);

		RegistryResponse<String> registryResponse = registryService.getRegistryResponse(queryResponse);

		Assert.assertEquals(true, queryResponse.isSuccess());
		Assert.assertEquals(this.itemsExpectedRegistryResponse(), registryResponse.getItems());
	}

	private List<String> itemsExpectedRegistryResponse() {
		List<String> items = new ArrayList<>();
		items.add("urn:uuid:d9e0169f-5bd9-4e77-abff-c9d356b29136");
		items.add("urn:uuid:db36ce52-0878-43d7-8fda-e7381aa9e758");
		items.add("urn:uuid:58ecea05-3fdc-40e7-bf66-8364df3211f2");
		return items;
	}
}
