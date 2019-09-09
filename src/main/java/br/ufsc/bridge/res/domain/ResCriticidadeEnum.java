package br.ufsc.bridge.res.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResCriticidadeEnum {
	BAIXO("Baixo", "at0102"),
	ALTO("Alto", "at0103");

	private String descricao;
	private String codigo;

	public static ResCriticidadeEnum getByCodigo(String codigo) {
		for (ResCriticidadeEnum value : values()) {
			if (value.getCodigo().equals(codigo)) {
				return value;
			}
		}
		return null;
	}
}
