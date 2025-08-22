package com.savenow.view.uiUtils;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.savenow.model.Box;
import com.savenow.model.Transaction;
import com.savenow.model.common.BaseEntity;

/**
 * These are method helpers related with UI.
 */
@SuppressWarnings("unchecked")
public class UiHelpers {
	/**
	 * In chrage of cleaning the screen.
	 */
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/**
	 * In charge of converting a simple double value to a formatted US currency string.
	 *
	 * @param amount represents the amount to be formatted.
	 * @return a string with a formatted US currency.
	 */
	public static String fromDoubleToFormattedCurrency(double amount) {
		Locale locale = Locale.forLanguageTag("en-US");
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
		return numberFormat.format(amount);
	}

	/**
	 * In charge of converting a loca date time to a formatted string date time.
	 *
	 * @param localDateTime represents the inserted local date time.
	 * @return a string with a formatted date time.
	 */
	public static String fromLocalDateTimeToFormattedDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.now()
			.format(formatter);
	}

	/**
	 * In charge of printing the UI table for entities.
	 */
	public static <T extends BaseEntity> void printUiTable(List<T> entities, Class<T> type) {
		if (type == Box.class) {
			if (entities.isEmpty()) {
				System.out.println("No boxes created yet. Start by adding one !");
				return;
			}
			List<Box> boxes = (List<Box>) entities;
			System.out.printf("%-40s %-20s %-20s %-20s %-20s%n", "ID", "Box name", "Amount ($)", "Creation Date",
				"Updated Date");
			System.out.println(
				"----------------------------------------------------------------------------------------------------------------------------------------------");
			boxes.forEach((Box box) -> {
				System.out.printf("%-40s %-20s %-20s %-20s %-20s%n",
					box.getId(), box.getName(), fromDoubleToFormattedCurrency(box.getTotalAmount()), box.getCreatedAt(),
					box.getUpdatedAt() == null ? "-" : box.getUpdatedAt());
			});
		}

		if (type == Transaction.class) {
			if (entities.isEmpty()) {
				System.out.println("No transactions created yet. Start by adding one !");
				return;
			}
			List<Transaction> transactions = (List<Transaction>) entities;
			System.out.printf("%-40s %-20s %-20s %-40s %-20s %-20s %-20s%n", "ID", "Type", "Impacted Box", "Reason",
				"Amount ($)", "Creation Date", "Updated Date");
			System.out.println(
				"------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			transactions.forEach((Transaction transaction) -> {
				System.out.printf("%-40s %-20s %-20s %-40s %-20s %-20s %-20s%n",
					transaction.getId(), transaction.getType(), transaction.getBoxName(), transaction.getReason(),
					UiHelpers.fromDoubleToFormattedCurrency(transaction.getAmount()), transaction.getCreatedAt(),
					transaction.getUpdatedAt() == null ? "-" : transaction.getUpdatedAt());
			});
		}

		if (type == Transaction.class) {
			if (entities.isEmpty()) {
				System.out.println("No transactions created yet. Start by adding one !");
			}
		}
	}
}
