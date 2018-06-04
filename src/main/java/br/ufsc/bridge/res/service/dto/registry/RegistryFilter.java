package br.ufsc.bridge.res.service.dto.registry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegistryFilter {

	private String cnsProfissional;
	private String cboProfissional;
	private String cnesProfissional;

	private String cnsCidadao;
	private Date dataInicial;
	private Date dataFim;
	private List<String> entryUUIDs;

	public RegistryFilter() {
		this.entryUUIDs = new ArrayList<>();
	}

	public boolean hasCnsCidadao() {
		return this.cnsCidadao != null;
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
