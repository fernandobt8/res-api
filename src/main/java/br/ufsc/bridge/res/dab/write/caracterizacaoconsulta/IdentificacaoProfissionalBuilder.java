package br.ufsc.bridge.res.dab.write.caracterizacaoconsulta;

import br.ufsc.bridge.res.dab.write.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.base.ParentArquetypeWrapper;

public class IdentificacaoProfissionalBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String cns;
	private String cbo;
	private boolean responsavel;

	public IdentificacaoProfissionalBuilder(PARENT parent) {
		super(parent);
	}

	public IdentificacaoProfissionalBuilder<PARENT> cns(String cns) {
		this.cns = cns;
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
		return "</oe:value></value></CNS><CBO><name>" +
				"<value>CBO</value></name><value><oe:value>";
	}

	private String openTagResponsavel() {
		return "</oe:value></value></CBO><É_o_responsável_pelo_atendimento_quest_><name>" +
				"<value>É o responsável pelo atendimento</value></name>\n" +
				"<value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></É_o_responsável_pelo_atendimento_quest_></Identificação_do_profissional>";
	}

	@Override
	public String getValue() {
		return this.cns + this.openTagCbo() + this.cbo + this.openTagResponsavel() + this.responsavel;
	}

}
