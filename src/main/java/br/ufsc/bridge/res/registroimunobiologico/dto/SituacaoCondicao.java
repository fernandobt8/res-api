package br.ufsc.bridge.res.registroimunobiologico.dto;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
public enum SituacaoCondicao {

	GESTANTE("Gestante"),
	PUERPERA("Puérpera"),
	VIAJANTE("Viajante"),
	COMUNICANTE_HANSENIASE("Comunicante hanseníase"),
	;

	private String value;

	public static class JsonConverter implements JsonPathValueConverter<SituacaoCondicao, String> {

		@Override
		public SituacaoCondicao convert(String value) {
			if(value == null) {
				return null;
			}
			for(SituacaoCondicao situacaoCondicao: SituacaoCondicao.values()) {
				if(situacaoCondicao.value.toLowerCase().equals(value.toLowerCase())) {
					return situacaoCondicao;
				}
			}
			return null;
		}
	}

}
