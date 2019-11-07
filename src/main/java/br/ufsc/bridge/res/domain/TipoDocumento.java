package br.ufsc.bridge.res.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {

	REGISTRO_ATENDIMENTO_CLINICO("RegistroAtendimentoClinico.v4.0"),
	REGISTRO_IMUNOBIOLOGICO("RegistroImunobiologico_v3"),
	SUMARIO_ALTA("cn2_sumario_alta_internacao_v1.0"),
	DISPENSACAO_MEDICAMENTOS("cn10_dispensacao_medicamentos_v1.0");

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
