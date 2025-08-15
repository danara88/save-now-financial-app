package com.savenow.shared.interfaces.box;

import java.util.List;

import com.savenow.model.Box;
import com.savenow.model.exceptions.box.AlreadyExistingBoxException;
import com.savenow.model.exceptions.box.BoxNotFoundException;
import com.savenow.shared.common.interfaces.IRepository;

/**
 * Represents the interface for box repository.
 */
public interface IBoxRepository extends IRepository {
	void create(Box box) throws AlreadyExistingBoxException;

	List<Box> getAll();

	Box getById(String id) throws BoxNotFoundException;

	void updateById(String id, String name, String description) throws BoxNotFoundException;

	void deleteById(String id) throws BoxNotFoundException;
}
