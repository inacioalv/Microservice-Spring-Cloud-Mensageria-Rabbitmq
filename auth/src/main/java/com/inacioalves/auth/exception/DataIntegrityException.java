package com.inacioalves.auth.exception;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -4448657475669734825L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
