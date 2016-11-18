package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResABTipoProblemaDiagnostico {

	CID10("CID10"),
	CIAP("CIAP2");

	private String descricao;
}
