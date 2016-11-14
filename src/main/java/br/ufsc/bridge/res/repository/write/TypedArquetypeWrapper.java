package br.ufsc.bridge.res.repository.write;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class TypedArquetypeWrapper<T> extends ArquetypeWrapper {

	protected T value;

}
