package br.ufsc.bridge.res.dab.writer.xml.caracterizacaoconsulta;

import br.ufsc.bridge.res.dab.domain.ResABTurnoEnum;
import br.ufsc.bridge.res.dab.writer.xml.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class TurnoAtendimentoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private ResABTurnoEnum value;

	public TurnoAtendimentoBuilder(PARENT parent, ResABTurnoEnum value) {
		super(parent);
		this.value = value;
	}

	@Override
	protected String openTags() {
		return "<Turno_de_atendimento><name><value>Turno de atendimento</value>" +
				"</name><value><defining_code><terminology_id>" +
				"<value>local</value></terminology_id><code_string>";
	}

	@Override
	public String getValue() {
		if (this.value != null) {
			return this.value.getCodigo();
		}
		return null;
	}

	@Override
	protected String closeTags() {
		return "</code_string></defining_code></value></Turno_de_atendimento>";
	}
}
