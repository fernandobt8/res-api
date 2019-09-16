package br.ufsc.bridge.res.dab;

import br.ufsc.bridge.res.dab.domain.JsonPathValueConverter;

public class StringJsonPathValueConverter implements JsonPathValueConverter<String> {
	@Override
	public String convert(String value) {
		return value;
	}
}
