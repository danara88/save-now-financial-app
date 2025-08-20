package com.savenow.model.repositories;

import com.savenow.view.uiUtils.UiHelpers;
import java.time.LocalDateTime;
import java.util.List;

import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.model.Box;
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
	 * Method in charge of saving a box in the database.
	 * @param box represents the new box created.
	 * @throws ResourceAlreadyExistsException represents a checked exception when trying to add duplicate boxes.
	 */
	@Override
	public void create(Box box) throws ResourceAlreadyExistsException {
		if(boxes.contains(box)) {
			throw new ResourceAlreadyExistsException("ERROR: Box with name " + box.getName() + "already exists.");
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
		return boxes;
	}

	/**
	 * Method in charge of getting a box by id.
	 * @param id represents the box id.
	 * @return an existing box from database.
	 * @throws ResourceNotFoundException represents a checked exception when a box was not found.
	 */
	@Override
	public Box getById(String id) throws ResourceNotFoundException {
		Box boxDB = boxes.stream().filter(box -> box.getId().equals(id)).findFirst().orElse(null);
		if(boxDB == null) {
			throw new ResourceNotFoundException("ERROR: Box with id " + id + " not found.");
		}
		return boxDB;
	}

	/**
	 * Method in charge of updating a box.
	 * @param id represents the box id to update.
	 * @param name reprersents the new box name.
	 * @param description represents the new box description.
	 * @throws ResourceNotFoundException represents a checked exception when a Box was not found.
	 */
	@Override
	public void updateById(String id, String name, String description, double totalAmount) throws ResourceNotFoundException {
		Box boxToUpdate = getById(id);
		int index = getIndex(boxToUpdate);

		boxToUpdate.setName(name);
		boxToUpdate.setDescription(description);
		boxToUpdate.setTotalAmount(totalAmount);

		String updatedAt = UiHelpers.fromLocalDateTimeToFormattedDateTime(LocalDateTime.now());
		boxToUpdate.setUpdatedAt(updatedAt);

		boxes.set(index, boxToUpdate);
		BoxPersistence.saveBoxes(boxes);
	}

	/**
	 * Method in charge of deleting a box from the database.
	 * @param id represents the box id to be deleted.
	 * @throws ResourceNotFoundException represents a checked exception when a Box was not found.
	 */
	@Override
	public void deleteById(String id) throws ResourceNotFoundException {
		Box boxToDelete = getById(id);
		boxes.remove(boxToDelete);
		BoxPersistence.saveBoxes(boxes);
	}

	/**
	 * In charge of getting the index number of a box
	 * @param box represents the box to extract the index.
	 * @return the index of a box within an array of boxes
	 */
	private int getIndex(Box box) {
		return boxes.indexOf(box);
	}
}
