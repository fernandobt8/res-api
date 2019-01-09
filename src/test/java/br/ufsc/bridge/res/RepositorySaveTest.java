package br.ufsc.bridge.res;

import java.io.FileInputStream;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;

import br.ufsc.bridge.res.dab.TipoDocumento;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO;
import br.ufsc.bridge.res.service.dto.repository.RepositorySaveDocumentDTO.ClassificationDTO;
import br.ufsc.bridge.res.service.repository.RepositoryService;
import br.ufsc.bridge.soap.http.SoapCredential;

@Slf4j
public class RepositorySaveTest {
	// private static final String repository_url = "http://177.71.197.58:57772/csp/healthshare/hsbus/services/HS.IHE.XDSb.Repository.Services.cls";

	private static final String repository_url = "http://localhost:8080/webnar/sim/testuser1__rep/rep/prb";

	public static void main(String args[]) {
		SoapCredential credential = new SoapCredential("", "");
		try {
			int add = 19;
			RepositoryService repositoryService = new RepositoryService(credential);

			RepositorySaveDTO registerDTO = RepositorySaveDTO.builder()
					.cboProfissional("225265")
					.cnesUnidadeSaude("5444429")
					.nomeUnidadeSaude("UBS Teste")
					.cnsProfissional("898000127489128")
					.nomeProfissional("Gabriel Holdener Geraldeli")

					.contentTypeCode(ClassificationDTO.builder()
							.code("22232009")
							.codingScheme("2.16.840.1.113883.6.96")
							.build())

					.patientId("IHEGREEN-2737^^^&1.3.6.1.4.1.21367.13.20.2000&ISO")
					.idInstalacao("1.3.6.1.4.1.21367.2010.1.2")
					.submissionSetId("1.42.20130403134532.123.1478642031821.46354499" + add)

					.document(RepositorySaveDocumentDTO.builder()
							.cboProfissional("225265")
							.cnesUnidadeSaude("5444429")
							.nomeUnidadeSaude("UBS Teste")
							.descricaoCboProfissional("Enfermeiro da estratégia de saúde da família")
							.cnsProfissional("898000127489128")
							.nomeProfissional("Gabriel Holdener Geraldeli")

							.classCode(ClassificationDTO.builder()
									.code("HEALTH")
									.codingScheme("1.3.6.1.4.1.19376.1.2.6.1")
									.build())
							.formatCode(ClassificationDTO.builder()
									.code("urn:ihe:pcc:xphr:2007")
									.codingScheme("1.3.6.1.4.1.19376.1.2.3")
									.build())
							.healthcareFacilityTypeCode(ClassificationDTO.builder()
									.code("394747008")
									.codingScheme("2.16.840.1.113883.6.96")
									.build())
							.practiceSettingCode(ClassificationDTO.builder()
									.code("Practice-F")
									.codingScheme("1.3.6.1.4.1.21367.2017.3")
									.build())
							.typeCode(ClassificationDTO.builder()
									.code("11488-4")
									.codingScheme("2.16.840.1.113883.6.1")
									.build())

							.sourcePatientId("898004405759903^^^&2.16.840.1.113883.13.236.123456&ISO")
							.patientId("IHEGREEN-2737^^^&1.3.6.1.4.1.21367.13.20.2000&ISO")
							.dataInicioAtendimento(new Date())
							.dataFimAtendimento(new Date())
							.documentId("1.42.20130403134532.123.1478642031821.4633229975489" + add)
							.document(IOUtils.toString(new FileInputStream("/home/fernandobt8/Documents/res/sbis-2018/exemplo-doc-atendimento-5.xml"))
									.replace("\n", "").replace("\r", "").replace("\t", ""))
							.tipoDocumento(TipoDocumento.CONSULTA_AB_CN1_V2)
							.url(repository_url)
							.build())
					.build();

			repositoryService.save(registerDTO);
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
