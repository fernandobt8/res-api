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

	private String closeTagCNS() {
		return "</oe:value></value></CNS>";
	}

	private String openTagCbo() {
		return "<CBO><name>" + "<value>CBO</value></name><value><oe:value>";
	}

	private String openTagNome() {
		return "<Nome><name><value>Nome</value></name><value><oe:value>";
	}

	private String closeTagNome() {
		return "</oe:value></value></Nome>";
	}

	private String openTagResponsavel() {
		return "</oe:value></value></CBO><É_o_responsável_pelo_atendimento_quest_><name><value>É o responsável pelo atendimento</value></name><value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></É_o_responsável_pelo_atendimento_quest_></Identificação_do_profissional>";
	}

	@Override
	public String getValue() {
		if (this.cns != null || this.nome != null || this.cbo != null || this.responsavel != false) {
			if (StringUtils.isNotBlank(this.nome)) {
				return this.cns + this.closeTagCNS() + this.openTagNome() + this.nome + this.closeTagNome() + this.openTagCbo() + this.cbo + this.openTagResponsavel()
						+ this.responsavel;
			} else {
				return this.cns + this.closeTagCNS() + this.openTagCbo() + this.cbo + this.openTagResponsavel() + this.responsavel;
			}
		}
		return null;
	}
}
