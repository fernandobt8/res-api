package br.ufsc.bridge.res.dab.writer.json;

import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

public class ProblemaDiagnosticoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	private String diagnosticoJson;

	public ProblemaDiagnosticoJsonBuilder(T parent) {
		super(parent, "problema-diagnostico.json");
		this.diagnosticoJson = new GsonBuilder().create().toJson(this.document.read("$.items[0]"));
		this.document.delete("$.items[0]");
	}

	public DiagnosticoJsonBuilder problema() {
		return new DiagnosticoJsonBuilder(this, this.diagnosticoJson);
	}

	@Override
	protected String childJsonPath() {
		return "$.items";
	}

	public class DiagnosticoJsonBuilder extends BaseJsonBuilder<ProblemaDiagnosticoJsonBuilder<T>> {

		private DiagnosticoJsonBuilder(ProblemaDiagnosticoJsonBuilder<T> parent, String json) {
			super(parent, JsonPath.parse(json));
		}

		public DiagnosticoJsonBuilder descricao(String descricao) {
			this.document.set("$.data.items[0].value.value", descricao);
			return this;
		}

		public DiagnosticoJsonBuilder tipo(String tipo) {
			this.document.set("$.data.items[0].value.defining_code..value", tipo);
			return this;
		}

		public DiagnosticoJsonBuilder codigo(String codigo) {
			this.document.set("$.data.items[0].value.defining_code.code_string", codigo);
			return this;
		}
	}
}
