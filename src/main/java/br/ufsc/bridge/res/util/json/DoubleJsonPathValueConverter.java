package br.ufsc.bridge.res.util.json;

public class DoubleJsonPathValueConverter implements JsonPathValueConverter<Double, String> {

	@Override
	public Double convert(String value) {
		return new Double(value);
	}
}
