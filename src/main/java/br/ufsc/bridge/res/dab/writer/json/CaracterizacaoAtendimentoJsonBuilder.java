package br.ufsc.bridge.res.dab.writer.json;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.util.RDateUtil;
import br.ufsc.bridge.res.util.json.BaseJsonBuilder;

public class CaracterizacaoAtendimentoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	public CaracterizacaoAtendimentoJsonBuilder(T parent) {
		super(parent, "caracterizacao-atendimento-imunobiologico");
	}

	public CaracterizacaoAtendimentoJsonBuilder<T> horaAtendimento(Date value) {
		if (value != null) {
			this.document.set("$.items[?(@.name.value == 'Admissão do paciente')]"
					+ ".data.items[?(@.name.value == 'Hora do atendimento')]"
					+ ".value.value", RDateUtil.dateToISOEHR(value));
		} else {
			this.document.delete("$.items[?(@.name.value == 'Admissão do paciente')]"
					+ ".data.items[?(@.name.value == 'Hora do atendimento')]");
		}
		return this;
	}

	public CaracterizacaoAtendimentoJsonBuilder<T> localAtendimento(String value) {
		this.document.set("$.items[?(@.name.value == 'Admissão do paciente')]"
				+ ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"
				+ ".items[?(@.name.value == 'Local de atendimento')]"
				+ ".value.value", value);
		return this;
	}

	public CaracterizacaoAtendimentoJsonBuilder<T> cnes(String value) {
		this.document.set("$.items[?(@.name.value == 'Admissão do paciente')]"
				+ ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"
				+ ".items[?(@.name.value == 'Instituição')]"
				+ ".items[?(@.name.value == 'CNES')]"
				+ ".value.value", value);
		return this;
	}

	public CaracterizacaoAtendimentoJsonBuilder<T> ine(String value) {
		this.document.set("$.items[?(@.name.value == 'Admissão do paciente')]"
				+ ".data.items[?(@.name.value == 'Localização atribuída ao paciente')]"
				+ ".items[?(@.name.value == 'Instituição')]"
				+ ".items[?(@.name.value == 'INE')]"
				+ ".value.value", value);
		return this;
	}

	public CaracterizacaoAtendimentoJsonBuilder<T> cnsProfissionalResponsavel(String value) {
		this.document.set("$.items[?(@.name.value == 'Admissão do paciente')]"
				+ ".data.items[?(@.name.value == 'Médico da consulta')]"
				+ ".items[?(@.name.value == 'CNS do profissional responsável')]"
				+ ".value.value", value);
		return this;
	}

	public CaracterizacaoAtendimentoJsonBuilder<T> nomeProfissionalResponsavel(String value) {
		if (StringUtils.isNotEmpty(value)) {
			this.document.set("$.items[?(@.name.value == 'Admissão do paciente')]"
					+ ".data.items[?(@.name.value == 'Médico da consulta')]"
					+ ".items[?(@.name.value == 'Nome do profissional responsável')]"
					+ ".value.value", value);
		} else {
			this.document.delete("$.items[?(@.name.value == 'Admissão do paciente')]"
					+ ".data.items[?(@.name.value == 'Médico da consulta')]"
					+ ".items[?(@.name.value == 'Nome do profissional responsável')]");
		}
		return this;
	}

	public CaracterizacaoAtendimentoJsonBuilder<T> cbo(String value) {
		this.document.set("$.items[?(@.name.value == 'Admissão do paciente')]"
				+ ".data.items[?(@.name.value == 'Médico da consulta')]"
				+ ".items[?(@.name.value == 'CBO')]"
				+ ".value.value", value);
		return this;
	}

}
