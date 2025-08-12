package com.savenow.view;

import com.savenow.shared.common.interfaces.IView;
import com.savenow.view.uiUtils.MenuUtils;

/**
 * Represent all the views/presentation for Home
 */
public class HomeView implements IView {

	/**
	 * Display manin app view
	 */
	public void homeView() {
		MenuUtils.printBannerAndMenu();
	}

	@Override
	public String toString() {
		return "HomeView";
	}
}
