package com.savenow.shared.interfaces.box;

import java.util.List;

import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.model.Box;
import com.savenow.shared.common.interfaces.IRepository;

/**
 * Represents the interface for box repository.
 */
public interface IBoxRepository extends IRepository {
	void create(Box box) throws ResourceAlreadyExistsException;

	List<Box> getAll();

	Box getById(String id) throws ResourceNotFoundException;

	void updateById(String id, String name, String description, double totalAmount) throws ResourceNotFoundException;

	void deleteById(String id) throws ResourceNotFoundException;
}
