package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResABTipoAtendimentoEnum  {

	CONSULTA_AGENDADA_PROGRAMADA_CUIDADO_CONTINUADO("Consulta agendada programada/ cuidado continuado", "at0.140"),
	CONSULTA_AGENDADA("Consulta Agendada", "at0.141"),
	DEMANDA_ESPONTANEA_CONSULTA_NO_DIA("Demanda Espontanea Consulta no Dia", "at0.143"),
	DEMANDA_ESPONTANEA_ATENDIMENTO_DE_URGENCIA("Demanda Espontanea Atendimento de Urgencia", "at0.144");

	private String descricao;
	private String codigo;

}
