package com.savenow.shared.interfaces.transaction;

import java.util.List;

import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.model.Transaction;
import com.savenow.model.enums.TransactionType;
import com.savenow.shared.common.interfaces.IRepository;

/**
 * Represents the interface for transaction repository.
 */
public interface ITransactionRepository extends IRepository {
	void create(Transaction transaction) throws ResourceAlreadyExistsException;

	List<Transaction> getAll();

	Transaction getById(String id) throws ResourceNotFoundException;

	void update(Transaction transactionToUpdate) throws ResourceNotFoundException;

	void deleteById(String id) throws ResourceNotFoundException;
}
