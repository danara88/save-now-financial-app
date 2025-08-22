package com.savenow.model.repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.savenow.view.uiUtils.UiHelpers;
import com.savenow.persistence.TransactionPersistence;
import com.savenow.model.Transaction;
import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.shared.interfaces.transaction.ITransactionRepository;

/**
 * Represents the implementation of the transaction repository
 */
public class TransactionRepository implements ITransactionRepository {
	private final List<Transaction> transactions;

	public TransactionRepository() {
		transactions = TransactionPersistence.loadTransactions();
	}

	/**
	 * Method in charge of saving a transaction in the database.
	 * @param transaction represents the transaction to be saved.
	 * @throws ResourceAlreadyExistsException represents a checked exception when trying to add duplicate transactions.
	 */
	@Override
	public void create(Transaction transaction) throws ResourceAlreadyExistsException {
		if (transactions.contains(transaction)) {
			throw new ResourceAlreadyExistsException("ERROR: Transaction with id " + transaction.getId() + "already exists.");
		}
		transactions.add(transaction);
		TransactionPersistence.saveTransactions(transactions);
	}

	/**
	 * Method in charge of getting all the transactions from database.
	 * @return a list of transactions saved in database.
	 */
	@Override
	public List<Transaction> getAll() {
		return transactions;
	}

	/**
	 * Method in charge of getting a transaction by id.
	 * @param id represents the transaction id.
	 * @return an existing transaction from database.
	 * @throws ResourceNotFoundException represents a checked exception when a transaction was not found.
	 */
	@Override
	public Transaction getById(String id) throws ResourceNotFoundException {
		Transaction transactionDB =  transactions.stream().filter(transaction -> transaction.getId().equals(id)).findFirst().orElse(null);
		if(transactionDB == null) {
			throw new ResourceNotFoundException("ERROR: Transaction with id " + id + " not found.");
		}
		return transactionDB;
	}

	/**
	 * Method in charge of updating a transaction by id.
	 * @param transactionToUpdate represents the transaction to be updated.
	 * @throws ResourceNotFoundException represents a checked exception when a transaction was not found.
	 */
	@Override
	public void update(Transaction transactionToUpdate) throws ResourceNotFoundException {
		int index = getIndexById(transactionToUpdate.getId());

		if (index < 0) {
			throw new ResourceNotFoundException("ERROR: Transaction with id " + transactionToUpdate.getId() + " not found.");
		}

		transactionToUpdate.setUpdatedAt(UiHelpers.fromLocalDateTimeToFormattedDateTime(LocalDateTime.now()));

		transactions.set(index, transactionToUpdate);
		TransactionPersistence.saveTransactions(transactions);
	}

	/**
	 * Method in charge of deleting a transaction from the database.
	 * @param id represents the transaction id to be deleted.
	 * @throws ResourceNotFoundException represents a checked exception when a transaction was not found.
	 */
	@Override
	public void deleteById(String id) throws ResourceNotFoundException {
		Transaction boxToDelete = getById(id);
		transactions.remove(boxToDelete);
		TransactionPersistence.saveTransactions(transactions);
	}

	/**
	 * In charge of getting the index number of a transaction.
	 * @param id represents the transaction id.
	 * @return the index of a transaction within an array of transactions.
	 */
	private int getIndexById(String id) throws ResourceNotFoundException {
		Transaction transaction = getById(id);
		return transactions.indexOf(transaction);
	}
}
