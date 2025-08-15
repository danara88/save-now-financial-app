package com.savenow.persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.savenow.model.Box;
import com.savenow.view.uiUtils.UiConstants;

/**
 * Represents the persistence approach for box feature
 */
public class BoxPersistence {
	private static final String FILE_PATH_DB = "BoxesDB.json";
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private static final Type boxListType = new TypeToken<List<Box>>(){}.getType();

	/**
	 * Method in charge of serializing an array and save it in the database.
	 * @param boxes represents a list of boxes to save.
	 */
	public static void saveBoxes(List<Box> boxes) {
		try(Writer writer = new FileWriter(FILE_PATH_DB)) {
			gson.toJson(boxes, writer);
		} catch (IOException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
		}
	}

	/**
	 * Method in charge of loading boxes from database.
	 * @return a list of boxes from database.
	 */
	public static List<Box> loadBoxes() {
		try(Reader reader = new FileReader(FILE_PATH_DB)) {
			return gson.fromJson(reader, boxListType);
		} catch (IOException e) {
			System.out.println(UiConstants.RED_COLOR + e.getMessage() + UiConstants.RESET_COLOR);
			return new ArrayList<>();
		}
	}
}
