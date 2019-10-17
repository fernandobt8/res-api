package br.ufsc.bridge.res.dab.domain;

import lombok.AllArgsConstructor;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

@AllArgsConstructor
public enum RacaCor {

	BRANCA("Branca"),
	PRETA("Preta"),
	PARDA("Parda"),
	AMARELA("Amarela"),
	INDIGENA("Indígena"),
	SEM_INFORMACAO("Sem informação");

	private String value;

	public static class JsonConverter implements JsonPathValueConverter<RacaCor, String> {

		@Override
		public RacaCor convert(String value) {
			if(value == null) {
				return null;
			}
			for(RacaCor racaCor: RacaCor.values()) {
				if(racaCor.value.toLowerCase().equals(value.toLowerCase())) {
					return racaCor;
				}
			}
			return null;
		}
	}

}
