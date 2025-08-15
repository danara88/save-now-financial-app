package com.savenow.model.exceptions.box;

/**
 * Represents a checked exception when a box was not found in the database.
 */
public class BoxNotFoundException extends Exception {
	public BoxNotFoundException() {
		super("ERROR: Box does not exist.");
	}
	public BoxNotFoundException(String message) {
		super(message);
	}
}
