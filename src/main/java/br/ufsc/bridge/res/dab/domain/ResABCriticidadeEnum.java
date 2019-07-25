package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// XXX: generalizar, não é só do dab
@Getter
@AllArgsConstructor
public enum ResABCriticidadeEnum {
	BAIXO("at0102"),
	ALTO("at0103");

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
