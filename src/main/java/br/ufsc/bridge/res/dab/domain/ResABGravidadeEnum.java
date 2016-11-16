package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResABGravidadeEnum {
	BAIXO("at0102"),
	ALTO("at0103");

	private String codigo;
}
