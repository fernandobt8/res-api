package br.ufsc.bridge.res.dab.writer.xml.alergia;

import br.ufsc.bridge.res.dab.writer.xml.base.ParentArquetypeWrapper;

public class AlergiaReacoesAdversasBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ParentArquetypeWrapper<PARENT> {

	public AlergiaReacoesAdversasBuilder(PARENT parent) {
		super(parent);
	}

	@Override
	protected String openTags() {
		return "<Alergias_e_fslash_ou_reações_adversas_no_atendimento><name><value>Alergias e/ou reações adversas no atendimento</value></name>";
	}

	@Override
	protected String closeTags() {
		return "</Alergias_e_fslash_ou_reações_adversas_no_atendimento>";
	}

	public RiscoReacaoAdversaBuilder<AlergiaReacoesAdversasBuilder<PARENT>> alergia() {
		return new RiscoReacaoAdversaBuilder<>(this);
	}
}
