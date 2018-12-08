package com.example.atm.exceptions;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = -8768213662322294614L;

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public ApplicationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ApplicationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
