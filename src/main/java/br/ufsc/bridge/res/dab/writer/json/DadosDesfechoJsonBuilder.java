package br.ufsc.bridge.res.dab.writer.json;

import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

public class DadosDesfechoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	private String desfechoJson;

	public DadosDesfechoJsonBuilder(T parent) {
		super(parent, "dados-desfecho.json");
		this.desfechoJson = new GsonBuilder().create().toJson(this.document.read("$.items.data.items[0]"));
		this.document.delete("$.items.data.items[0]");
	}

	public DesfechoJsonBuilder desfecho() {
		return new DesfechoJsonBuilder(this, this.desfechoJson);
	}

	@Override
	protected String childJsonPath() {
		return "$.items.data.items";
	}

	public class DesfechoJsonBuilder extends BaseJsonBuilder<DadosDesfechoJsonBuilder<T>> {

		private DesfechoJsonBuilder(DadosDesfechoJsonBuilder<T> parent, String json) {
			super(parent, JsonPath.parse(json));
		}

		public DesfechoJsonBuilder descricao(String descricao) {
			this.document.set("$.value.value", descricao);
			return this;
		}

		public DesfechoJsonBuilder codigo(String codigo) {
			this.document.set("$.value.defining_code.code_string", codigo);
			return this;
		}
	}
}
