package com.MortgageCalculator;

/**
 * The MortgageCalculator class calculates mortgage-related values.
 */
public class MortgageCalculator {

    // Constants
    private final static byte MONTHS_IN_YEAR = 12;
    private final static byte PERCENT = 100;
    private int principal;
    private float annualInterest;
    private byte years;

    /**
     * Creates a new MortgageCalculator instance with the provided parameters.
     *
     * @param principal - The principal amount of the loan.
     * @param annualInterest - The annual interest rate.
     * @param years - The number of years for the loan term.
     */
    public MortgageCalculator(int principal, float annualInterest, byte years) {
        this.principal = principal;
        this.annualInterest = annualInterest;
        this.years = years;
    }

    /**
     * Calculate the monthly mortgage payment.
     *
     * @return - The monthly mortgage payment.
     */
    public double calculateMortgage() {

        float monthlyInterest = getMonthlyInterest();
        float numberOfPayment = getNumberOfPayment();

        return principal
                * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayment))
                / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1);

    }

    /**
     * Calculate the remaining balance of the loan after a specified number of payments.
     *
     * @param numberOfPaymentsMade - The number of payments made.
     * @return - The remaining balance of the loan.
     */
    public double calculateBalance(
            short numberOfPaymentsMade
    ) {
        float monthlyInterest = getMonthlyInterest();
        float numberOfPayment = getNumberOfPayment();

        return principal
                * ((Math.pow(1 + monthlyInterest, numberOfPayment) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterest, numberOfPayment) - 1));
    }

    /**
     * Get the monthly interest rate.
     *
     * @return - The monthly interest rate.
     */
    private float getMonthlyInterest() {
        return annualInterest / MONTHS_IN_YEAR / PERCENT;
    }

    /**
     * Get the total number of payments over the loan term.
     *
     * @return - The total number of payments.
     */
    private int getNumberOfPayment() {
        return years * MONTHS_IN_YEAR;
    }

    /**
     * Get an array of remaining balances for each month of the loan term.
     *
     * @return - An array of remaining balances.
     */
    public double[] getRemainingBalances() {
        var balances =  new double[getNumberOfPayment()];
        for (short month = 1; month <= balances.length ; month++)
            balances[month - 1] = calculateBalance(month);

        return balances;
    }
}
