package br.ufsc.bridge.res.soap;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.service.soap.repository.RepositoryService;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositoryDocumentItem;
import br.ufsc.bridge.res.service.soap.repository.dto.RepositoryFilter;
import br.ufsc.bridge.soap.http.SoapCredential;

@Slf4j
public class RepositoryLoadTest {

	// private static final String repository_url = "http://177.71.197.58:57772/csp/healthshare/hsbus/services/HS.IHE.XDSb.Repository.Services.cls";

	private static final String repository_url = "http://xdstools.bridge.ufsc.br:80/xdstools7.0.2/sim/teste-salux__rep/rep/ret";

	static String cbo = "225130";
	static String cns = "992294125290005";
	static String cnes = "7592477";

	public static void main(String args[]) {
		SoapCredential credential = new SoapCredential("", "");
		try {
			RepositoryService repositoryService = new RepositoryService(credential);

			RepositoryFilter repositoryFilter = RepositoryFilter.builder()
					.cnsProfissional(cns)
					.cboProfissional(cbo)
					.cnesProfissional(cnes)
					.document(RepositoryFilter.DocumentItemFilter.builder()
							.repositoryURL(repository_url)
							.repositoryUniqueId("1.1.4567332.1.2")
							.documentUniqueId("1.42.20130403134532.123.1478642031821.463322997548919")
							.build())
					.build();

			List<RepositoryDocumentItem> documents = repositoryService.getDocuments(repositoryFilter);
			for (RepositoryDocumentItem item : documents) {
				log.debug("Document:");
				log.debug(item.getDocument());
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
