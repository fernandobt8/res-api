package br.ufsc.bridge.res.dab.write.caracterizacaoconsulta;

import br.ufsc.bridge.res.dab.write.base.TypedArquetypeWrapper;

public class TipoAtendimentoAB extends TypedArquetypeWrapper<TipoAtendimentoEnum> {

	public TipoAtendimentoAB(TipoAtendimentoEnum value) {
		super(value);
	}

	@Override
	protected String openTags() {
		return "<Tipo_de_atendimento><name><value>Tipo de atendimento</value></name><value><defining_code><terminology_id><value>local</value></terminology_id><code_string>";
	}

	@Override
	protected String closeTags() {
		return "</code_string></defining_code></value></Tipo_de_atendimento>";
	}

	@Override
	protected String getContent() {
		return this.value.getCodigo();
	}

}
