package br.ufsc.bridge.res.service.exception;

public class ResHttpConnectionException extends ResException {
	private static final long serialVersionUID = 1L;

	public ResHttpConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResHttpConnectionException(String message) {
		super(message);
	}

	public ResHttpConnectionException(Throwable cause) {
		super(cause);
	}
}
