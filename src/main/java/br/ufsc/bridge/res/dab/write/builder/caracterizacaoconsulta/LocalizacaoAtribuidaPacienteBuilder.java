package br.ufsc.bridge.res.dab.write.builder.caracterizacaoconsulta;

import lombok.Getter;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

@Getter
public class LocalizacaoAtribuidaPacienteBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String cnes;

	private String ine;

	public LocalizacaoAtribuidaPacienteBuilder(PARENT parent, String cnes, String ine) {
		super(parent);
		this.cnes = cnes;
		this.ine = ine;
	}

	@Override
	protected String openTags() {
		return "<Localização_atribuída_ao_paciente><name><value>Localização atribuída ao paciente</value></name><Local_de_atendimento><name><value>Local de atendimento</value></name><value><value>No próprio estabelecimento</value><defining_code><terminology_id><value>local</value></terminology_id><code_string>at0.177</code_string></defining_code></value></Local_de_atendimento><Instituição><name><value>Instituição</value></name><Estabelecimento_de_saúde><name><value>Estabelecimento de saúde</value></name><value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></Identificação_da_equipe_de_saúde></Instituição></Localização_atribuída_ao_paciente>";
	}

	@Override
	public String getValue() {
		return this.cnes + this.closeCnes() + this.ine;
	}

	private String closeCnes() {
		return "</oe:value></value></Estabelecimento_de_saúde><Identificação_da_equipe_de_saúde><name><value>Identificação da equipe de saúde</value></name><value><oe:value>";
	}
}
