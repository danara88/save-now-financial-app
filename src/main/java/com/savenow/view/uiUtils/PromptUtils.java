package com.savenow.view.uiUtils;

import java.util.Scanner;

/**
 * In charge of handling prompt system interactions
 */
public class PromptUtils {
    /**
     * Captures user prompt (string)
     * @return String
     */
    public static String inputString(String label) {
        System.out.println(label + UiConstants.defaultPromptLabel);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Captures user prompt (double)
     * @return Double
     */
    public static Double inputDouble(String label) {
        System.out.println(label + UiConstants.defaultPromptLabel);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    /**
     * Captures user prompt (int)
     * @return Integer
     */
    public static Integer inputInteger(String label) {
        System.out.println(label + UiConstants.defaultPromptLabel);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
