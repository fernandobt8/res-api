package br.ufsc.bridge.res.dab.writer.json;

import java.util.Date;

import br.ufsc.bridge.res.domain.ResCriticidadeEnum;
import br.ufsc.bridge.res.util.RDateUtil;

import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class AlergiaReacoesAdversasJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	private String alergiaJson;

	public AlergiaReacoesAdversasJsonBuilder(T parent) {
		super(parent, "alergia-reacoes.json");
		this.alergiaJson = new GsonBuilder().create().toJson(this.document.read("$.items[0]"));
		this.document.delete("$.items[0]");
	}

	public AlergiaJsonBuilder alergia() {
		return new AlergiaJsonBuilder(this, this.alergiaJson);
	}

	@Override
	protected String childJsonPath() {
		return "$.items";
	}

	public class AlergiaJsonBuilder extends BaseJsonBuilder<AlergiaReacoesAdversasJsonBuilder<T>> {

		private String eventoJson;

		private boolean hasCriticidade;

		private AlergiaJsonBuilder(AlergiaReacoesAdversasJsonBuilder<T> parent, String json) {
			super(parent, JsonPath.parse(json));
			this.eventoJson = new GsonBuilder().create().toJson(((JSONArray) this.document.read("$.data.items[?(@.name.value == 'Evento da reação')]")).get(0));
			this.document.delete("$.data.items[?(@.name.value == 'Evento da reação')]");
		}

		public AlergiaJsonBuilder agente(String agente) {
			this.document.set("$.data.items[?(@.name.value == 'Agente/substância específica')].value.value", agente);
			return this;
		}

		public AlergiaJsonBuilder categoria(String categoria) {
			this.document.set("$.data.items[?(@.name.value == 'Categoria do agente causador da alergia ou reação adversa')].value.value", categoria);
			return this;
		}

		public AlergiaJsonBuilder criticidade(ResCriticidadeEnum criticidade) {
			this.hasCriticidade = criticidade != null;
			if (this.hasCriticidade) {
				this.document.set("$.data.items[?(@.name.value == 'Criticidade')].value.value", criticidade.getDescricao());
				this.document.set("$.data.items[?(@.name.value == 'Criticidade')].value..code_string", criticidade.getCodigo());
			}
			return this;
		}

		@Override
		public Object json() {
			if (!this.hasCriticidade) {
				this.document.delete("$.data.items[?(@.name.value == 'Criticidade')]");
			}
			return super.json();
		}

		@Override
		protected String childJsonPath() {
			return "$.data.items";
		}

		public EventoReacaoJsonBuilder evento() {
			return new EventoReacaoJsonBuilder(this, this.eventoJson);
		}
	}

	public class EventoReacaoJsonBuilder extends BaseJsonBuilder<AlergiaJsonBuilder> {

		private boolean hasDataInstalacao;
		private boolean hasManifestacao;
		private boolean hasEvolucao;

		private EventoReacaoJsonBuilder(AlergiaJsonBuilder parent, String json) {
			super(parent, JsonPath.parse(json));
		}

		public EventoReacaoJsonBuilder dataInstalacao(Date data) {
			this.hasDataInstalacao = data != null;
			if (this.hasDataInstalacao) {
				this.document.set("$.items[?(@.name.value == 'Data da instalação da reação adversa')].value.value", RDateUtil.dateToISOEHR(data));
			}
			return this;
		}

		public EventoReacaoJsonBuilder manifestacao(String manifestacao) {
			this.hasManifestacao = manifestacao != null;
			if (this.hasManifestacao) {
				this.document.set("$.items[?(@.name.value == 'Manifestação')].value.value", manifestacao);
			}
			return this;
		}

		public EventoReacaoJsonBuilder evolucao(String evolucao) {
			this.hasEvolucao = evolucao != null;
			if (this.hasEvolucao) {
				this.document.set("$.items[?(@.name.value == 'Evolução da alergia/reação adversa')].value.value", evolucao);
			}
			return this;
		}

		@Override
		public Object json() {
			if (!(this.hasEvolucao || this.hasManifestacao || this.hasDataInstalacao)) {
				return null;
			}
			if (!this.hasEvolucao) {
				this.document.delete("$.items[?(@.name.value == 'Evolução da alergia/reação adversa')]");
			}
			if (!this.hasDataInstalacao) {
				this.document.delete("$.items[?(@.name.value == 'Data da instalação da reação adversa')]");
			}
			if (!this.hasManifestacao) {
				this.document.delete("$.items[?(@.name.value == 'Manifestação')]");
			}
			return super.json();
		}
	}
}
