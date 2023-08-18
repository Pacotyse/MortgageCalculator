package com.MyApp;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    // Constants
    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    /**
     * Main method that calculates mortgage and payments.
     *
     * @param args - Command-line arguments (not used).
     */
    public static void main(String[] args) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        // Read user inputs
        int principal = (int) readNumber("Principal: ", 1000, 1000000);
        float annualInterest = (float) readNumber("Annual Interest Rate: ", 1, 30);
        float monthlyInterest = annualInterest / MONTHS_IN_YEAR / PERCENT;
        byte years = (byte) readNumber("Period (years): ", 1, 30);
        short numberOfPayment = (short) (years * MONTHS_IN_YEAR);

        // Calculate and print mortgage
        printMortgage(currency, principal, monthlyInterest, numberOfPayment);

        // Calculate and print payment schedule
        printPaymentSchedule(currency, principal, monthlyInterest, years, numberOfPayment);
    }

    /**
     * Print mortgage details.
     *
     * @param currency - NumberFormat instance for currency formatting.
     * @param principal - Loan principal amount.
     * @param monthlyInterest - Monthly interest rate.
     * @param numberOfPayment - Total number of payments.
     */
    private static void printMortgage(NumberFormat currency, int principal, float monthlyInterest,
            short numberOfPayment) {
        double mortgage = calculateMortgage(principal, monthlyInterest, numberOfPayment);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + currency.format(mortgage));
    }

    /**
     * Print payment schedule details.
     *
     * @param currency - NumberFormat instance for currency formatting.
     * @param principal - Loan principal amount.
     * @param monthlyInterest - Monthly interest rate.
     * @param years - Loan period in years.
     * @param numberOfPayment - Total number of payments.
     */
    private static void printPaymentSchedule(NumberFormat currency, int principal, float monthlyInterest, byte years,
            short numberOfPayment) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (short month = 1 ; month <= (years * MONTHS_IN_YEAR); month++ ) {
            double calculatedPayment = calculateBalance(principal, monthlyInterest, numberOfPayment, month);
            System.out.println(currency.format(calculatedPayment));
        };
    }

    /**
     * Read a number within a specified range.
     *
     * @param prompt - Prompt message for user input.
     * @param min - Minimum allowed value.
     * @param max - Maximum allowed value.
     * @return - The user input number within the specified range.
     */
    public static double readNumber(
            String prompt,
            double min,
            double max
    ) {
        Scanner scanner = new Scanner(System.in);
        double value;
        while(true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if (value >= min && value <= max)
                break;
            System.out.println("Enter a number between " + min + " and " + max);
        }
        return value;
    }

    /**
     * Calculate the mortgage payment.
     *
     * @param principal - Loan principal amount.
     * @param monthlyInterest - Monthly interest rate.
     * @param numberOfPayment - Total number of payments.
     * @return - The calculated mortgage payment.
     */
    public static double calculateMortgage(
            int principal,
            float monthlyInterest,
            short numberOfPayment) {

        return principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayment))
                / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1);

    }

    /**
     * Calculate the remaining balance.
     *
     * @param principal - Loan principal amount.
     * @param monthlyInterest - Monthly interest rate.
     * @param numberOfPayment - Total number of payments.
     * @param numberOfPaymentsMade - Number of payments made.
     * @return - The remaining balance.
     */
    public static double calculateBalance(
            int principal,
            float monthlyInterest,
            short numberOfPayment,
            short numberOfPaymentsMade
    ) {
        return principal
                * ((Math.pow(1 +  monthlyInterest, numberOfPayment) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 +  monthlyInterest, numberOfPayment) - 1));
    }
}
