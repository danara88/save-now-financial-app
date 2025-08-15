package com.savenow.shared.interfaces.box;

import com.savenow.model.Box;
import com.savenow.model.exceptions.box.AlreadyExistingBoxException;
import com.savenow.shared.common.exceptions.DataValidationException;
import com.savenow.shared.common.interfaces.IController;
import java.util.List;

/**
 * Represents the interface for box controller.
 */
public interface IBoxController extends IController {
	void addBox(String name, String description, double initialAmount) throws DataValidationException, AlreadyExistingBoxException;

	List<Box> listBoxes();
}
