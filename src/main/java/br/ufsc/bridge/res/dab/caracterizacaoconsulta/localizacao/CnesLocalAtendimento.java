package br.ufsc.bridge.res.dab.caracterizacaoconsulta.localizacao;

import br.ufsc.bridge.res.repository.write.TypedArquetypeWrapper;

public class CnesLocalAtendimento extends TypedArquetypeWrapper<String> {

	public CnesLocalAtendimento(String value) {
		super(value);
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

	@Override
	protected String getContent() {
		return this.value;
	}

}
