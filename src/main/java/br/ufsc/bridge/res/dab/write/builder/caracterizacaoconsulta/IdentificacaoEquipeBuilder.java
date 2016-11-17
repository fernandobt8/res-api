package br.ufsc.bridge.res.dab.write.builder.caracterizacaoconsulta;

import lombok.Getter;

import br.ufsc.bridge.res.dab.write.builder.base.ArquetypeWrapper;
import br.ufsc.bridge.res.dab.write.builder.base.ParentArquetypeWrapper;

@Getter
public class IdentificacaoEquipeBuilder<PARENT extends ParentArquetypeWrapper<?>> extends ArquetypeWrapper<PARENT> {

	private String value;

	public IdentificacaoEquipeBuilder(PARENT parent, String value) {
		super(parent);
		this.value = value;
	}

	@Override
	protected String openTags() {
		return "<Identificação_da_equipe_de_saúde><name>" +
				"<value>Identificação da equipe de saúde</value>" +
				"</name><value><oe:value>";
	}

	@Override
	protected String closeTags() {
		return "</oe:value></value></Identificação_da_equipe_de_saúde>";
	}

}
