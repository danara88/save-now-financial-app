package com.savenow.view;

import com.savenow.shared.common.interfaces.IView;
import com.savenow.shared.interfaces.box.IBoxController;

/**
 * In charge of rendering views related with boxes
 */
public class BoxView implements IView {
	private final IBoxController _boxController;

	public BoxView(IBoxController boxController) {
		_boxController = boxController;
	}

	public void addBoxView() {}

	public void updateBoxView() {}

	public void deleteBoxView() {}

	public void listBoxView() {
		_boxController.listBoxes();
	}

	public void getBoxView() {}

	@Override
	public String toString() {
		return "BoxView";
	}
}
