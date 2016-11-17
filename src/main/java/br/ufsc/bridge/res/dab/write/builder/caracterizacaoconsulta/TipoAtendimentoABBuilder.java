package br.ufsc.bridge.res.dab.write.builder.caracterizacaoconsulta;

import br.ufsc.bridge.res.dab.domain.ResABTipoAtendimentoEnum;
import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class TipoAtendimentoABBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private ResABTipoAtendimentoEnum tipoAtendimento;

	public TipoAtendimentoABBuilder(PARENT parent, ResABTipoAtendimentoEnum value) {
		super(parent);
		this.tipoAtendimento = value;
	}

	@Override
	protected final String openTags() {
		return "<Tipo_de_atendimento><name><value>Tipo de atendimento</value></name><value><defining_code><terminology_id><value>local</value></terminology_id><code_string>";
	}

	@Override
	protected final String closeTags() {
		return "</code_string></defining_code></value></Tipo_de_atendimento>";
	}

	@Override
	public String getValue() {
		if (this.tipoAtendimento != null) {
			return this.tipoAtendimento.getCodigo();
		}
		return null;
	}

}
