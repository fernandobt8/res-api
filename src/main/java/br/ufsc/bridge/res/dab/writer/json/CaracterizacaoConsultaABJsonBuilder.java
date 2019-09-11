package br.ufsc.bridge.res.dab.writer.json;

import java.util.Date;

import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.util.RDateUtil;

public class CaracterizacaoConsultaABJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	public CaracterizacaoConsultaABJsonBuilder(T parent) {
		super(parent, "caracterizacao-atendimento.json");
	}

	public CaracterizacaoConsultaABJsonBuilder<T> tipoAtendimento(ResABTipoAtendimentoEnum value) {
		this.document.set("$.items.data.items[?(@.name.value == 'Tipo de atendimento')].value.value", value.getDescricao());
		this.document.set("$.items.data.items[?(@.name.value == 'Tipo de atendimento')].value..code_string", value.getCodigo());
		return this;
	}

	public CaracterizacaoConsultaABJsonBuilder<T> localizacaoAtribuidaPaciente(String cnes, String ine) {
		this.document.set("$.items.data.items[*].items[*].items[?(@.name.value == 'Estabelecimento de saúde')].value.value", cnes);

		if (ine != null) {
			this.document.set("$.items.data.items[*].items[*].items[?(@.name.value == 'Identificação da equipe de saúde')].value.value", ine);
		} else {
			this.document.delete("$.items.data.items[*].items[*].items[?(@.name.value == 'Identificação da equipe de saúde')].value.value");
		}

		return this;
	}

	public CaracterizacaoConsultaABJsonBuilder<T> nomeProfissional(String nome) {
		this.document.set("$.items.data.items[*].items[?(@.name.value == 'Nome do profissional')].value.value", nome);
		return this;
	}

	public CaracterizacaoConsultaABJsonBuilder<T> cnsProfissional(String cns) {
		this.document.set("$.items.data.items[*].items[?(@.name.value == 'CNS do profissional')].value.value", cns);
		return this;
	}

	public CaracterizacaoConsultaABJsonBuilder<T> cboProfissional(String cbo) {
		this.document.set("$.items.data.items[*].items[?(@.name.value == 'Ocupação do profissional')].value..code_string", cbo);
		return this;
	}

	public CaracterizacaoConsultaABJsonBuilder<T> cboDescricaoProfissional(String cboDescricao) {
		this.document.set("$.items.data.items[*].items[?(@.name.value == 'Ocupação do profissional')].value.value", cboDescricao);
		return this;
	}

	public CaracterizacaoConsultaABJsonBuilder<T> dataHoraAdmissao(Date data) {
		this.document.set("$.items.data.items[?(@.name.value == 'Data/hora da admissão')].value.value", RDateUtil.dateToISOEHR(data));
		return this;
	}

}
