package com.savenow.shared.interfaces.box;

import com.savenow.model.Box;
import com.savenow.model.exceptions.box.AlreadyExistingBoxException;
import com.savenow.shared.common.interfaces.IRepository;
import java.util.List;

/**
 * Represents the interface for box repository.
 */
public interface IBoxRepository extends IRepository {
	void create(Box box) throws AlreadyExistingBoxException;

	List<Box> getAll();
}
