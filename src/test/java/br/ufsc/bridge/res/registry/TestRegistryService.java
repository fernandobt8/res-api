package br.ufsc.bridge.res.registry;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.res.service.dto.header.Credential;
import br.ufsc.bridge.res.service.dto.header.RegistryHeader;
import br.ufsc.bridge.res.service.dto.registry.RegistryFilter;
import br.ufsc.bridge.res.service.registry.RegistryService;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryRequest;

public class TestRegistryService {

	String PATH_TEST_RESOURCE = System.getProperty("user.dir") + "/src/test/resources/registry/";

	@Test
	public void testGetRegistriesHeader() throws Exception {
		String pathFile = this.PATH_TEST_RESOURCE + "TestGetRegistriesHeader.xml";
		InputStream resourceAsStream = new FileInputStream(pathFile);

		RegistryHeader registryHeader = new RegistryHeader(new Credential("123", "123"));
		RegistryService registryService = new RegistryService(new Credential("123", "123"));

		RegistryFilter filter = new RegistryFilter();
		filter.setCnsCidadao("898004405760294");
		AdhocQueryRequest adhocQueryRequest = registryService.buildRequest(filter, "ObjectRef");

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		registryHeader.create(adhocQueryRequest).writeTo(outputStream);
		String string = new String(outputStream.toByteArray(), "UTF-8");
		System.out.println(string);
		Assert.assertEquals(IOUtils.toString(resourceAsStream).replace("\n", "").replace("\r", "").replace("\t", ""), string);
	}
}
