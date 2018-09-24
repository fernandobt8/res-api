package br.ufsc.bridge.res.dab;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {

	ATENDIMENTO_CIT_2018("RegistroAtendimentoClínico_CIT_2018"),
	CONSULTA_AB_CN1_V1("Resumo de consulta ab_CN1_v1"),
	CONSULTA_AB_CN1_V2("Resumo de consulta ab_CN1_v2.0.oet");

	private String codigo;
}
