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

            if(!isRequired) {
                inputValue = inputValue.isEmpty() ? "0" : inputValue;
            }

            if(isInvalidDoubleInputValue(inputValue) && isRequired) {
                System.out.println(UiConstants.RED_COLOR + "Input " + fieldName + " is not a valid input." + UiConstants.RESET_COLOR);
            }
        } while(isInvalidDoubleInputValue(inputValue) && isRequired);

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
            if(isInvalidIntegerInputValue(inputValue)) {
                System.out.println(UiConstants.RED_COLOR + "Input " + fieldName + " is not a valid input." + UiConstants.RESET_COLOR);
            }
        } while(isInvalidIntegerInputValue(inputValue));

        return Integer.parseInt(inputValue);
    }

    /**
     * Verifies if a provided value can be converted to a double type.
     * @param input represents the inserted value by the user.
     * @return retruns TRUE if input is invalid or FALSE if input is valid.
     */
    private static boolean isInvalidDoubleInputValue(String input) {
        if (input.isEmpty()) {
            return true;
        }
        try {
            Double.parseDouble(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Verifies if a provided value can be converted to an integer type.
     * @param input represents the inserted value by the user
     * @return retruns TRUE if input is invalid or FALSE if input is valid.
     */
    private static boolean isInvalidIntegerInputValue(String input) {
        if (input.isEmpty()) {
            return true;
        }
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}
