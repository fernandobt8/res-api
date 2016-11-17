package br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.gestante;

import java.util.Date;

import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

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

	public GestanteBuilder<PARENT> cicloMenstrual(Date value, Date dataEvento) {
		new CicloMenstrualBuilder<>(this, value, dataEvento);
		return this;
	}

	public GestanteBuilder<PARENT> gestacao(String idadeGestacional, Date dataMedicao) {
		new GestacaoBuilder<>(this, idadeGestacional, dataMedicao);
		return this;
	}

	public GestanteBuilder<PARENT> sumarioObstetrico(String gestasPrevias, String partos) {
		new SumarioObstetricoBuilder<>(this, gestasPrevias, partos);
		return this;
	}

}
