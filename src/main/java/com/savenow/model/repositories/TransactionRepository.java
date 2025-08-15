package com.savenow.model.repositories;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.savenow.persistence.TransactionPersistence;
import com.savenow.model.Transaction;
import com.savenow.model.enums.TransactionType;
import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.shared.interfaces.box.ITransactionRepository;

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
	 * @param id represents the transaction id.
	 * @param reason represents the new transaction reason.
	 * @param type represents the new transaction type.
	 * @throws ResourceNotFoundException represents a checked exception when a transaction was not found.
	 */
	@Override
	public void updateById(String id, String reason, TransactionType type) throws ResourceNotFoundException {
		Transaction resourceToUpdate = getById(id);
		int index = getIndex(resourceToUpdate);

		resourceToUpdate.setReason(reason);
		resourceToUpdate.setType(type);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String updatedAt = LocalDateTime.now().format(formatter);
		resourceToUpdate.setUpdatedAt(updatedAt);

		transactions.set(index, resourceToUpdate);
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
	 * @param transaction represents the transaction to extract the index.
	 * @return the index of a transaction within an array of transactions.
	 */
	private int getIndex(Transaction transaction) {
		return transactions.indexOf(transaction);
	}
}
