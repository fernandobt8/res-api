package br.ufsc.bridge.res.dab;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDocumento {

	ATENDIMENTO_CIT_2018("RegistroAtendimentoClínico_CIT_2018");

	private String codigo;
}
