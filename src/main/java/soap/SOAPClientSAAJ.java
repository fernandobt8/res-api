package soap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

import br.ufsc.bridge.res.dab.ResABEncontro;
import br.ufsc.bridge.res.dto.header.Credential;
import br.ufsc.bridge.res.dto.registry.RegistryFilter;
import br.ufsc.bridge.res.dto.registry.RegistryItem;
import br.ufsc.bridge.res.dto.registry.RegistryResponse;
import br.ufsc.bridge.res.dto.repository.RepositoryFilter;
import br.ufsc.bridge.res.dto.repository.RepositoryFilter.DocumentItemFilter;
import br.ufsc.bridge.res.dto.repository.RepositoryRegisterDTO;
import br.ufsc.bridge.res.dto.repository.RepositoryRegisterDocumentDTO;
import br.ufsc.bridge.res.dto.repository.RepositoryResponseDTO;
import br.ufsc.bridge.res.dto.repository.RepositoryResponseDTO.DocumentItem;
import br.ufsc.bridge.res.registry.RegistryService;
import br.ufsc.bridge.res.repository.RepositoryService;

public class SOAPClientSAAJ {

	/**
	 * Starting point for the SAAJ - SOAP Client Testing
	 *
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static void main(String args[]) throws Exception {
		Credential credential = new Credential("CADSUS.RES", "C@ASD213123adsas6dasdas7das6");

		// repository(credential);

		// registry(credential);

		// testeXml();

		save(credential);
	}

	private static void save(Credential credential) throws Exception {

		RepositoryService repositoryService = new RepositoryService(credential);

		RepositoryRegisterDTO registerDTO = new RepositoryRegisterDTO();
		registerDTO.setCboProfissional("225125");
		registerDTO.setCnesUnidadeSaude("5444429");
		registerDTO.setCnsProfissional("898000127489128");

		registerDTO.setCnsPaciente("898004405760294");
		registerDTO.setIdInstalacao("vai-birl");
		registerDTO.setSubmissionSetId("tomara-que-nao-duplique");
		registerDTO.setDocuments(new ArrayList<RepositoryRegisterDocumentDTO>());

		RepositoryRegisterDocumentDTO documentDTO = new RepositoryRegisterDocumentDTO();
		documentDTO.setCboProfissional("225125");
		documentDTO.setCnesUnidadeSaude("5444429");
		documentDTO.setCnsProfissional("898000127489128");

		documentDTO.setCnsPaciente("898004405760294");
		documentDTO.setDataInicioAtendimento(new Date());
		documentDTO.setDataFimAtendimento(new Date());
		documentDTO.setDocumentId("esse-nao-duplica-certeza");
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("ResumoConsultaAB1.xml").openStream();
		documentDTO.setDocument(IOUtils.toString(resourceAsStream));

		registerDTO.getDocuments().add(documentDTO);
		repositoryService.save(registerDTO);
	}

	private static void testeXml() throws IOException {
		InputStream resourceAsStream = SOAPClientSAAJ.class.getResource("ResumoConsultaAB1.xml").openStream();

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ResABEncontro.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ResABEncontro e = (ResABEncontro) jaxbUnmarshaller.unmarshal(resourceAsStream);

			e.getCaracterizacaoConsulta();
		} catch (JAXBException e1) {
			e1.printStackTrace();
		}
	}

	private static void repository(Credential credential) {
		RepositoryService repositoryService = new RepositoryService(credential);

		RepositoryFilter repositoryFilter = new RepositoryFilter();

		repositoryFilter.getDocuments().add(new DocumentItemFilter("2.16.840.1.113883.3.711.2.1.4.5.11601", "1.42.20130403134532.123.1475256277528.2"));

		RepositoryResponseDTO documents = repositoryService.getDocuments(repositoryFilter);
		for (DocumentItem item : documents.getDocuments()) {
			JAXBContext jaxbContext;
			try {
				jaxbContext = JAXBContext.newInstance(ResABEncontro.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				InputStream inputStream = IOUtils.toInputStream(item.getDocument());
				ResABEncontro e = (ResABEncontro) jaxbUnmarshaller.unmarshal(inputStream);

				System.out.println(item.getDocument());
			} catch (JAXBException e1) {
				e1.printStackTrace();
			}
		}
	}

	private static RegistryResponse registry(Credential credential) {
		RegistryService registryService = new RegistryService(credential);

		RegistryFilter registryFilter = new RegistryFilter();
		registryFilter.setCnsCidadao("898004405760294");

		RegistryResponse registries = registryService.getRegistries(registryFilter);
		for (RegistryItem item : registries.getItems()) {
			System.out.println(item.getRepositoryUniqueId());
			System.out.println(item.getDocumentUniqueId());
			System.out.println("unidade: " + item.getCnesUnidadeSaude());
			System.out.println("prof: " + item.getCnsProfissional());
			System.out.println("cbo: " + item.getCbo());
			System.out.println("data: " + item.getServiceStartTime());
			System.out.println();
		}

		return registries;
	}
}