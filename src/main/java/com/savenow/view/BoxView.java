package com.savenow.view;

import com.savenow.model.exceptions.box.BoxNotFoundException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.savenow.model.Box;
import com.savenow.model.exceptions.box.AlreadyExistingBoxException;
import com.savenow.shared.common.exceptions.DataValidationException;
import com.savenow.shared.common.interfaces.IView;
import com.savenow.shared.interfaces.box.IBoxController;
import com.savenow.view.uiUtils.PromptUtils;
import com.savenow.view.uiUtils.UiConstants;

/**
 * In charge of rendering views related with boxes
 */
public class BoxView implements IView {
	private final IBoxController _boxController;

	public BoxView(IBoxController boxController) {
		_boxController = boxController;
	}

	/**
	 * In charge of displaying the view to create a box.
	 */
	public void addBoxView() {
		System.out.println(UiConstants.createBoxTitle);
		String boxName = PromptUtils.inputString("Enter box name", "name", true);
		String boxDescription = PromptUtils.inputString("Enter box description", "description", true);
		double initialBoxAmount = PromptUtils.inputDouble("Enter initial box amount (type 0 to avoid adding initial amount)" , "amount");

		try {
			_boxController.addBox(boxName, boxDescription, initialBoxAmount);
			System.out.println(UiConstants.resourceCreatedSuccess);
			System.out.println();
		} catch (DataValidationException | AlreadyExistingBoxException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
		} catch (Exception e) {
			System.out.println(UiConstants.defaultErrorMessage);
		}
	}

	/**
	 * In charge of displaying the view for box listing.
	 */
	public void listBoxesView() {
		System.out.println(UiConstants.getAllBoxesTitle);
		printBoxesTableUi();
	}

	/**
	 * In charge of displaying the view for updating a box.
	 */
	public void updateBoxView() {
		System.out.println(UiConstants.updateBoxTitle);
		System.out.println();
		printBoxesTableUi();
		System.out.println();
		String boxId = PromptUtils.inputString("Enter box id to update", "id", true);
		Box boxDB = null;

		try {
			boxDB = _boxController.getBoxById(boxId);
		} catch (BoxNotFoundException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
			return;
		}

		String boxName = PromptUtils.inputString("Enter new box name (" + boxDB.getName() + "). Please press enter if not changes.", "name", false);
		String boxDescription = PromptUtils.inputString("Enter new box description (" + boxDB.getDescription() + "). Please press enter if not changes.", "description", false);

		try {
			_boxController.updateBox(boxId, !boxName.isEmpty() ? boxName : boxDB.getName(), !boxDescription.isEmpty() ? boxDescription : boxDB.getDescription());
			System.out.println(UiConstants.resourceUpdatedSuccess);
			System.out.println();
		} catch (DataValidationException | BoxNotFoundException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
		} catch (Exception e) {
			System.out.println(UiConstants.defaultErrorMessage);
		}
	}

	/**
	 * In charge of displaying the view for deleting a box.
	 */
	public void deleteBoxView() {
		System.out.println(UiConstants.deleteBoxTitle);
		System.out.println();
		printBoxesTableUi();
		System.out.println();
		String boxId = PromptUtils.inputString("Enter box id to delete", "id", true);

		try {
			_boxController.deleteBox(boxId);
			System.out.println(UiConstants.resourceDeletedSuccess);
			System.out.println();
		} catch (BoxNotFoundException | DataValidationException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
		} catch (Exception e) {
			System.out.println(UiConstants.defaultErrorMessage);
		}
	}

	/**
	 * In charge of printing a table lising boxes.
	 */
	private void printBoxesTableUi() {
		List<Box> boxes = _boxController.listBoxes();
		Locale locale = Locale.forLanguageTag("en-US");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

		if (boxes.isEmpty()) {
			System.out.println("No boxes found.");
			return;
		}

		System.out.printf("%-40s %-20s %-20s %-10s%n", "ID", "Box name", "Amount ($)", "Creation Date");
		System.out.println(
			"--------------------------------------------------------------------------------------------------------------------------------------");
		boxes.forEach((box) -> {
			System.out.printf("%-40s %-20s %-20s %-10s%n",
				box.getId(), box.getName(), numberFormat.format(box.getTotalAmount()), box.getCreatedAt());
		});
	}

	@Override
	public String toString() {
		return "BoxView";
	}
}
