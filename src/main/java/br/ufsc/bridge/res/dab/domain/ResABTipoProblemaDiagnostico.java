package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

// XXX: generalizar após merge
@Getter
@AllArgsConstructor
public enum ResABTipoProblemaDiagnostico {

	CID10("CID10"),
	CID10_1998("CID-10_1998.v1.0.0"),
	CIAP("CIAP2");

	private String tipo;

	public static ResABTipoProblemaDiagnostico getByTipo(String codigo) {
		for (ResABTipoProblemaDiagnostico value : values()) {
			if (value.getTipo().equals(codigo)) {
				return value;
			}
		}
		return null;
	}
}
