package com.savenow.shared.common.exceptions;

/**
 * Throw when an input value from user is invalid.
 */
public class DataValidationException extends Exception {
	public DataValidationException(String message) {
		super(message);
	}
}
