package br.ufsc.bridge.res.service.exception;

import java.util.Map;

import lombok.Getter;

@Getter
public class ResUrlsException extends ResException {
	private static final long serialVersionUID = 1L;
	private Map<String, ResException> exceptions;

	public ResUrlsException(Map<String, ResException> exceptions) {
		super("Alguns links apresentam problemas ao enviar");
		this.exceptions = exceptions;
	}
}