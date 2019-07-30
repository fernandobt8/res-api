package br.ufsc.bridge.res.dab.write.builder.desfecho;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class CondutaBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String conduta;

	public CondutaBuilder(PARENT parent, String conduta) {
		super(parent);
		this.conduta = conduta;
	}

	@Override
	protected String openTags() {
		return "<Conduta><name><value>Conduta</value></name><value><value>";
	}

	@Override
	protected String closeTags() {
		return "</value></value></Conduta>";
	}

	@Override
	public String getValue() {
		return this.conduta;
	}
}
