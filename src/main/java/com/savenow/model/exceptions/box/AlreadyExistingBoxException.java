package com.savenow.model.exceptions.box;

/**
 * Represents a checked exception when trying to add an existing box into the database.
 */
public class AlreadyExistingBoxException extends Exception {
	public AlreadyExistingBoxException() {
		super("ERROR: Box already exists.");
	}

	public AlreadyExistingBoxException(String message) {
		super(message);
	}
}
