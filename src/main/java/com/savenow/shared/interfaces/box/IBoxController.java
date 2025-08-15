package com.savenow.shared.interfaces.box;

import java.util.List;

import com.savenow.model.Box;
import com.savenow.model.exceptions.box.AlreadyExistingBoxException;
import com.savenow.model.exceptions.box.BoxNotFoundException;
import com.savenow.shared.common.exceptions.DataValidationException;
import com.savenow.shared.common.interfaces.IController;

/**
 * Represents the interface for box controller.
 */
public interface IBoxController extends IController {
	void addBox(String name, String description, double initialAmount) throws DataValidationException, AlreadyExistingBoxException;

	List<Box> listBoxes();

	Box getBoxById(String id) throws BoxNotFoundException;

	void updateBox(String id, String name, String description) throws DataValidationException, BoxNotFoundException;

	void deleteBox(String id) throws BoxNotFoundException, DataValidationException;
}
