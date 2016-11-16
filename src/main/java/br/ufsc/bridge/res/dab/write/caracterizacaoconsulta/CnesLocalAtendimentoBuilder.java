package br.ufsc.bridge.res.dab.write.caracterizacaoconsulta;

import lombok.Getter;

import br.ufsc.bridge.res.dab.write.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.base.ParentArquetypeWrapper;

@Getter
public class CnesLocalAtendimentoBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String value;

	public CnesLocalAtendimentoBuilder(PARENT parent, String cnes) {
		super(parent);
		this.value = cnes;
	}

	@Override
	protected String openTags() {
		return "<Localização_atribuída_ao_paciente><name><value>Localização atribuída ao paciente</value></name><Estabelecimento_de_saúde><name><value>Estabelecimento de saúde</value></name>"
				+ "<CNES_do_local_do_atendimento><name><value>CNES do local do atendimento</value></name><value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></CNES_do_local_do_atendimento></Estabelecimento_de_saúde></Localização_atribuída_ao_paciente>";
	}
}
