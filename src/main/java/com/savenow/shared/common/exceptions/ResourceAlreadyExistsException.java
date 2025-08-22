package com.savenow.shared.common.exceptions;

/**
 * Throw when a requested resource already exists.
 */
public class ResourceAlreadyExistsException extends Exception {

	public ResourceAlreadyExistsException(String message) {
		super(message);
	}
}
