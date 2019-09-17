package br.ufsc.bridge.res.dab;

import br.ufsc.bridge.res.dab.domain.JsonPathValueConverter;

public class DummyJsonPathValueConverter<T, I> implements JsonPathValueConverter<T, I> {
	@Override
	public T convert(I value) {
		return (T) value;
	}
}
