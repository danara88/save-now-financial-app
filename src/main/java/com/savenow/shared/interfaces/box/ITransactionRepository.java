package com.savenow.shared.interfaces.box;

import java.util.List;

import com.savenow.model.Transaction;
import com.savenow.model.enums.TransactionType;
import com.savenow.model.exceptions.box.AlreadyExistingBoxException;
import com.savenow.model.exceptions.box.BoxNotFoundException;
import com.savenow.shared.common.interfaces.IRepository;

/**
 * Represents the interface for transaction repository.
 */
public interface ITransactionRepository extends IRepository {
	void create(Transaction transaction) throws AlreadyExistingBoxException;

	List<Transaction> getAll();

	Transaction getById(String id) throws BoxNotFoundException;

	void updateById(String id, String reason, TransactionType type) throws BoxNotFoundException;

	void deleteById(String id) throws BoxNotFoundException;
}
