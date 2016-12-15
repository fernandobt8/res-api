package br.ufsc.bridge.res;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.domain.ResABTipoProblemaDiagnostico;
import br.ufsc.bridge.res.dab.domain.ResABTurnoEnum;
import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;
import br.ufsc.bridge.res.service.dto.header.Credential;
import br.ufsc.bridge.res.service.dto.registry.RegistryFilter;
import br.ufsc.bridge.res.service.dto.registry.RegistryItem;
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryResponseDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositoryResponseDTO.DocumentItem;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.service.registry.RegistryService;
import br.ufsc.bridge.res.service.repository.RepositoryService;

@Slf4j
public class SOAPClientSAAJ {

	/**
	 * Starting point for the SAAJ - SOAP Client Testing
	 *
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void main(String args[]) {
		Credential credential = new Credential("CADSUS.RES", "C@ASD213123adsas6dasdas7das6");

		try {
			// repository(credential);

			// registry(credential);

			// testeXml();

			testeXml2();

			// save(credential);

		} catch (Exception e) {
			log.error("", e);
		}
	}

	private static void save(Credential credential) throws Exception {

		RepositoryService repositoryService = new RepositoryService(credential);

		RepositorySaveDTO registerDTO = new RepositorySaveDTO();
		registerDTO.setCboProfissional("225265");
		registerDTO.setCnesUnidadeSaude("5444429");
		registerDTO.setNomeUnidadeSaude("UBS Teste");
		registerDTO.setCnsProfissional("898000127489128");
		registerDTO.setNomeProfissional("Gabriel Holdener Geraldeli");

		registerDTO.setCnsPaciente("898004405760294");
		registerDTO.setIdInstalacao("1.3.6.1.4.1.21367.2010.1.2");
		registerDTO.setSubmissionSetId("1.42.20130403134532.123.1478642031821.45");
		registerDTO.setDocuments(new ArrayList<RepositorySaveDocumentDTO>());

		RepositorySaveDocumentDTO documentDTO = new RepositorySaveDocumentDTO();
		documentDTO.setCboProfissional("225265");
		documentDTO.setCnesUnidadeSaude("5444429");
		documentDTO.setNomeUnidadeSaude("UBS Teste");
		documentDTO.setDescricaoCboProfissional("Enfermeiro da estratégia de saúde da família");
		documentDTO.setCnsProfissional("898000127489128");
		documentDTO.setNomeProfissional("Gabriel Holdener Geraldeli");

		documentDTO.setCnsPaciente("898004405760294");
		documentDTO.setDataInicioAtendimento(new Date());
		documentDTO.setDataFimAtendimento(new Date());
		documentDTO.setDocumentId("1.42.20130403134532.123.1478642031821.45");
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("doc2.xml").openStream();
		// documentDTO.setDocument(IOUtils.toString(resourceAsStream));

		registerDTO.getDocuments().add(documentDTO);
		repositoryService.save(registerDTO);
	}

	private static void testeXml2() throws Exception {
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("doc1.xml").openStream();

		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
			// aqui criar um arquivo XML contendo os dados do atendimento clinico e fazer assert equals com o DTO
			System.out.println(resumoConsulta.getXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeConvertXmlToResumoConsulta() throws Exception {
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("docCompleto.xml").openStream();
		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
			Assert.assertEquals(ResABTipoAtendimentoEnum.CONSULTA_AGENDADA, resumoConsulta.getTipoAtendimento());
			Assert.assertEquals("4254160", resumoConsulta.getCnes());
			Assert.assertEquals("703006821796679", resumoConsulta.getProfissionais().get(0).getCns());
			Assert.assertEquals("2235-65", resumoConsulta.getProfissionais().get(0).getCbo());
			Assert.assertEquals("703006821796679", resumoConsulta.getProfissionais().get(0).getCns());
			Assert.assertEquals("2235-65", resumoConsulta.getProfissionais().get(0).getCbo());
			Assert.assertEquals("707000801749036", resumoConsulta.getProfissionais().get(1).getCns());
			Assert.assertEquals("2236-05", resumoConsulta.getProfissionais().get(1).getCbo());
			Assert.assertEquals("898001153249911", resumoConsulta.getProfissionais().get(2).getCns());
			Assert.assertEquals("2235-64", resumoConsulta.getProfissionais().get(2).getCbo());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			String dateInString = "2016-05-23T15:00:00.000-03:00";
			Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getDataAtendimento());
			Assert.assertEquals(ResABTurnoEnum.NOITE, resumoConsulta.getTurno());
			Assert.assertEquals("994.250", resumoConsulta.getPeso());
			Assert.assertEquals("987.55", resumoConsulta.getAltura());
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			dateInString = "2016-03-16";
			Assert.assertEquals(formatter.parse(dateInString), resumoConsulta.getDum());
			Assert.assertEquals("P9W5D", resumoConsulta.getIdadeGestacional());
			Assert.assertEquals("2", resumoConsulta.getGestasPrevias());
			Assert.assertEquals("1", resumoConsulta.getPartos());
			Assert.assertEquals("A920", resumoConsulta.getProblemasDiagnosticos().get(0).getCodigo());
			Assert.assertEquals("Febre de Chikungunya", resumoConsulta.getProblemasDiagnosticos().get(0).getDescricao());
			Assert.assertEquals(ResABTipoProblemaDiagnostico.CID10, resumoConsulta.getProblemasDiagnosticos().get(0).getTipo());
			// Assert.assertEquals("", resumoConsulta.getProblemasDiagnosticos().get(1).getCodigo());
			// Assert.assertEquals("", resumoConsulta.getProblemasDiagnosticos().get(1).getDescricao());
			// Assert.assertEquals("", resumoConsulta.getProblemasDiagnosticos().get(1).getTipo());
			// Assert.assertEquals("", resumoConsulta.getProblemasDiagnosticos().get(2).getCodigo());
			// Assert.assertEquals("", resumoConsulta.getProblemasDiagnosticos().get(2).getDescricao());
			// Assert.assertEquals("", resumoConsulta.getProblemasDiagnosticos().get(2).getTipo());

			resumoConsulta.getAlergias();
			resumoConsulta.getCondutas();
			resumoConsulta.getEncaminhamentos();
			resumoConsulta.getIne();
			resumoConsulta.getMedicamentos();

			resumoConsulta.getProcedimentos();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeConvertXmlToResumoConsultaMinimos() throws Exception {
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("docMinimoInterno.xml").openStream();
		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
			Assert.assertNull("Turno", resumoConsulta.getTurno());
			Assert.assertNull("CNES", resumoConsulta.getCnes());
			Assert.assertNull("CNS", resumoConsulta.getProfissionais().get(0).getCns());
			Assert.assertNull("CBO", resumoConsulta.getProfissionais().get(0).getCbo());
			Assert.assertNull("Data de Atendimento", resumoConsulta.getDataAtendimento());
			Assert.assertNull("Alergias", resumoConsulta.getAlergias());
			Assert.assertNull("Altura", resumoConsulta.getAltura());
			Assert.assertNull("Condutas", resumoConsulta.getCondutas());
			Assert.assertNull("DUM", resumoConsulta.getDum());
			Assert.assertNull("Atendimento", resumoConsulta.getTipoAtendimento());
			Assert.assertNull("Encaminhamentos", resumoConsulta.getEncaminhamentos());
			Assert.assertNull("Gestas Previas", resumoConsulta.getGestasPrevias());
			Assert.assertNull("Idade Gestacional", resumoConsulta.getIdadeGestacional());
			Assert.assertNull("INE", resumoConsulta.getIne());
			Assert.assertNull("Partos", resumoConsulta.getPartos());
			Assert.assertNull("Medicamentos", resumoConsulta.getMedicamentos());
			Assert.assertNull("Peso", resumoConsulta.getPeso());
			Assert.assertNull("Problemas", resumoConsulta.getProblemasDiagnosticos());
			Assert.assertNull("Procedimentos", resumoConsulta.getProcedimentos());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeConvertXmlToResumoConsultaMinimosGeral() throws Exception {
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("docMinimoGeral.xml").openStream();
		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeConvertXmlToResumoConsultaMinimosExt() throws Exception {
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("docValoresMiniExt.xml").openStream();
		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void repository(Credential credential) throws Exception {
		RepositoryService repositoryService = new RepositoryService(credential);

		RepositoryFilter repositoryFilter = new RepositoryFilter();
		repositoryFilter.setRepositoryURL("https://servicoshm.saude.gov.br/EHR-UNB/ProxyService/RepositoryPS");
		repositoryFilter.getDocuments().add(new DocumentItemFilter("2.16.840.1.113883.3.711.2.1.4.5.11601", "1.42.20130403134532.123.1475256277528.2"));

		RepositoryResponseDTO documents = repositoryService.getDocuments(repositoryFilter);
		for (DocumentItem item : documents.getDocuments()) {
			System.out.println(item.getDocument());
		}
	}

	private static RegistryResponse registry(Credential credential) throws Exception {
		RegistryService registryService = new RegistryService(credential);

		RegistryFilter registryFilter = new RegistryFilter();
		registryFilter.setCnsCidadao("898004405760294");

		RegistryResponse<String> registries = registryService.getRegistriesRef(registryFilter);
		for (String uuid : registries.getItems()) {
			System.out.println(uuid);
		}

		registryFilter = new RegistryFilter();
		registryFilter.setEntryUUIDs(registries.getItems());

		RegistryResponse<RegistryItem> registries2 = registryService.getRegistriesHeader(registryFilter);
		for (RegistryItem item : registries2.getItems()) {
			System.out.println(item.getRepositoryUniqueId());
			System.out.println(item.getDocumentUniqueId());
			System.out.println("unidade: " + item.getCnesUnidadeSaude());
			System.out.println("prof: " + item.getCnsProfissional());
			System.out.println("cbo: " + item.getCbo());
			System.out.println("data: " + item.getServiceStartTime());
			System.out.println();
		}

		return registries2;
	}
}