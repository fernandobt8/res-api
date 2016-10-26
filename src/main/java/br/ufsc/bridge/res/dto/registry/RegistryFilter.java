package br.ufsc.bridge.res.dto.registry;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistryFilter {

	private String cnsCidadao;
	private Date dataInicial;
	private Date dataFim;

	public boolean hasDataInicial() {
		return this.dataInicial != null;
	}

	public boolean hasDataFim() {
		return this.dataFim != null;
	}
}
