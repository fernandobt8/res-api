package br.ufsc.bridge.res.dab;

import java.util.Date;

import br.ufsc.bridge.res.dab.domain.JsonPathValueConverter;
import br.ufsc.bridge.res.util.RDateUtil;

public class DateJsonPathValueConverter implements JsonPathValueConverter<Date> {
	@Override
	public Date convert(String value) {
		return  RDateUtil.isoEHRToDate(value);
	}
}
