package com.savenow.shared.interfaces.transaction;

import java.util.List;

import com.savenow.model.Transaction;
import com.savenow.model.enums.TransactionType;
import com.savenow.shared.common.exceptions.DataValidationException;
import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.interfaces.IController;

/**
 * Represents the interface for transaction controller.
 */
public interface ITransactionController extends IController {
	void addTransaction(String boxId, String boxName, String reason, Double amount, TransactionType type) throws DataValidationException, ResourceAlreadyExistsException;

	List<Transaction> listTransactions();
}
