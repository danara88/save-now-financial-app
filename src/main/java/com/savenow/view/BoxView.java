package com.savenow.view;

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
		String boxName = PromptUtils.inputString("Enter box name", "name");
		String boxDescription = PromptUtils.inputString("Enter box description", "description");
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
		List<Box> boxes = _boxController.listBoxes();

		Locale locale = Locale.forLanguageTag("en-US");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

		System.out.printf("%-40s %-20s %-20s %-10s%n", "ID", "Box name", "Amount ($)", "Creation Date");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
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
