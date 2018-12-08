package com.example.atm.exceptions;

// unchecked exception
public class AccountNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -8939059715610029699L;

	public AccountNotFoundException(String message) {
		super(message);
	}

	public AccountNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountNotFoundException(Throwable cause) {
		super(cause);
	}
}
