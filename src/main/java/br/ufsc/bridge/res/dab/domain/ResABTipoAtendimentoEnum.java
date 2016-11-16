package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResABTipoAtendimentoEnum {

	CONSULTA_AGENDADA_PROGRAMADA_CUIDADO_CONTINUADO("at0.140"),
	CONSULTA_AGENDADA("at0.141"),
	DEMANDA_ESPONTANEA_CONSULTA_NO_DIA("at0.143"),
	DEMANDA_ESPONTANEA_ATENDIMENTO_DE_URGENCIA("at0.144");

	private String codigo;

}
