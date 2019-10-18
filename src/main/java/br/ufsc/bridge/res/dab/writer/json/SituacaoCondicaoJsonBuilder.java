package br.ufsc.bridge.res.dab.writer.json;

import br.ufsc.bridge.res.registroimunobiologico.dto.SituacaoCondicao;
import br.ufsc.bridge.res.util.json.BaseJsonBuilder;

public class SituacaoCondicaoJsonBuilder<T extends BaseJsonBuilder<?>> extends BaseJsonBuilder<T> {

	public SituacaoCondicaoJsonBuilder(T parent) {
		super(parent, "situacao-condicao");
	}

	public SituacaoCondicaoJsonBuilder<T> situacao(SituacaoCondicao value) {
		if (value != null) {
			this.document.set("$.value.value", value.getValue());
		}
		return this;
	}
}
