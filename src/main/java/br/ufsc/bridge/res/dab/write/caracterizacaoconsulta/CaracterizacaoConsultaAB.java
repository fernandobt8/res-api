package br.ufsc.bridge.res.dab.write.caracterizacaoconsulta;

import br.ufsc.bridge.res.dab.write.base.ParentArquetypeWrapper;

public class CaracterizacaoConsultaAB extends ParentArquetypeWrapper {

	public CaracterizacaoConsultaAB(TipoAtendimentoAB tipoAtendimentoAB) {
		this.childs.add(tipoAtendimentoAB);
	}

	@Override
	protected String openTags() {
		return "<Caracterização_da_consulta><name><value>Caracterização da consulta</value></name><Admissão_do_paciente><name><value>Admissão do paciente</value></name>"
				+ "<language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id>"
				+ "<value>IANA_character-sets</value></terminology_id><code_string>UTF-8</code_string></encoding><subject></subject><data>";
	}

	@Override
	protected String closeTags() {
		return "</data></Admissão_do_paciente></Caracterização_da_consulta>";
	}

}
