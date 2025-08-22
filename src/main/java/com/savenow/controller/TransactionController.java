package com.savenow.controller;

import java.util.List;

import com.savenow.model.Box;
import com.savenow.model.Transaction;
import com.savenow.model.enums.TransactionType;
import com.savenow.shared.common.exceptions.DataValidationException;
import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.shared.interfaces.box.IBoxRepository;
import com.savenow.shared.interfaces.transaction.ITransactionController;
import com.savenow.shared.interfaces.transaction.ITransactionRepository;

/**
 * Represents the transaction controller implementation
 */
public class TransactionController implements ITransactionController {
	private final ITransactionRepository _transactionRepository;
	private final IBoxRepository _boxRepository;

	public TransactionController(ITransactionRepository transactionRepository, IBoxRepository boxRepository) {
		_transactionRepository = transactionRepository;
		_boxRepository = boxRepository;
	}

	@Override
	public void addTransaction(String boxId, String reason, Double amount, TransactionType type)
		throws DataValidationException, ResourceAlreadyExistsException, ResourceNotFoundException {
		validateData(boxId, reason, amount, type);
		Box box = _boxRepository.getById(boxId);
		Transaction transaction = Transaction.createTransaction(boxId, box.getName(), reason, amount, type);

		double boxTotalAmount = box.getTotalAmount();
		if (transaction.getType() == TransactionType.INCOME) {
			boxTotalAmount += amount;
		}
		if (transaction.getType() == TransactionType.WITHDRAW) {
			boxTotalAmount -= amount;
		}
		box.setTotalAmount(boxTotalAmount);

		_boxRepository.update(box);
		_transactionRepository.create(transaction);
	}

	@Override
	public List<Transaction> listTransactions() {
		return _transactionRepository.getAll();
	}

	@Override
	public Transaction getTransactionById(String id) throws ResourceNotFoundException {
		return _transactionRepository.getById(id);
	}

	@Override
	public void updateTransaction(String boxId, String transactionId, String reason, Double amount)
		throws DataValidationException, ResourceNotFoundException {
		this.validateData(boxId, transactionId, reason, amount);

		Box existingBox = _boxRepository.getById(boxId);
		Transaction exisitngTransaction = _transactionRepository.getById(transactionId);

		if (exisitngTransaction.getAmount() != amount) {
			double boxTotalAmount = existingBox.getTotalAmount();
			if (exisitngTransaction.getType() == TransactionType.INCOME) {
				boxTotalAmount -= exisitngTransaction.getAmount();
				boxTotalAmount += amount;
			}
			if (exisitngTransaction.getType() == TransactionType.WITHDRAW) {
				boxTotalAmount += exisitngTransaction.getAmount();
				boxTotalAmount -= amount;
			}
			existingBox.setTotalAmount(boxTotalAmount);
		}

		exisitngTransaction.setReason(reason);
		exisitngTransaction.setAmount(amount);

		_boxRepository.update(existingBox);
		_transactionRepository.update(exisitngTransaction);
	}

	@Override
	public void deleteTransaction(String id) throws ResourceNotFoundException, DataValidationException {
		if (id.isEmpty()) {
			throw new DataValidationException("ERROR: Transaction id is required.");
		}

		Transaction transactionToDelete = _transactionRepository.getById(id);
		Box box = _boxRepository.getById(transactionToDelete.getBoxId());
		double boxTotalAmount = box.getTotalAmount();

		if (transactionToDelete.getType() == TransactionType.INCOME) {
			boxTotalAmount -= transactionToDelete.getAmount();
		}

		if (transactionToDelete.getType() == TransactionType.WITHDRAW) {
			boxTotalAmount += transactionToDelete.getAmount();
		}

		box.setTotalAmount(boxTotalAmount);

		_boxRepository.update(box);
		_transactionRepository.deleteById(id);
	}

	/**
	 * In charge of validating the input data by the user
	 * @param boxId represents the box id.
	 * @param reason represents the transaction reason.
	 * @param amount represents the amount from the transaction.
	 * @param transactionType represents the transaction type (income/withdraw).
	 * @throws DataValidationException exception throwed when a validation fails.
	 */
	private void validateData(String boxId, String reason, Double amount, TransactionType transactionType) throws DataValidationException {
		if (boxId.isEmpty()) {
			throw new DataValidationException("ERROR: Box id is required.");
		}

		if (reason.isEmpty()) {
			throw new DataValidationException("ERROR: Transaction reason is required.");
		}

		if (amount == null) {
			throw new DataValidationException("ERROR: Amount is required.");
		}

		if(amount < 0) {
			throw new DataValidationException("ERROR: Amount must be a greatter than 0");
		}

		if (transactionType == null) {
			throw new DataValidationException("ERROR: Transaction type is required.");
		}
	}

	/**
	 * In charge of validating the input data by the user
	 * @param boxId represents the box id.
	 * @param reason represents the transaction reason.
	 * @param amount represents the amount from the transaction.
	 * @throws DataValidationException exception throwed when a validation fails.
	 */
	private void validateData(String boxId, String transactionId, String reason, Double amount) throws DataValidationException {
		if (boxId.isEmpty()) {
			throw new DataValidationException("ERROR: Box id is required.");
		}

		if (transactionId.isEmpty()) {
			throw new DataValidationException("ERROR: Transaction id is required.");
		}

		if (reason.isEmpty()) {
			throw new DataValidationException("ERROR: Transaction reason is required.");
		}

		if (amount == null) {
			throw new DataValidationException("ERROR: Amount is required.");
		}

		if(amount < 0) {
			throw new DataValidationException("ERROR: Amount must be a greatter than 0");
		}
	}
}
