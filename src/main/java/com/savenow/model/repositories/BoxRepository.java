package com.savenow.model.repositories;

import java.util.List;

import com.savenow.model.Box;
import com.savenow.model.exceptions.box.AlreadyExistingBoxException;
import com.savenow.persistence.BoxPersistence;
import com.savenow.shared.interfaces.box.IBoxRepository;

/**
 * Represents the implementation of the box repository
 */
public class BoxRepository implements IBoxRepository {
	private final List<Box> boxes;

	public BoxRepository() {
		boxes = BoxPersistence.loadBoxes();
	}

	/**
	 * Method in charge of save a box in the database.
	 * @param box represents the new box created.
	 * @throws AlreadyExistingBoxException represents a checked exception when trying to add duplicate boxes.
	 */
	@Override
	public void create(Box box) throws AlreadyExistingBoxException {
		if(boxes.contains(box)) {
			throw new AlreadyExistingBoxException();
		}
		boxes.add(box);
		BoxPersistence.saveBoxes(boxes);
	}

	/**
	 * Method in charge of getting all the boxes from database.
	 * @return a list of boxes saved in database.
	 */
	@Override
	public List<Box> getAll() {
		return BoxPersistence.loadBoxes();
	}
}
