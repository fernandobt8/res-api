package br.ufsc.bridge.res.dab.write.builder.alergia;

import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.domain.ResCriticidadeEnum;

public class RiscoReacaoAdversaBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	private String agente;
	private ResCriticidadeEnum gravidade;
	private String categoria;

	public RiscoReacaoAdversaBuilder(PARENT parent) {
		super(parent);
	}

	public RiscoReacaoAdversaBuilder<PARENT> agente(String agente) {
		this.agente = agente;
		return this;
	}

	public RiscoReacaoAdversaBuilder<PARENT> gravidade(ResCriticidadeEnum gravidade) {
		this.gravidade = gravidade;
		return this;
	}

	public RiscoReacaoAdversaBuilder<PARENT> categoria(String categoria) {
		this.categoria = categoria;
		return this;
	}

	@Override
	public String getValue() {
		String value = "";
		if (this.agente != null) {
			value += this.openTagsAgente() + this.agente + this.closeTagsAgente();
		}
		if (this.gravidade != null) {
			value += this.openTagsGravidade() + this.gravidade.getCodigo() + this.closeTagsGravidade();
		}
		if (this.categoria != null) {
			value += this.openTagsCategoria() + this.categoria + this.closeTagsCategoria();
		}
		value += super.getValue();
		return value;
	}

	@Override
	protected String openTags() {
		return "<Risco_de_Reação_Adversa><name><value>Risco de Reação Adversa</value></name><language>"
				+ "<terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string>"
				+ "</language><encoding><terminology_id><value>IANA_character-sets</value>"
				+ "</terminology_id><code_string>UTF-8</code_string></encoding><subject></subject><data>";
	}

	@Override
	protected String closeTags() {
		return "</data></Risco_de_Reação_Adversa>";
	}

	private String openTagsAgente() {
		return "<Agente__fslash__substância_específica><name><value>Agente / substância específica</value></name><value><oe:value>";
	}

	private String closeTagsAgente() {
		return "</oe:value></value></Agente__fslash__substância_específica>";
	}

	private String openTagsGravidade() {
		return "<Gravidade><name><value>Gravidade</value></name><value><defining_code><terminology_id><value>local</value></terminology_id><code_string>";
	}

	private String closeTagsGravidade() {
		return "</code_string></defining_code></value></Gravidade>";
	}

	private String openTagsCategoria() {
		return "<Categoria_do_agente_causador_da_alergia__fslash__reação_adversa><name>"
				+ "<value>Categoria do agente causador da alergia / reação adversa</value></name><value><oe:value>";
	}

	private String closeTagsCategoria() {
		return "</oe:value></value></Categoria_do_agente_causador_da_alergia__fslash__reação_adversa>";
	}

	public EventoReacaoBuilder<RiscoReacaoAdversaBuilder<PARENT>> eventoReacao() {
		return new EventoReacaoBuilder<>(this);
	}

}
