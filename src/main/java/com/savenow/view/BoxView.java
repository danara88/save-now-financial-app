package com.savenow.view;

import java.util.List;

import com.savenow.view.uiUtils.UiHelpers;
import com.savenow.shared.common.exceptions.ResourceAlreadyExistsException;
import com.savenow.shared.common.exceptions.ResourceNotFoundException;
import com.savenow.model.Box;
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
		double initialBoxAmount = PromptUtils.inputDouble("Enter initial box amount. Please press enter if not initial amount needed" , "amount", false);

		try {
			_boxController.addBox(boxName, boxDescription, initialBoxAmount);
			System.out.println(UiConstants.resourceCreatedSuccess);
			System.out.println();
		} catch (DataValidationException | ResourceAlreadyExistsException e) {
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
		UiHelpers.printUiTable(boxes, Box.class);
	}

	/**
	 * In charge of displaying the view for updating a box.
	 */
	public void updateBoxView() {
		System.out.println(UiConstants.updateBoxTitle);
		System.out.println();
		List<Box> boxes = _boxController.listBoxes();
		UiHelpers.printUiTable(boxes, Box.class);
		System.out.println();

		String boxId;

		do {
			boxId = PromptUtils.inputString("Enter box id to update", "id", true);
			if (!boxExists(boxId)) {
				System.out.println(
					UiConstants.RED_COLOR + "Box with id " + boxId + " does not exist. Please enter an existing id" + UiConstants.RESET_COLOR);
			}
		} while (!boxExists(boxId));

		try {
			Box boxDB = _boxController.getBoxById(boxId);
			String boxName = PromptUtils.inputString("Enter new box name (" + boxDB.getName() + "). Please press enter if not changes", "name", false);
			String boxDescription = PromptUtils.inputString("Enter new box description (" + boxDB.getDescription() + "). Please press enter if not changes", "description", false);
			_boxController.updateBox(boxId, !boxName.isEmpty() ? boxName : boxDB.getName(), !boxDescription.isEmpty() ? boxDescription : boxDB.getDescription(), boxDB.getTotalAmount());
			System.out.println(UiConstants.resourceUpdatedSuccess);
			System.out.println();
		} catch (DataValidationException | ResourceNotFoundException  e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
		} catch (Exception e) {
			System.out.println(UiConstants.defaultErrorMessage);
		}
	}

	/**
	 * Method in charge of verifying if a box exists
	 * @param boxId represents the box id.
	 * @return retruns true if dthe resource exists.
	 */
	private boolean boxExists(String boxId) {
		if (boxId.isEmpty()) {
			return false;
		}
		try {
			_boxController.getBoxById(boxId);
			return true;
		} catch (ResourceNotFoundException e) {
			return false;
		}
	}

	/**
	 * In charge of displaying the view for deleting a box.
	 */
	public void deleteBoxView() {
		System.out.println(UiConstants.deleteBoxTitle);
		System.out.println();
		List<Box> boxes = _boxController.listBoxes();
		UiHelpers.printUiTable(boxes, Box.class);
		System.out.println();
		String boxId = PromptUtils.inputString("Enter box id to delete", "id", true);

		try {
			_boxController.deleteBox(boxId);
			System.out.println(UiConstants.resourceDeletedSuccess);
			System.out.println();
		} catch (ResourceNotFoundException | DataValidationException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
		} catch (Exception e) {
			System.out.println(UiConstants.defaultErrorMessage);
		}
	}

	@Override
	public String toString() {
		return "BoxView";
	}
}
