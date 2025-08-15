package com.savenow.controller;

import java.util.List;

import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.model.Box;
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
	public void addBox(String name, String description, double initialAmount) throws DataValidationException, ResourceAlreadyExistsException {
		validateData(name, description);
		Box box = Box.createBox(name, description, initialAmount);
		_boxRepository.create(box);
	}

	@Override
	public List<Box> listBoxes() {
		return _boxRepository.getAll();
	}

	@Override
	public void updateBox(String id, String name, String description) throws DataValidationException, ResourceNotFoundException {
		validateData(id, name, description);
		_boxRepository.updateById(id, name, description);
	}

	@Override
	public void deleteBox(String id) throws ResourceNotFoundException, DataValidationException {
		if(id.isEmpty()){
			throw new DataValidationException("ERROR: Box id is required.");
		}
		_boxRepository.deleteById(id);
	}

	@Override
	public Box getBoxById(String id) throws ResourceNotFoundException {
		return _boxRepository.getById(id);
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

	/**
	 * Validated inputed data to create a box
	 * @param id id of the box
	 * @param name name of the box
	 * @param description description of the box
	 * @throws DataValidationException checked exception
	 */
	private void validateData(String id, String name, String description) throws DataValidationException {
		if(id.isEmpty()) {
			throw new DataValidationException("ERROR: Box id is required.");
		}

		if(name.isEmpty()) {
			throw new DataValidationException("ERROR: Box name is required.");
		}

		if(description.isEmpty()) {
			throw new DataValidationException("ERROR: Box description is required.");
		}
	}
}
