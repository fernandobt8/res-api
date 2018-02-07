package br.ufsc.bridge.res;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBException;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

import br.ufsc.bridge.res.dab.dto.ResABResumoConsulta;
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
import br.ufsc.bridge.soap.http.SoapCredential;

@Slf4j
public class SOAPClientSAAJ {

	private static final String registry_url = "http://23.236.48.233:8280/services/DocumentRegistry";
	private static final String repository_url = "http://23.236.48.233:8280/services/DocumentRepository";

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

			// testeXml();

			// testeXml2();

			save(credential);

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
		documentDTO.setDocumentId("1.42.20130403134532.123.1478642031821.4633");
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("doc1.xml").openStream();
		documentDTO.setDocument(IOUtils.toString(resourceAsStream));
		documentDTO.setUrl(repository_url);

		registerDTO.getDocuments().add(documentDTO);
		repositoryService.save(registerDTO);
	}

	@SuppressWarnings("unused")
	private static void testeXml2() throws Exception {
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("doc1.xml").openStream();

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

		RepositoryFilter repositoryFilter = new RepositoryFilter();
		repositoryFilter.getDocuments().add(new DocumentItemFilter(
				repository_url,
				"2.16.840.1.113883.3.711.2.1.4.5.11601",
				"1.42.20130403134532.123.1475256277528.2"));

		RepositoryResponseDTO documents = repositoryService.getDocuments(repositoryFilter);
		for (DocumentItem item : documents.getDocuments()) {
			System.out.println(item.getDocument());
		}
	}

	@SuppressWarnings("unused")
	private static RegistryResponse<RegistryItem> registry(SoapCredential credential) throws Exception {
		RegistryService registryService = new RegistryService(credential, registry_url);

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