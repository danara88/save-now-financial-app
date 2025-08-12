package com.savenow.controller;

import com.savenow.shared.interfaces.box.IBoxController;
import com.savenow.shared.interfaces.box.IBoxRepository;

/**
 * Represents the implementation of the box controller
 */
public class BoxController implements IBoxController {
	private final IBoxRepository _boxRepository;

	public BoxController(IBoxRepository boxRepository) {
		_boxRepository = boxRepository;
	}

	public void listBoxes() {
		System.out.println("List boxes...");
	}
}
