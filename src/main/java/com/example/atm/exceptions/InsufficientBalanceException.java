package com.example.atm.exceptions;

//checked exception
public class InsufficientBalanceException extends Exception{
	
	private static final long serialVersionUID = 1602513863185865244L;

	public InsufficientBalanceException(String message) {
		super(message);
	}

	public InsufficientBalanceException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientBalanceException(Throwable cause) {
		super(cause);
	}
}
