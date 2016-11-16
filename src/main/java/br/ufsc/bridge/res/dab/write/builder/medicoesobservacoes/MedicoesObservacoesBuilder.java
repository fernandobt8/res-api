package br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes;

import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.avaliacao.AvaliacaoAntropometricaBuilder;
import br.ufsc.bridge.res.dab.write.builder.medicoesobservacoes.gestante.GestanteBuilder;

public class MedicoesObservacoesBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public MedicoesObservacoesBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Medições_e_observações><name><value>Medições e observações</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Medições_e_observações>";
	}

	public AvaliacaoAntropometricaBuilder<MedicoesObservacoesBuilder<PARENT>> avaliacaoAntropometrica() {
		return new AvaliacaoAntropometricaBuilder<>(this);
	}

	public GestanteBuilder<MedicoesObservacoesBuilder<PARENT>> gestante() {
		return new GestanteBuilder<>(this);
	}

}
