package com.savenow.view.uiUtils;

/**
 * These are method helpers related with UI.
 */
public class UiHelpers {
	/**
	 * In chrage of cleaning the screen.
	 */
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
}
