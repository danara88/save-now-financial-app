package com.savenow.shared.common.exceptions;

/**
 * Throw when a requested resource cannot be found.
 */
public class ResourceNotFoundException extends Exception {

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
