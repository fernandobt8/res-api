package br.ufsc.bridge.res.dab.write.builder.caracterizacaoconsulta;

import org.apache.commons.lang3.StringUtils;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class IdentificacaoProfissionalBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String cns;
	private String nome;
	private String cbo;
	private boolean responsavel;

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

	public IdentificacaoProfissionalBuilder<PARENT> responsavel(boolean responsavel) {
		this.responsavel = responsavel;
		return this;
	}

	@Override
	protected String openTags() {
		return "<Identificação_do_profissional><name><value>Identificação do profissional</value></name><CNS><name><value>CNS</value></name><value><oe:value>";
	}

	private String openTagCbo() {
		return "</oe:value></value></CNS><CBO><name>" + "<value>CBO</value></name><value><oe:value>";
	}

	private String closeTagCbo() {
		return "</oe:value></value></CBO>";
	}

	private String openTagNome() {
		return "<Nome><name><value>Nome</value></name><value><oe:value>";
	}

	private String closeTagNome() {
		return "</oe:value></value></Nome>";
	}

	private String openTagResponsavel() {
		return "<É_o_responsável_pelo_atendimento_quest_><name><value>É o responsável pelo atendimento</value></name><value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></É_o_responsável_pelo_atendimento_quest_></Identificação_do_profissional>";
	}

	@Override
	public String getValue() {
		if (StringUtils.isNotBlank(this.nome)) {
			return this.cns + this.openTagCbo() + this.cbo + this.closeTagCbo() + this.openTagNome() + this.nome + this.closeTagNome() + this.openTagResponsavel()
					+ this.responsavel;
		} else {
			return this.cns + this.openTagCbo() + this.cbo + this.closeTagCbo() + this.openTagResponsavel() + this.responsavel;
		}
	}
}
