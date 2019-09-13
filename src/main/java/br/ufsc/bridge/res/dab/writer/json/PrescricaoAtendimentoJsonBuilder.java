package br.ufsc.bridge.res.dab.writer.json;

import java.util.List;

import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class PrescricaoAtendimentoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	private String medicamentoNaoEstruturado;

	public PrescricaoAtendimentoJsonBuilder(T parent) {
		super(parent, "prescricao-atendimento.json");
		this.medicamentoNaoEstruturado = new GsonBuilder().create().toJson(
				((JSONArray) this.document.read("$.items.data.items[?(@.name.value == 'Medicamentos prescritos no atendimento (não estruturado)')]")).get(0));
		this.document.delete("$.items.data.items[?(@.name.value == 'Medicamentos prescritos no atendimento (não estruturado)')]");
	}

	public MedicamentoNaoEstruturado medicamentoNaoEstruturado() {
		return new MedicamentoNaoEstruturado(this, this.medicamentoNaoEstruturado);
	}

	@Override
	protected String childJsonPath() {
		return "$.items.data.items";
	}

	public class MedicamentoNaoEstruturado extends BaseJsonBuilder<PrescricaoAtendimentoJsonBuilder<T>> {

		public MedicamentoNaoEstruturado(PrescricaoAtendimentoJsonBuilder<T> parent, String medicamento) {
			super(parent, JsonPath.parse(medicamento));
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
