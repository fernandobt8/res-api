package br.ufsc.bridge.res.service.exception;

public class ResServiceSevereException extends ResException {
	private static final long serialVersionUID = 1L;

	public ResServiceSevereException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResServiceSevereException(String message) {
		super(message);
	}

	public ResServiceSevereException(Throwable cause) {
		super(cause);
	}
}
