package br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.avaliacao;

import java.util.Date;

import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class AvaliacaoAntropometricaBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public AvaliacaoAntropometricaBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Medições><name><value>Medições</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Medições>";
	}

	public AvaliacaoAntropometricaBuilder<PARENT> pesoCorporal(Date dataEvento, String peso) {
		new PesoCorporalBuilder<>(this, dataEvento, peso);
		return this;
	}

	public AvaliacaoAntropometricaBuilder<PARENT> altura(Date dataEvento, String altura) {
		new AlturaComprimentoBuilder<>(this, dataEvento, altura);
		return this;
	}

	public AvaliacaoAntropometricaBuilder<PARENT> perimetroCefalico(Date dataEvento, String perimetroCefalico) {
		new PerimetroCefalicoBuilder<>(this, dataEvento, perimetroCefalico);
		return this;
	}

}
