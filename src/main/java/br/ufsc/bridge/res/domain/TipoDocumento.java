package br.ufsc.bridge.res.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {

	ATENDIMENTO_CIT_2018("RegistroAtendimentoClínico_CIT_2018"),
	CONSULTA_AB_CN1_V1("Resumo de consulta ab_CN1_v1"),
	CONSULTA_AB_CN1_V2("Resumo de consulta ab_CN1_v2.0.oet"),
	SUMARIO_DE_ALTA("cn2_sumario_alta_internacao_v1.0");
	// XXX: add atual tipo do res aqui e mudar no save

	private String codigo;

	public static TipoDocumento getByCodigo(String codigo) {
		for (TipoDocumento tipoDocumento : TipoDocumento.values()) {
			if (tipoDocumento.getCodigo().equals(codigo)) {
				return tipoDocumento;
			}
		}
		return null;
	}

}
