package br.ufsc.bridge.res.dab.write.builder.procedimentospequenascirurgias;

import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class ProcedimentosPequenasCirurgiasBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public ProcedimentosPequenasCirurgiasBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Procedimentos_ou_pequenas_cirurgias><name><value>Procedimentos ou pequenas cirurgias</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Procedimentos_ou_pequenas_cirurgias>";
	}

	public ProcedimentoBuilder<ProcedimentosPequenasCirurgiasBuilder<PARENT>> procedimento() {
		return new ProcedimentoBuilder<>(this);
	}

}
