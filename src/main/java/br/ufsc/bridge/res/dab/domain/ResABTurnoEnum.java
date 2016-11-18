package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResABTurnoEnum {

	MANHA("at0.166"),
	TARDE("at0.167"),
	NOITE("at0.168");

	private String codigo;

	public static ResABTurnoEnum getByCodigo(String codigo) {
		for (ResABTurnoEnum value : values()) {
			if (value.getCodigo().equals(codigo)) {
				return value;
			}
		}
		return null;
	}

}
