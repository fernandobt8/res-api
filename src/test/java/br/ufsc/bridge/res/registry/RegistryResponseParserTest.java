package br.ufsc.bridge.res.registry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import oasis.names.tc.ebxml_regrep.xsd.query._3.AdhocQueryResponse;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ClassificationType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExternalIdentifierType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ExtrinsicObjectType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.RegistryObjectListType;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.SlotType1;
import oasis.names.tc.ebxml_regrep.xsd.rim._3.ValueListType;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.res.service.dto.registry.RegistryItem;
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.service.registry.parse.RegistryResponseParser;

public class RegistryResponseParserTest {

	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");

	@Test
	public void testeDoisDocumentosValidos() {

		RegistryItem documentoEsperadoUm = this.createDocumentoValido();
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoEsperadoUm);

		RegistryItem documentoEsperadoDois = this.createDocumentoValido();
		documentoEsperadoDois.setNomeProfissional(null);
		documentoEsperadoDois.setNomeUnidadeSaude(null);
		response.getRegistryObjectList().getIdentifiable().add(this.buildDocumento(documentoEsperadoDois));

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 2);

		RegistryItem documentoAtual = registryResponseEsperada.getItems().get(0);
		Assert.assertEquals(documentoEsperadoUm.getRepositoryUniqueId(), documentoAtual.getRepositoryUniqueId());
		Assert.assertEquals(documentoEsperadoUm.getDocumentUniqueId(), documentoAtual.getDocumentUniqueId());
		Assert.assertEquals(this.dateFormat.format(documentoEsperadoUm.getServiceStartTime()), this.dateFormat.format(documentoAtual.getServiceStartTime()));
		Assert.assertEquals(documentoEsperadoUm.getCnesUnidadeSaude(), documentoAtual.getCnesUnidadeSaude());
		Assert.assertEquals(documentoEsperadoUm.getNomeUnidadeSaude(), documentoAtual.getNomeUnidadeSaude());
		Assert.assertEquals(documentoEsperadoUm.getCnsProfissional(), documentoAtual.getCnsProfissional());
		Assert.assertEquals(documentoEsperadoUm.getNomeProfissional(), documentoAtual.getNomeProfissional());
		Assert.assertEquals(documentoEsperadoUm.getCbo(), documentoAtual.getCbo());

		Assert.assertNull(registryResponseEsperada.getItems().get(1).getNomeProfissional());
		Assert.assertNull(registryResponseEsperada.getItems().get(1).getNomeUnidadeSaude());

	}

	@Test
	public void testeUmDocumentoValidoUmInvalido() {

		RegistryItem documentoEsperado = this.createDocumentoValido();
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoEsperado);
		response.getRegistryObjectList().getIdentifiable().add(null);

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 1);
	}

	@Test
	public void testeValorDosCamposNulosRetornarNenhumDocumento() {
		RegistryItem documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setRepositoryUniqueId(null);
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);

		documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setDocumentUniqueId(null);
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);

		documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setServiceStartTime(null);
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);

		documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setCnesUnidadeSaude(null);
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);

		documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setCnsProfissional(null);
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);

		documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setCbo(null);
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);
	}

	@Test
	public void testeValorDosCamposInvalidosRetornarNenhumDocumento() {
		RegistryItem documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setCnesUnidadeSaude("Nome unidade saúde");
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);

		documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setCnsProfissional("Nome do profissional");
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);

		documentoEsperado = this.createDocumentoValido();
		documentoEsperado.setCbo("Nome do cbo");
		this.verificaSeParseRetornaListaDocumentosVazia(documentoEsperado);
	}

	@Test
	public void test_AdhocQueryResponse_RegistryObjectList_Null_RetornarNenhumDocumento() {
		AdhocQueryResponse response = new AdhocQueryResponse();
		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 0);
	}

	@Test
	public void test_AdhocQueryResponse_RegistryObjectList_com_identifiable_JAXBElement_null_RetornarNenhumDocumento() {
		RegistryItem documentoValido = this.createDocumentoValido();
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoValido);
		response.getRegistryObjectList().getIdentifiable().clear();
		response.getRegistryObjectList().getIdentifiable().add(null);

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 0);
	}

	@Test
	public void testValorDoJAXBElementNullRetornarNenhumDocumento() {
		RegistryItem documentoValido = this.createDocumentoValido();
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoValido);
		response.getRegistryObjectList().getIdentifiable().get(0).setValue(null);

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 0);
	}

	@Test
	public void teste_SlotType1_ValueList_Null_RetornarNenhumDocumento() {
		RegistryItem documentoValido = this.createDocumentoValido();
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoValido);
		response.getRegistryObjectList().getIdentifiable().get(0).getValue().getSlot().get(0).setValueList(null);

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 0);
	}

	@Test
	public void teste_SlotType1_ValueList_com_Value_vazio_RetornarNenhumDocumento() {
		RegistryItem documentoValido = this.createDocumentoValido();
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoValido);
		response.getRegistryObjectList().getIdentifiable().get(0).getValue().getSlot().get(0).getValueList().getValue().clear();

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 0);
	}

	@Test
	public void teste_SlotType1_ValueList_com_itemDeValue_null_RetornarNenhumDocumento() {
		RegistryItem documentoValido = this.createDocumentoValido();
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoValido);
		response.getRegistryObjectList().getIdentifiable().get(0).getValue().getSlot().get(0).getValueList().getValue().clear();
		response.getRegistryObjectList().getIdentifiable().get(0).getValue().getSlot().get(0).getValueList().getValue().add(null);

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 0);
	}

	@Test
	public void teste_SlotType1_com_Name_null_RetornarNenhumDocumento() {
		RegistryItem documentoValido = this.createDocumentoValido();
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoValido);
		response.getRegistryObjectList().getIdentifiable().get(0).getValue().getSlot().get(0).setName(null);

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 0);
	}

	private void verificaSeParseRetornaListaDocumentosVazia(RegistryItem documentoEsperado) {
		AdhocQueryResponse response = this.buildAdhocQueryResponse(documentoEsperado);

		RegistryResponse<RegistryItem> registryResponseEsperada = RegistryResponseParser.parse(response);
		Assert.assertTrue(registryResponseEsperada.getItems().size() == 0);
	}

	private RegistryItem createDocumentoValido() {
		RegistryItem documentoEsperado = new RegistryItem();
		documentoEsperado.setRepositoryUniqueId("2.16.840.1.113883.3.711.2.1.4.5.11601");
		documentoEsperado.setDocumentUniqueId("1.42.20130403134532.123.1432167738");
		documentoEsperado.setServiceStartTime(new Date());
		documentoEsperado.setCnsProfissional("000000000000000");
		documentoEsperado.setNomeProfissional("João da Silva");
		documentoEsperado.setCnesUnidadeSaude("0123456");
		documentoEsperado.setNomeUnidadeSaude("UBS Florianópolis");
		documentoEsperado.setCbo("654321");
		return documentoEsperado;
	}

	private AdhocQueryResponse buildAdhocQueryResponse(RegistryItem documentoItem) {
		AdhocQueryResponse response = new AdhocQueryResponse();
		JAXBElement<ExtrinsicObjectType> documento = this.buildDocumento(documentoItem);
		RegistryObjectListType registryObjectListType = new RegistryObjectListType();
		registryObjectListType.getIdentifiable().add(documento);
		response.setRegistryObjectList(registryObjectListType);
		return response;
	}

	private JAXBElement<ExtrinsicObjectType> buildDocumento(RegistryItem documentoItem) {
		ExtrinsicObjectType extrinsicObjectType = new ExtrinsicObjectType();
		extrinsicObjectType.getSlot().add(this.createSlot("repositoryUniqueId", documentoItem.getRepositoryUniqueId()));
		extrinsicObjectType.getSlot().add(
				this.createSlot("serviceStartTime", documentoItem.getServiceStartTime() != null ? this.dateFormat.format(documentoItem.getServiceStartTime()) : null));
		extrinsicObjectType.getExternalIdentifier().add(this.createExternalIdentifier(documentoItem.getDocumentUniqueId()));
		extrinsicObjectType.getClassification().add(this.createClassification(documentoItem));
		JAXBElement<ExtrinsicObjectType> documento = new JAXBElement<>(new QName(""), ExtrinsicObjectType.class, null, extrinsicObjectType);
		return documento;
	}

	private SlotType1 createSlot(String name, String value) {
		SlotType1 slot = new SlotType1();
		slot.setName(name);
		ValueListType valueList = new ValueListType();
		valueList.getValue().add(value);
		slot.setValueList(valueList);
		return slot;
	}

	private ExternalIdentifierType createExternalIdentifier(String value) {
		ExternalIdentifierType externalIdentifierType = new ExternalIdentifierType();
		externalIdentifierType.setIdentificationScheme("urn:uuid:2e82c1f6-a085-4c72-9da3-8640a32e42ab");
		externalIdentifierType.setValue(value);
		return externalIdentifierType;
	}

	private ClassificationType createClassification(RegistryItem documentoItem) {
		ClassificationType classificationType = new ClassificationType();
		classificationType.setClassificationScheme("urn:uuid:93606bcf-9494-43ec-9b4e-a7748d1a838d");

		List<SlotType1> slots = classificationType.getSlot();

		String authorInstitution = StringUtils.isNotBlank(documentoItem.getNomeUnidadeSaude()) ? documentoItem.getNomeUnidadeSaude() + "^" : "^";
		authorInstitution = authorInstitution + (StringUtils.isNotBlank(documentoItem.getCnesUnidadeSaude()) ? documentoItem.getCnesUnidadeSaude() : "");

		slots.add(this.createSlot("authorInstitution", authorInstitution));

		String authorPerson = StringUtils.isNotBlank(documentoItem.getCnsProfissional()) ? documentoItem.getCnsProfissional() + "^" : "^";
		authorPerson = authorPerson + (StringUtils.isNotBlank(documentoItem.getNomeProfissional()) ? documentoItem.getNomeProfissional() : "");

		slots.add(this.createSlot("authorPerson", authorPerson));
		slots.add(this.createSlot("authorSpecialty", documentoItem.getCbo() + "^"));
		return classificationType;
	}
}
