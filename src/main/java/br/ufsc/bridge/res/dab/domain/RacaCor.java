package br.ufsc.bridge.res.dab.domain;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

public enum RacaCor {

	BRANCA,
	PRETA,
	PARDA,
	AMARELA,
	INDIGENA,
	SEM_INFORMACAO;

	public static class JsonConverter implements JsonPathValueConverter<RacaCor, String> {

		@Override
		public RacaCor convert(String value) {
			return null;
		}
	}

}
