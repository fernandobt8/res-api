package br.ufsc.bridge.res.dab.write.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class TypedArquetypeWrapper<T> extends ArquetypeWrapper {

	protected T value;

}
