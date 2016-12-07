package br.ufsc.bridge.res.http.exception;

public class ResHttpException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResHttpException(String error) {
		super(error);
	}

	public ResHttpException(String error, Throwable e) {
		super(error, e);
	}
}
