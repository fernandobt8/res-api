package br.ufsc.bridge.res.service.exception;

public class ResServerErrorException extends ResException {
	private static final long serialVersionUID = 1L;

	public ResServerErrorException(String message) {
		super(message);
	}
}
