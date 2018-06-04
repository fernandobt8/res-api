package br.ufsc.bridge.res;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

import br.ufsc.bridge.res.dab.TipoDocumento;
import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;
import br.ufsc.bridge.res.service.dto.registry.RegistryFilter;
import br.ufsc.bridge.res.service.dto.registry.RegistryFilter.RegistryFilterBuilder;
import br.ufsc.bridge.res.service.dto.registry.RegistryItem;
import br.ufsc.bridge.res.service.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.service.dto.repository.RepositoryDocumentItem;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.service.registry.RegistryService;
import br.ufsc.bridge.res.service.repository.RepositoryService;
import br.ufsc.bridge.soap.http.SoapCredential;

@Slf4j
public class SOAPClientSAAJ {

	private static final String registry_url = "http://35.224.244.22:8280/services/DocumentRegistry";
	private static final String repository_url = "http://35.224.244.22:8280/services/DocumentRepository";

	static String cbo = "225130";
	static String cns = "992294125290005";
	static String cnes = "7592477";

	/**
	 * Starting point for the SAAJ - SOAP Client Testing
	 *
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void main(String args[]) {
		SoapCredential credential = new SoapCredential("CADSUS.RES", "C@ASD213123adsas6dasdas7das6");
		try {
			// repository(credential);

			// registry(credential);

			save(credential);

			// testeXml();

			// testeXml2();

		} catch (Exception e) {
			log.error("", e);
		}
	}

	private static void save(SoapCredential credential) throws Exception {

		RepositoryService repositoryService = new RepositoryService(credential);

		RepositorySaveDTO registerDTO = new RepositorySaveDTO();
		registerDTO.setCboProfissional("225265");
		registerDTO.setCnesUnidadeSaude("5444429");
		registerDTO.setNomeUnidadeSaude("UBS Teste");
		registerDTO.setCnsProfissional("898000127489128");
		registerDTO.setNomeProfissional("Gabriel Holdener Geraldeli");

		registerDTO.setCnsPaciente("898004405760294");
		registerDTO.setIdInstalacao("1.3.6.1.4.1.21367.2010.1.2");
		registerDTO.setSubmissionSetId("1.42.20130403134532.123.1478642031821.4635");
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
		documentDTO.setDocumentId("1.42.20130403134532.123.1478642031821.463322");
		documentDTO.setDocument(IOUtils.toString(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/CN4-CIT_doc-crianca.xml"))
				.replace("\n", "").replace("\r", "").replace("\t", ""));
		documentDTO.setTipoDocumento(TipoDocumento.ATENDIMENTO_CIT_2018);
		documentDTO.setUrl(repository_url);

		registerDTO.getDocuments().add(documentDTO);
		repositoryService.save(registerDTO);
	}

	@SuppressWarnings("unused")
	private static void testeXml2() throws Exception {
		InputStream resourceAsStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/CN4-CIT_doc-crianca.xml");

		try {
			ResABResumoConsulta resumoConsulta = new ResABResumoConsulta(IOUtils.toString(resourceAsStream));
			System.out.println(resumoConsulta.getXml());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void repository(SoapCredential credential) throws Exception {
		RepositoryService repositoryService = new RepositoryService(credential);

		RepositoryFilter repositoryFilter = new RepositoryFilter(cns, cbo, cnes);
		repositoryFilter.getDocuments().add(new DocumentItemFilter(
				repository_url,
				"1.3.6.1.4.1.21367.2010.1.2.1125",
				"1.42.20130403134532.123.1517918149401.2"));

		List<RepositoryDocumentItem> documents = repositoryService.getDocuments(repositoryFilter);
		for (RepositoryDocumentItem item : documents) {
			System.out.println(item.getDocument());
		}
	}

	@SuppressWarnings("unused")
	private static RegistryResponse<RegistryItem> registry(SoapCredential credential) throws Exception {
		RegistryService registryService = new RegistryService(credential, registry_url);

		RegistryFilterBuilder filterBuilder = RegistryFilter.builder()
				.cnsProfissional(cns)
				.cboProfissional(cbo)
				.cnesProfissional(cnes);

		RegistryResponse<String> registries = registryService.getRegistriesRef(filterBuilder.cnsCidadao("898004405760294").build());
		for (String uuid : registries.getItems()) {
			System.out.println(uuid);
		}
		System.out.println();

		RegistryResponse<RegistryItem> registries2 = registryService.getRegistriesHeader(filterBuilder.entryUUIDs(registries.getItems()).build());
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