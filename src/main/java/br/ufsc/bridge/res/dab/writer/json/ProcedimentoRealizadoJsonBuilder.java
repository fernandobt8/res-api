package br.ufsc.bridge.res.dab.writer.json;

import java.util.Date;

import br.ufsc.bridge.res.util.RDateUtil;

import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

public class ProcedimentoRealizadoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	private String procedimentoJson;

	public ProcedimentoRealizadoJsonBuilder(T parent) {
		super(parent, "procedimento-realizado.json");
		this.procedimentoJson = new GsonBuilder().create().toJson(this.document.read("$.items[0]"));
		this.document.delete("$.items[0]");
	}

	public ProcedimentoJsonBuilder procedimento() {
		return new ProcedimentoJsonBuilder(this, this.procedimentoJson);
	}

	@Override
	protected String childJsonPath() {
		return "$.items";
	}

	public class ProcedimentoJsonBuilder extends BaseJsonBuilder<ProcedimentoRealizadoJsonBuilder<T>> {

		private ProcedimentoJsonBuilder(ProcedimentoRealizadoJsonBuilder<T> parent, String json) {
			super(parent, JsonPath.parse(json));
		}

		public ProcedimentoJsonBuilder data(Date data) {
			this.document.set("$.time.value", RDateUtil.dateToISOEHR(data));
			this.document.set("$.description.items[?(@.name.value == 'Data da realização')].value.value", RDateUtil.dateToISOEHR(data));
			return this;
		}

		public ProcedimentoJsonBuilder descricao(String descricao) {
			this.document.set("$.description.items[?(@.name.value == 'Procedimento SUS')].value.value", descricao);
			return this;
		}

		public ProcedimentoJsonBuilder codigo(String codigo) {
			this.document.set("$.description.items[?(@.name.value == 'Procedimento SUS')].value.defining_code.code_string", codigo);
			return this;
		}
	}
}
