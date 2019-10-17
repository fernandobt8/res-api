package br.ufsc.bridge.res.dab.domain;

import br.ufsc.bridge.res.util.json.JsonPathValueConverter;

public enum Sexo {

	MASCULINO,
	FEMININO,
	INDETERMINADO;

	public static class JsonConverter implements JsonPathValueConverter<Sexo, String> {

		@Override
		public Sexo convert(String value) {
			return null;
		}
	}

}
