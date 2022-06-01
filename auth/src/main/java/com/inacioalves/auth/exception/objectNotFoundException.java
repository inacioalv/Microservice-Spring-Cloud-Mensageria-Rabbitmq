package com.inacioalves.auth.exception;

public class objectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 705499077807853414L;

	public objectNotFoundException(String msg) {
		super(msg);
	}
	
	public objectNotFoundException(String msg, Throwable causa) {
		super(msg,causa);
	}
}
