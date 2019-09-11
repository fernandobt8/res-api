package br.ufsc.bridge.res.dab.writer.xml.desfecho;

import br.ufsc.bridge.res.dab.writer.xml.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class EncaminhamentoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String descricao;

	public EncaminhamentoBuilder(PARENT parent, String descricao) {
		super(parent);
		this.descricao = descricao;
	}

	@Override
	protected String openTags() {
		return "<Encaminhamento><name><value>Encaminhamento</value></name><value><oe:value>";
	}

	@Override
	public String getValue() {
		return this.descricao;
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></Encaminhamento>";
	}

}
