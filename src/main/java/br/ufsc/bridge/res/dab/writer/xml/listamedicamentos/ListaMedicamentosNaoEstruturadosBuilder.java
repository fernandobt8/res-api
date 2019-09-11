package br.ufsc.bridge.res.dab.writer.xml.listamedicamentos;

import java.util.List;

import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class ListaMedicamentosNaoEstruturadosBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public ListaMedicamentosNaoEstruturadosBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Prescrição_no_atendimento><name><value>Prescrição no atendimento</value></name><Linha_de_Medicação><name><value>Linha de Medicação</value></name>"
				+ "<language><terminology_id><value>ISO_639-1</value></terminology_id><code_string>pt</code_string></language><encoding><terminology_id>"
				+ "<value>IANA_character-sets</value></terminology_id><code_string>UTF-8</code_string></encoding><subject xsi:type=\"oe:PARTY_SELF\"/><data>";
	}

	@Override
	protected String closeTags() {
		return "</data></Linha_de_Medicação></Prescrição_no_atendimento>";
	}

	public ListaMedicamentosNaoEstruturadosBuilder<PARENT> itemMedicacaoNaoEstruturada(List<String> medicamentosNaoEstruturadas) {
		new ItemMedicacaoNaoEstruturadaBuilder<>(this, medicamentosNaoEstruturadas);
		return this;
	}

}
