package br.ufsc.bridge.res.dab.writer.xml.caracterizacaoconsulta;

import br.ufsc.bridge.res.dab.writer.xml.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class IdentificacaoProfissionalBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String cns;
	private String nome;
	private String cbo;
	private boolean responsavel;
	private String descricaoCbo;

	public IdentificacaoProfissionalBuilder(PARENT parent) {
		super(parent);
	}

	public IdentificacaoProfissionalBuilder<PARENT> cns(String cns) {
		this.cns = cns;
		return this;
	}

	public IdentificacaoProfissionalBuilder<PARENT> nome(String nome) {
		this.nome = nome;
		return this;
	}

	public IdentificacaoProfissionalBuilder<PARENT> cbo(String cbo) {
		this.cbo = cbo;
		return this;
	}

	public IdentificacaoProfissionalBuilder<PARENT> descricaoCbo(String descricaoCbo) {
		this.descricaoCbo = descricaoCbo;
		return this;
	}

	public IdentificacaoProfissionalBuilder<PARENT> responsavel(boolean responsavel) {
		this.responsavel = responsavel;
		return this;
	}

	@Override
	protected String openTags() {
		return "<Profissionais_do_atendimento><name><value>Profissionais do atendimento</value></name><Nome_do_profissional><name><value>Nome do profissional</value></name><value><oe:value>";
	}

	private String openTagCns() {
		return "</oe:value></value></Nome_do_profissional><CNS_do_profissional><name><value>CNS do profissional</value></name><value><oe:value>";
	}

	private String openTagDescricaoCbo() {
		return "</oe:value></value></CNS_do_profissional><Ocupação_do_profissional><name><value>Ocupação do profissional</value></name><value><value>";
	}

	private String openTagCbo() {
		return "</value><defining_code><terminology_id><value>CBO_2002.v1.0.0</value></terminology_id><code_string>";
	}

	private String openTagResponsavel() {
		return "</code_string></defining_code></value></Ocupação_do_profissional><É_o_responsável_pelo_atendimento_quest_><name><value>É o responsável pelo atendimento?</value></name><value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></É_o_responsável_pelo_atendimento_quest_></Profissionais_do_atendimento>";
	}

	@Override
	public String getValue() {
		if (this.cns != null || this.nome != null || this.cbo != null || this.responsavel != false) {
			//			if (StringUtils.isNotBlank(this.nome)) {
			return this.nome + this.openTagCns() + this.cns + this.openTagDescricaoCbo() + this.descricaoCbo + this.openTagCbo() + this.cbo + this.openTagResponsavel() + this.responsavel;
			//			} else {
			//				return this.cns + this.openTagCbo() + this.cbo + this.openTagResponsavel() + this.responsavel;
			//			}
		}
		return null;
	}
}
