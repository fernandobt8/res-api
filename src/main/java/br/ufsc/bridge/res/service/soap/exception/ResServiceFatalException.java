package br.ufsc.bridge.res.service.soap.exception;

public class ResServiceFatalException extends ResException {

	private static final long serialVersionUID = 1L;

	public ResServiceFatalException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResServiceFatalException(String message) {
		super(message);
	}

	public ResServiceFatalException(Throwable cause) {
		super(cause);
	}
}
