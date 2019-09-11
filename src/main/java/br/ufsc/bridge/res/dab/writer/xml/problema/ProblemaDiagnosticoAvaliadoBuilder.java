package br.ufsc.bridge.res.dab.writer.xml.problema;

import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class ProblemaDiagnosticoAvaliadoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public ProblemaDiagnosticoAvaliadoBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Problemas_fslash_Diagnósticos_avaliados><name><value>Problemas/Diagnósticos avaliados</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Problemas_fslash_Diagnósticos_avaliados>";
	}

	public ProblemaDiagnosticoBuilder<ProblemaDiagnosticoAvaliadoBuilder<PARENT>> problema() {
		return new ProblemaDiagnosticoBuilder<>(this);
	}
}
