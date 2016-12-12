package br.ufsc.bridge.res.http.exception;

import br.ufsc.bridge.res.service.exception.ResException;

public class ResHttpRequestResponseException extends ResException {

	private static final long serialVersionUID = 1L;

	public ResHttpRequestResponseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResHttpRequestResponseException(String message) {
		super(message);
	}
}
