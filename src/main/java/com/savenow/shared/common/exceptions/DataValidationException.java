package com.savenow.shared.common.exceptions;

/**
 * Represents a checked exception for user inputed data.
 * Throw this exception when trying to validate data comming from outside.
 */
public class DataValidationException extends Exception {

	public DataValidationException(String message) {
		super(message);
	}
}
