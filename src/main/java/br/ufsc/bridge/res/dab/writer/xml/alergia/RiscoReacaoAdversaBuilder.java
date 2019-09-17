package br.ufsc.bridge.res.dab.writer.xml.alergia;

import br.ufsc.bridge.res.dab.domain.ResCriticidadeEnum;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

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
			value += this.openTagsGravidade() + this.gravidade.getDescricao() + this.getMidTagsGravidade() + this.gravidade.getCodigo() + this.closeTagsGravidade();
		}
		if (this.categoria != null) {
			value += this.openTagsCategoria() + this.categoria + this.closeTagsCategoria();
		}
		value += super.getValue();
		return value;
	}

	@Override
	protected String openTags() {
		return "<Risco_de_reação_adversa><name><value>Risco de reação adversa</value></name><language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id><value>IANA_character-sets</value></terminology_id><code_string>UTF-8</code_string></encoding><subject xsi:type=\"oe:PARTY_SELF\"/><data>";
	}

	@Override
	protected String closeTags() {
		return "</data></Risco_de_reação_adversa>";
	}

	private String openTagsAgente() {
		return "<Agente_fslash_substância_específica><name><value>Agente/substância específica</value></name><value><oe:value>";
	}

	private String closeTagsAgente() {
		return "</oe:value></value></Agente_fslash_substância_específica>";
	}

	private String openTagsGravidade() {
		return "<Criticidade><name><value>Criticidade</value></name><value><value>";
	}

	private String getMidTagsGravidade() {
		return "</value><defining_code><terminology_id><value>local</value></terminology_id><code_string>";
	}

	private String closeTagsGravidade() {
		return "</code_string></defining_code></value></Criticidade>";
	}

	private String openTagsCategoria() {
		return "<Categoria_do_agente_causador_da_alergia_ou_reação_adversa><name><value>Categoria do agente causador da alergia ou reação adversa</value></name><value><value>";
	}

	private String closeTagsCategoria() {
		return "</value></value></Categoria_do_agente_causador_da_alergia_ou_reação_adversa>";
	}

	public EventoReacaoBuilder<RiscoReacaoAdversaBuilder<PARENT>> eventoReacao() {
		return new EventoReacaoBuilder<>(this);
	}

}
