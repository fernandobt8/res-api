package br.ufsc.bridge.res.dab.writer.xml.desfecho;

import br.ufsc.bridge.res.dab.domain.ResABCondutaEnum;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class DadosDesfechoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public DadosDesfechoBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Dados_do_desfecho><name><value>Dados do desfecho</value></name>"
				+ "<Desfecho__fslash__alta_do_contato_assistencial><name><value>"
				+ "Desfecho / alta do contato assistencial</value></name><language><terminology_id>"
				+ "<value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language>"
				+ "<encoding><terminology_id><value>IANA_character-sets</value></terminology_id>"
				+ "<code_string>UTF-8</code_string></encoding><subject></subject><data>";
	}

	@Override
	protected String closeTags() {
		return "</data></Desfecho__fslash__alta_do_contato_assistencial></Dados_do_desfecho>";
	}

	public DadosDesfechoBuilder<PARENT> conduta(ResABCondutaEnum conduta) {
		new CondutaBuilder<>(this, conduta);
		return this;
	}

	public SolicitacoesEncaminhamentoBuilder<DadosDesfechoBuilder<PARENT>> solicitacoesEncaminhamento() {
		return new SolicitacoesEncaminhamentoBuilder<>(this);
	}
}
