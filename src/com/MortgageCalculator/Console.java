package com.MortgageCalculator;

import java.util.Scanner;

/**
 * The Console class provides methods for reading user input from the console.
 */
public class Console {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Read a double value from the console.
     *
     * @param prompt - Prompt message for user input.
     * @return - The user input as a double.
     */
    public static double readNumber(String prompt) {
        return scanner.nextDouble();
    }

    /**
     * Read a double value from the console within a specified range.
     *
     * @param prompt - Prompt message for user input.
     * @param min    - Minimum allowed value.
     * @param max    - Maximum allowed value.
     * @return - The user input number within the specified range.
     */
    public static double readNumber(
            String prompt,
            double min,
            double max
    ) {
        double value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return value;
    }
}
