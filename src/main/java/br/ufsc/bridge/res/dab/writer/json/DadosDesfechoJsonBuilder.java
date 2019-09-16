package br.ufsc.bridge.res.dab.writer.json;

import br.ufsc.bridge.res.dab.writer.json.base.BaseJsonBuilder;

public class DadosDesfechoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	private String desfechoJson;

	public DadosDesfechoJsonBuilder(T parent) {
		super(parent, "dados-desfecho");
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
			super(parent, "desfecho");
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
