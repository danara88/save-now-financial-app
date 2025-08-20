package com.savenow.view.uiUtils;

import java.util.Scanner;

/**
 * In charge of handling prompt system interactions
 */
public class PromptUtils {
    /**
     * Captures user prompt (string).
     * @param label represents the input label.
     * @param fieldName represents the name assigned to the input.
     * @param isRequired represents if the input is required.
     * @return returns the string setted value from the user.
     */
    public static String inputString(String label, String fieldName, boolean isRequired) {
        String inputValue;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(label + UiConstants.defaultPromptLabel);
            inputValue = scanner.nextLine();
            if (inputValue.isEmpty() && isRequired) {
                System.out.println(UiConstants.RED_COLOR + "Input " + fieldName + " must not be empty." + UiConstants.RESET_COLOR);
            }
        } while (inputValue.isEmpty() && isRequired);

        return inputValue;
    }

    /**
     * Captures user prompt (double)
     * @param label represents the input label.
     * @param fieldName represents the name assigned to the input.
     * @param isRequired represents if the input is required.
     * @return returns the double setted value from the user.
     */
    public static Double inputDouble(String label, String fieldName, boolean isRequired) {
        String inputValue;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(label + UiConstants.defaultPromptLabel);
            inputValue = scanner.nextLine();
            if(!isDouble(inputValue) && isRequired) {
                System.out.println(UiConstants.RED_COLOR + "Input " + fieldName + " is not a valid input." + UiConstants.RESET_COLOR);
            }
        } while(!isDouble(inputValue) && isRequired);

        return Double.parseDouble(inputValue);
    }

    /**
     * Captures user prompt (int)
     * @return Integer
     */
    public static Integer inputInteger(String label, String fieldName) {
        String inputValue;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println(label + UiConstants.defaultPromptLabel);
            inputValue = scanner.nextLine();
            if(!isInteger(inputValue)) {
                System.out.println(UiConstants.RED_COLOR + "Input " + fieldName + " is not a valid input." + UiConstants.RESET_COLOR);
            }
        } while(!isInteger(inputValue));

        return Integer.parseInt(inputValue);
    }

    /**
     * Verifies if a provided value can be converted to a double type.
     * @param input represents the inserted value by the user
     * @return true if it can be converted to a double type
     */
    private static boolean isDouble(String input) {
        if (input.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Verifies if a provided value can be converted to an integer type.
     * @param input represents the inserted value by the user
     * @return true if it can be converted to an integer type
     */
    private static boolean isInteger(String input) {
        if (input.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
