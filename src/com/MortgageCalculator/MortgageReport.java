package com.MortgageCalculator;

import java.text.NumberFormat;

/**
 * The MortgageReport class generates and prints mortgage-related reports.
 */
public class MortgageReport {

    private final NumberFormat currency;
    private MortgageCalculator calculator;

    /**
     * Creates a new MortgageReport instance using the provided MortgageCalculator.
     *
     * @param calculator - The MortgageCalculator to generate reports for.
     */
    public MortgageReport(MortgageCalculator calculator) {
        this.calculator = calculator;
        currency = NumberFormat.getCurrencyInstance();
    }

    /**
     * Print the payment schedule, showing the remaining balances for each month of the loan term.
     */
    public void printPaymentSchedule() {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for (double balance : calculator.getRemainingBalances())
            System.out.println(currency.format(balance));
    }

    /**
     * Print the mortgage details, including the monthly payment amount.
     */
    public void printMortgage() {
        double mortgage = calculator.calculateMortgage();
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments : " + currency.format(mortgage));
    }
}
