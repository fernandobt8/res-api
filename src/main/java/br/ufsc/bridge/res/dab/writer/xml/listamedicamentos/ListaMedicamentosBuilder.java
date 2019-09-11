package br.ufsc.bridge.res.dab.writer.xml.listamedicamentos;

import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class ListaMedicamentosBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public ListaMedicamentosBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Lista_de_medicamentos><name><value>Lista de medicamentos</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Lista_de_medicamentos>";
	}

	public ItemMedicacaoBuilder<ListaMedicamentosBuilder<PARENT>> itemMedicacao() {
		return new ItemMedicacaoBuilder<>(this);
	}

}
