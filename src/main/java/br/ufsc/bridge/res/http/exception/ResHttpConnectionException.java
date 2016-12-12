package br.ufsc.bridge.res.http.exception;

import br.ufsc.bridge.res.service.exception.ResException;

public class ResHttpConnectionException extends ResException {

	private static final long serialVersionUID = 1L;

	public ResHttpConnectionException(String message, Throwable cause) {
		super(message, cause);
	}
}
