package com.savenow.controller;

import com.savenow.model.Box;
import com.savenow.model.Transaction;
import com.savenow.model.enums.TransactionType;
import com.savenow.shared.common.exceptions.DataValidationException;
import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.interfaces.box.IBoxRepository;
import com.savenow.shared.interfaces.transaction.ITransactionController;
import com.savenow.shared.interfaces.transaction.ITransactionRepository;
import java.util.List;

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
	public void addTransaction(String boxId, String boxName, String reason, Double amount, TransactionType type)
		throws DataValidationException, ResourceAlreadyExistsException {
		validateData(boxId, boxName, reason, amount, type);
		Transaction transaction = Transaction.createTransaction(boxId, boxName, reason, amount, type);
		Box box = _boxRepository.getById(boxId);
		if (transaction.getType() == TransactionType.INCOME) {
			box.totalAmount +=  amount;
		}
		if (transaction.getType() == TransactionType.WITHDRAW) {
			box.totalAmount -=  amount;
		}
		_boxRepository.updateById(box.getId(), box.getName(), box.getDescription(), box.getTotalAmount());
		_transactionRepository.create(transaction);
	}

	@Override
	public List<Transaction> listTransactions() {
		return _transactionRepository.getAll();
	}

	/**
	 * In charge of validating the input data by the user
	 * @param boxId represents the box id.
	 * @param reason represents the transaction reason.
	 * @param amount represents the amount from the transaction.
	 * @param transactionType represents the transaction type (income/withdraw).
	 * @throws DataValidationException exception throwed when a validation fails.
	 */
	private void validateData(String boxId, String boxName, String reason, Double amount, TransactionType transactionType) throws DataValidationException {
		if (boxId.isEmpty()) {
			throw new DataValidationException("ERROR: Box id is required.");
		}

		if (boxName.isEmpty()) {
			throw new DataValidationException("ERROR: Box name is required.");
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
}
