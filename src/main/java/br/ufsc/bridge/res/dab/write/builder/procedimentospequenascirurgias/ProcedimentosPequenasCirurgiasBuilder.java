package br.ufsc.bridge.res.dab.write.builder.procedimentospequenascirurgias;

import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class ProcedimentosPequenasCirurgiasBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public ProcedimentosPequenasCirurgiasBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Procedimento_openBrkt_s_closeBrkt__realizado_openBrkt_s_closeBrkt__ou_solicitado_openBrkt_s_closeBrkt_><name><value>Procedimento(s) realizado(s) ou solicitado(s)</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Procedimento_openBrkt_s_closeBrkt__realizado_openBrkt_s_closeBrkt__ou_solicitado_openBrkt_s_closeBrkt_>";
	}

	public ProcedimentoBuilder<ProcedimentosPequenasCirurgiasBuilder<PARENT>> procedimento() {
		return new ProcedimentoBuilder<>(this);
	}

}
