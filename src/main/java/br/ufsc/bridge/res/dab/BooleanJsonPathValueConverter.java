package br.ufsc.bridge.res.dab;

import java.util.Date;

import br.ufsc.bridge.res.dab.domain.JsonPathValueConverter;
import br.ufsc.bridge.res.util.RDateUtil;

public class BooleanJsonPathValueConverter implements JsonPathValueConverter<Boolean> {
	@Override
	public Boolean convert(String value) {
		return "true".equals(value);
	}
}
