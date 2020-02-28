package com.src.java.crm.exceptions;

public class SessionExpiredException extends RuntimeException{
	public SessionExpiredException(String message) {
		super(message);
	}
}
