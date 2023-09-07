package com.MortgageCalculator;

import java.text.NumberFormat;

/**
 * The Main class is the entry point for the Mortgage Calculator application.
 */
public class Main {

    /**
     * The main method of the application.
     *
     * @param args - Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        // Read user inputs
        int principal = (int) Console.readNumber("Principal : ", 1000, 1000000);
        float annualInterest = (float) Console.readNumber("Annual Interest Rate : ", 1, 30);
        byte years = (byte) Console.readNumber("Period (years) : ", 1, 30);

        var calculator = new MortgageCalculator(principal, annualInterest, years);
        var report = new MortgageReport(calculator);

        // Calculate and print mortgage
        report.printMortgage();

        // Calculate and print payment schedule
        report.printPaymentSchedule();
    }
}
