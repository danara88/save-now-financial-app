package com.savenow.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.savenow.model.Transaction;
import com.savenow.view.uiUtils.UiConstants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the persistence approach for transaction feature.
 */
public class TransactionPersistence {
	private static final String FILE_PATH_DB = "TransactionsDB.json";
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final Type transactionListType = new TypeToken<List<Transaction>>(){}.getType();

	/**
	 * Method in charge of serializing an array and save it in the database.
	 * @param transactions represents a list of transactions to save.
	 */
	public static void saveTransactions(List<Transaction> transactions) {
		try(Writer writer = new FileWriter(FILE_PATH_DB)) {
			gson.toJson(transactions, writer);
		} catch (IOException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
		}
	}

	/**
	 * Method in charge of loading transactions from database.
	 * @return a list of transactions from database.
	 */
	public static List<Transaction> loadTransactions() {
		try(Reader reader = new FileReader(FILE_PATH_DB)) {
			return gson.fromJson(reader, transactionListType);
		} catch (IOException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
			return new ArrayList<>();
		}
	}
}
