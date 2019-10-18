package br.ufsc.bridge.res.dab.writer.json;

import br.ufsc.bridge.res.util.json.BaseJsonBuilder;

public class RegistroImunobiologicoJsonBuilder extends BaseJsonBuilder<RegistroImunobiologicoJsonBuilder> {

	public RegistroImunobiologicoJsonBuilder() {
		super("registro-imunobiologico");
	}

	@Override
	protected String childJsonPath() {
		return "$.content";
	}

	public CaracterizacaoAtendimentoJsonBuilder<RegistroImunobiologicoJsonBuilder> caracterizacaoAtendimento() {
		return new CaracterizacaoAtendimentoJsonBuilder<>(this);
	}

	public RegistroImunizacaoJsonBuilder<RegistroImunobiologicoJsonBuilder> registroImunizacao() {
		return new RegistroImunizacaoJsonBuilder<>(this);
	}

	//dataAdministracao
}
