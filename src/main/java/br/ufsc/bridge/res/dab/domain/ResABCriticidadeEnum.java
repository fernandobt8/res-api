package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResABCriticidadeEnum {
	BAIXO("Baixo", "at0102"),
	ALTO("Alto", "at0103");

	private String descricao;
	private String codigo;

	public static ResABCriticidadeEnum getByCodigo(String codigo) {
		for (ResABCriticidadeEnum value : values()) {
			if (value.getCodigo().equals(codigo)) {
				return value;
			}
		}
		return null;
	}
}
