package br.ufsc.bridge.res.dab.writer.xml.desfecho;

import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class SolicitacoesEncaminhamentoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public SolicitacoesEncaminhamentoBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Solicitações_de_encaminhamentos><name><value>Solicitações de encaminhamentos</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Solicitações_de_encaminhamentos>";
	}

	public SolicitacoesEncaminhamentoBuilder<PARENT> encaminhamento(String descricao) {
		new EncaminhamentoBuilder<>(this, descricao);
		return this;
	}

}
