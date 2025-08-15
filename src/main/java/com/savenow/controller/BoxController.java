package com.savenow.controller;

import java.util.List;

import com.savenow.model.Box;
import com.savenow.model.exceptions.box.AlreadyExistingBoxException;
import com.savenow.shared.common.exceptions.DataValidationException;
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

	@Override
	public void addBox(String name, String description, double initialAmount) throws DataValidationException, AlreadyExistingBoxException {
		validateData(name, description);
		Box box = Box.createBox(name, description, initialAmount);
		_boxRepository.create(box);
	}

	@Override
	public List<Box> listBoxes() {
		return _boxRepository.getAll();
	}

	/**
	 * Validated inputed data to create a box
	 * @param name name of the box
	 * @param description description of the box
	 * @throws DataValidationException checked exception
	 */
	private void validateData(String name, String description) throws DataValidationException {
		if(name.isEmpty()) {
			throw new DataValidationException("ERROR: Box name is required.");
		}

		if(description.isEmpty()) {
			throw new DataValidationException("ERROR: Box description is required.");
		}
	}
}
