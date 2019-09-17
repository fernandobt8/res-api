package br.ufsc.bridge.res.dab;

import br.ufsc.bridge.res.dab.domain.JsonPathValueConverter;

public class BooleanJsonPathValueConverter implements JsonPathValueConverter<Boolean, String> {
	@Override
	public Boolean convert(String value) {
		return "true".equals(value);
	}
}
