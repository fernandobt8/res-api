package br.ufsc.bridge.res.dab.write.builder.problema;

import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class ProblemaDiagnosticoAvaliadoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public ProblemaDiagnosticoAvaliadoBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Problemas__fslash__diagnósticos_avaliados><name><value>Problemas / diagnósticos avaliados</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Problemas__fslash__diagnósticos_avaliados>";
	}

	public ProblemaDiagnosticoBuilder<ProblemaDiagnosticoAvaliadoBuilder<PARENT>> problema() {
		return new ProblemaDiagnosticoBuilder<>(this);
	}
}
