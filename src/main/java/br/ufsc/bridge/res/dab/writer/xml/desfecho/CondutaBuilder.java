package br.ufsc.bridge.res.dab.writer.xml.desfecho;

import br.ufsc.bridge.res.dab.domain.ResABCondutaEnum;
import br.ufsc.bridge.res.dab.writer.xml.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class CondutaBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private ResABCondutaEnum conduta;

	public CondutaBuilder(PARENT parent, ResABCondutaEnum conduta) {
		super(parent);
		this.conduta = conduta;
	}

	@Override
	protected String openTags() {
		return "<Motivo_do_desfecho><name><value>Motivo do desfecho</value></name><value><value>";
	}

	// XXX: esperar chegar servidor para alterar code_string
	@Override
	protected String closeTags() {
		return "</value><defining_code><terminology_id><value>local</value></terminology_id><code_string>at0058</code_string></defining_code></value></Motivo_do_desfecho>";
	}

	@Override
	public String getValue() {
		return this.conduta.getDescricao();
	}
}
