package br.ufsc.bridge.res.dab.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import br.ufsc.bridge.res.util.json.DateJsonPathValueConverter;
import br.ufsc.bridge.res.util.json.JsonPathProperty;

@Getter
@Setter
@NoArgsConstructor
public class CaracterizacaoAtendimento implements Serializable {

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Hora do atendimento')]"
			+ ".value.value",
			converter = DateJsonPathValueConverter.class)
	private Date horaAtendimento;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"
			+ ".items[?(@.name.value == 'Local de atendimento')]"
			+ ".value.value")
	private String localAtendimento;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"
			+ ".items[?(@.name.value == 'Instituição')]"
			+ ".items[?(@.name.value == 'CNES')]"
			+ ".value.value")
	private String cnes;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"
			+ ".items[?(@.name.value == 'Instituição')]"
			+ ".items[?(@.name.value == 'INE')]"
			+ ".value.value")
	private String ine;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Médico da consulta')]"
			+ ".items[?(@.name.value == 'CNS do profissional responsável')]"
			+ ".value.value")
	private String cnsProfissional;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Médico da consulta')]"
			+ ".items[?(@.name.value == 'Nome do profissional responsável')]"
			+ ".value.value")
	private String nomeProfissional;

	@JsonPathProperty(value = "$.items[?(@.name.value == 'Admissão do paciente')]"
			+ ".data.items[?(@.name.value == 'Médico da consulta')]"
			+ ".items[?(@.name.value == 'CBO')]"
			+ ".value.value")
	private String cbo;


}
