package com.savenow.view;

import java.util.List;

import com.savenow.model.Transaction;
import com.savenow.model.enums.TransactionType;
import com.savenow.shared.common.exceptions.DataValidationException;
import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.model.Box;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.shared.common.interfaces.IView;
import com.savenow.shared.interfaces.box.IBoxController;
import com.savenow.shared.interfaces.transaction.ITransactionController;
import com.savenow.view.uiUtils.PromptUtils;
import com.savenow.view.uiUtils.UiConstants;
import com.savenow.view.uiUtils.UiHelpers;

/**
 * Represent all the views/presentation for transactions
 */
public class TransactionView implements IView {
	private final IBoxController _boxController;
	private final ITransactionController _transactionController;

	public TransactionView(IBoxController boxController, ITransactionController transactionController) {
		_boxController = boxController;
		_transactionController = transactionController;
	}

	/**
	 * In charge of rendering the view to create a transaction.
	 */
	public void addTransactionView() {
		System.out.println(UiConstants.createTransactionTitle);
		System.out.println();
		List<Box> boxes = _boxController.listBoxes();
		UiHelpers.printUiTable(boxes, Box.class);
		System.out.println();

		Box box;
		String boxId;
		TransactionType transactionType;
		double amount;

		// Get box id
		do {
			boxId = PromptUtils.inputString("Enter box id", "id", true);
			box = tryGetBoxById(boxId);
			if (box == null) {
				System.out.println(
					UiConstants.RED_COLOR + "Box with id " + boxId + " does not exist. Please enter an existing box id." + UiConstants.RESET_COLOR);
			}
		} while (box == null);

		// Get transaction type
		String transactionTypeInput;
		do {
			transactionTypeInput = PromptUtils.inputString("What type of transaction do you want to make? (0 - Deposit, 1 - Withdraw)", "type", true);
			transactionType = getTransactionTypeFromString(transactionTypeInput);
			if(isInvalidInputTransactionType(transactionTypeInput)) {
				System.out.println(
					UiConstants.RED_COLOR + "Invalid option. Please insert 0 for a deposit or 1 for a withdraw." + UiConstants.RESET_COLOR);
			}
		} while (isInvalidInputTransactionType(transactionTypeInput));

		String reason = PromptUtils.inputString("What is the reason for this transaction?", "reason", true);

		String promptAmountWithdraw = "Enter the amount you want to withdraw";
		String promptAmountDeposit = "Enter the amount you want to deposit";

		// Get transaction amount
		do {
			amount = PromptUtils.inputDouble(transactionType == TransactionType.INCOME ? promptAmountDeposit : promptAmountWithdraw, "amount", true);
			if (isInvalidInputAmount(amount, box, transactionType)) {
				if (amount > box.getTotalAmount() && transactionType == TransactionType.WITHDRAW) {
					System.out.println(
						UiConstants.RED_COLOR + "Your box '" + box.getName() +"' has a total amount of " + UiHelpers.fromDoubleToFormattedCurrency(box.getTotalAmount()) + ". You cannot withdraw more than this amount." + UiConstants.RESET_COLOR);
				}
				if (amount < 0) {
					System.out.println(
						UiConstants.RED_COLOR + "ERROR: Amount should be greatter than 0." + UiConstants.RESET_COLOR);
				}
			}
		} while (isInvalidInputAmount(amount, box, transactionType));

		try {
			_transactionController.addTransaction(box.getId(), box.getName(), reason, amount, transactionType);
			System.out.println(UiConstants.resourceCreatedSuccess);
			System.out.println();
		} catch (DataValidationException | ResourceAlreadyExistsException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
		} catch (Exception e) {
			System.out.println(UiConstants.defaultErrorMessage);
		}
	}

	/**
	 * In charge of displaying the view for transaction listing.
	 */
	public void listTransactionsView() {
		System.out.println(UiConstants.getAllMovementsTitle);
		List<Transaction> transactions = _transactionController.listTransactions();
		UiHelpers.printUiTable(transactions, Transaction.class);
	}

	/**
	 * Method in charge of validating the input set by the user for amount.
	 * @param amount represents the amount setted by the user.
	 * @param box represents the box to get the total amount.
	 * @return returns TRUE if inserted value is invalid or FALSE if it is valid.
	 */
	private boolean isInvalidInputAmount(Double amount, Box box, TransactionType transactionType) {
		return amount == null || amount <= 0 || (transactionType == TransactionType.WITHDRAW && amount > box.getTotalAmount());
	}

	/**
	 * Method in charge of validating the input set by the user for transaction type.
	 * @param input represents the input set by the user.
	 * @return returns TRUE if inserted value is invalid or FALSE if it is valid.
	 */
	private boolean isInvalidInputTransactionType(String input) {
		return input.isEmpty() || (!input.equals("0") && !input.equals("1"));
	}

	/**
	 * Method in charge of getting the transaction type from a string.
	 * @param transactionTypeInput represents the input set by the user.
	 * @return returns a TransactionType value from enumeration.
	 */
	private TransactionType getTransactionTypeFromString(String transactionTypeInput) {
		if(!isInvalidInputTransactionType(transactionTypeInput)) {
			return transactionTypeInput.equals("0") ? TransactionType.INCOME : TransactionType.WITHDRAW;
		}
		return TransactionType.INCOME;
	}

	/**
	 * Method in charge of validation the box id setted by the user.
	 * @param boxId represents the Box id.
	 * @return return true if valdiations failed and false if validations succeed.
	 */
	private Box tryGetBoxById(String boxId) {
		if (boxId.isEmpty()) {
			return null;
		}
		try {
			return _boxController.getBoxById(boxId);
		} catch (ResourceNotFoundException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "TransactionView";
	}
}
