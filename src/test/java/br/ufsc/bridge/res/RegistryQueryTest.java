package br.ufsc.bridge.res;

import lombok.extern.slf4j.Slf4j;

import br.ufsc.bridge.res.service.soap.registry.RegistryService;
import br.ufsc.bridge.res.service.soap.registry.dto.RegistryFilter;
import br.ufsc.bridge.res.service.soap.registry.dto.RegistryItem;
import br.ufsc.bridge.res.service.soap.registry.dto.RegistryResponse;
import br.ufsc.bridge.soap.http.SoapCredential;

@Slf4j
public class RegistryQueryTest {

	// private static final String registry_url = "http://35.224.244.22:8280/services/DocumentRegistry";

	private static final String registry_url = "http://xdstools.bridge.ufsc.br:80/xdstools7.0.2/sim/teste-salux__reg/reg/sq";

	static String cbo = "225130";
	static String cns = "992294125290005";
	static String cnes = "7592477";

	public static void main(String args[]) {
		SoapCredential credential = new SoapCredential("", "");
		try {
			RegistryService registryService = new RegistryService(null, registry_url);

			RegistryFilter.RegistryFilterBuilder filterBuilder = RegistryFilter.builder()
					.cnsProfissional(cns)
					.cboProfissional(cbo)
					.cnesProfissional(cnes);

			RegistryResponse<String> registries = registryService.getRegistriesRef(filterBuilder.patientId("206256456550009^^^&1.3.6.1.4.1.21367.13.20.2000&ISO").build());
			log.debug("Registry ObjectRef");
			for (String uuid : registries.getItems()) {
				log.debug(uuid);
			}
			log.debug("\n");

			RegistryResponse<RegistryItem> registries2 = registryService.getRegistriesHeader(filterBuilder.entryUUIDs(registries.getItems()).build());
			log.debug("Registry LeafClass");
			for (RegistryItem item : registries2.getItems()) {
				log.debug("\n");
				log.debug(item.getRepositoryUniqueId());
				log.debug(item.getDocumentUniqueId());
				log.debug("unidade: " + item.getCnesUnidadeSaude());
				log.debug("prof: " + item.getCnsProfissional());
				log.debug("cbo: " + item.getCbo());
				log.debug("data: " + item.getServiceStartTime());
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
