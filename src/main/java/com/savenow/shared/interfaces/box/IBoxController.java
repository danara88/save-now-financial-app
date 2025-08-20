package com.savenow.shared.interfaces.box;

import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import java.util.List;

import com.savenow.model.Box;
import com.savenow.shared.common.exceptions.DataValidationException;
import com.savenow.shared.common.interfaces.IController;

/**
 * Represents the interface for box controller.
 */
public interface IBoxController extends IController {
	void addBox(String name, String description, double initialAmount) throws DataValidationException, ResourceAlreadyExistsException;

	List<Box> listBoxes();

	Box getBoxById(String id) throws ResourceNotFoundException;

	void updateBox(String id, String name, String description, Double totalAmount) throws DataValidationException, ResourceNotFoundException;

	void deleteBox(String id) throws ResourceNotFoundException, DataValidationException;
}
