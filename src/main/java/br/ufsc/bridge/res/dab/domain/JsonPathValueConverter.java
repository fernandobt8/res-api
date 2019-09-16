package br.ufsc.bridge.res.dab.domain;

public interface JsonPathValueConverter<T> {

	public T convert(String value);

}
