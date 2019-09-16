package br.ufsc.bridge.res.dab.writer.json;

import java.util.List;

import br.ufsc.bridge.res.dab.writer.json.base.BaseJsonBuilder;

public class PrescricaoAtendimentoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	public PrescricaoAtendimentoJsonBuilder(T parent) {
		super(parent, "prescricao-atendimento");
	}

	public MedicamentoNaoEstruturado medicamentoNaoEstruturado() {
		return new MedicamentoNaoEstruturado(this);
	}

	@Override
	protected String childJsonPath() {
		return "$.items.data.items";
	}

	public class MedicamentoNaoEstruturado extends BaseJsonBuilder<PrescricaoAtendimentoJsonBuilder<T>> {

		public MedicamentoNaoEstruturado(PrescricaoAtendimentoJsonBuilder<T> parent) {
			super(parent, "medicamento-nao-estruturado");
		}

		public MedicamentoNaoEstruturado medicamentos(List<String> medicamentos) {
			StringBuilder value = new StringBuilder();
			for (String medicamentoNaoEstruturado : medicamentos) {
				value.append(medicamentoNaoEstruturado);
				value.append("; ");
			}
			this.document.set("$.items.value.value", value.toString());
			return this;
		}
	}
}