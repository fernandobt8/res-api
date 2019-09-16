package br.ufsc.bridge.res.util;

import br.ufsc.bridge.res.service.soap.exception.ResException;

public class ResABXMLParserException extends ResException {

	private static final long serialVersionUID = 1L;

	public ResABXMLParserException(String message, Throwable e) {
		super(message, e);
	}
}
