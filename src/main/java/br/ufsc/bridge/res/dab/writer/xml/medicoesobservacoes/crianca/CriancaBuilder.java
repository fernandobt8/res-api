package br.ufsc.bridge.res.dab.writer.xml.medicoesobservacoes.crianca;

import java.util.Date;

import br.ufsc.bridge.res.dab.domain.ResABAleitamentoMaternoEnum;
import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class CriancaBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public CriancaBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Criança><name><value>Criança</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Criança>";
	}

	public CriancaBuilder<PARENT> aleitamentoMaterno(Date dataEvento, ResABAleitamentoMaternoEnum aleitamentoMaterno) {
		new AleitamentoMaternoBuilder<>(this, dataEvento, aleitamentoMaterno);
		return this;
	}

}
