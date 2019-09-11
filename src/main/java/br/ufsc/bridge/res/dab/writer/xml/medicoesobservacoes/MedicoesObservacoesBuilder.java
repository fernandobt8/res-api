package br.ufsc.bridge.res.dab.writer.xml.medicoesobservacoes;

import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;
import br.ufsc.bridge.res.dab.writer.xml.medicoesobservacoes.avaliacao.AvaliacaoAntropometricaBuilder;

public class MedicoesObservacoesBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public MedicoesObservacoesBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Observações><name><value>Observações</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Observações>";
	}

	public AvaliacaoAntropometricaBuilder<MedicoesObservacoesBuilder<PARENT>> avaliacaoAntropometrica() {
		return new AvaliacaoAntropometricaBuilder<>(this);
	}

	//	public GestanteBuilder<MedicoesObservacoesBuilder<PARENT>> gestante() {
	//		return new GestanteBuilder<>(this);
	//	}
	//
	//	public CriancaBuilder<MedicoesObservacoesBuilder<PARENT>> crianca() {
	//		return new CriancaBuilder<>(this);
	//	}

}
