package br.ufsc.bridge.res.dab.write.desfecho;

import br.ufsc.bridge.res.dab.domain.ResABCondutaEnum;
import br.ufsc.bridge.res.dab.write.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.base.ParentArquetypeWrapper;

public class CondutaBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private ResABCondutaEnum conduta;

	public CondutaBuilder(PARENT parent, ResABCondutaEnum conduta) {
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
		return this.conduta.getDescricao();
	}
}
