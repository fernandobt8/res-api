package br.ufsc.bridge.res.service.soap.exception;

public class ResException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResException(String message) {
		super(message);
	}

	public ResException(Throwable cause) {
		super(cause);
	}
}
