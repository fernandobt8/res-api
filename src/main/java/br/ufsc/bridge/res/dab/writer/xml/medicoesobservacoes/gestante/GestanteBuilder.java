package br.ufsc.bridge.res.dab.writer.xml.medicoesobservacoes.gestante;

import java.util.Date;

import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class GestanteBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public GestanteBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Gestante><name><value>Gestante</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Gestante>";
	}

	public GestanteBuilder<PARENT> cicloMenstrual(Date dataEvento, Date value) {
		new CicloMenstrualBuilder<>(this, dataEvento, value);
		return this;
	}

	public GestanteBuilder<PARENT> gestacao(Date dataMedicao, String idadeGestacional) {
		new GestacaoBuilder<>(this, dataMedicao, idadeGestacional);
		return this;
	}

	public GestanteBuilder<PARENT> sumarioObstetrico(String gestasPrevias, String partos) {
		new SumarioObstetricoBuilder<>(this, gestasPrevias, partos);
		return this;
	}

}
