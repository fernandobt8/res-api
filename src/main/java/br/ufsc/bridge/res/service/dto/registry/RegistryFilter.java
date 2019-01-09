package br.ufsc.bridge.res.service.dto.registry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegistryFilter {

	private String cnsProfissional;
	private String cboProfissional;
	private String cnesProfissional;

	private String patientId;
	private Date dataInicial;
	private Date dataFim;
	private List<String> entryUUIDs;

	public RegistryFilter() {
		this.entryUUIDs = new ArrayList<>();
	}

	public boolean hasPatientId() {
		return this.patientId != null;
	}

	public boolean hasDataInicial() {
		return this.dataInicial != null;
	}

	public boolean hasDataFim() {
		return this.dataFim != null;
	}

	public boolean hasEntryUUID() {
		return this.entryUUIDs != null && !this.entryUUIDs.isEmpty();
	}

}
