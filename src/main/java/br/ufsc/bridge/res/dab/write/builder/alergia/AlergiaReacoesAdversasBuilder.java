package br.ufsc.bridge.res.dab.write.builder.alergia;

import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

public class AlergiaReacoesAdversasBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public AlergiaReacoesAdversasBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Alergias_e_reações_adversas><name><value>Alergias e reações adversas</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Alergias_e_reações_adversas>";
	}

	public RiscoReacaoAdversaBuilder<AlergiaReacoesAdversasBuilder<PARENT>> alergia() {
		return new RiscoReacaoAdversaBuilder<>(this);
	}
}
