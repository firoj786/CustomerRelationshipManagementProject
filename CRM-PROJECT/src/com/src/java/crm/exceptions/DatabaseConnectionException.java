package com.src.java.crm.exceptions;

public class DatabaseConnectionException extends RuntimeException{
	public DatabaseConnectionException(String message) {
		super(message);
	}
}
