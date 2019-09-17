package br.ufsc.bridge.res.dab.domain;

public interface JsonPathValueConverter<T, I> {

	T convert(I value);

}
