package br.ufsc.bridge.res.dab.reader;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class EHRReader<T> {

	private Class<T> objClass;

	public EHRRoot<T> from(String root) {
		return new EHRRoot<T>(root, this.objClass);
	}

}
