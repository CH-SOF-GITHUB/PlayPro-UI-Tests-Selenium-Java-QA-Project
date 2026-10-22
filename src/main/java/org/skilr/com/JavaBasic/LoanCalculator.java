package org.skilr.com.JavaBasic;

public class LoanCalculator {

    private int amount;
    private int year;
    private double interestRate;

    public LoanCalculator(int amount, int year, double interestRate) throws LoanCalculatorException {
        this.amount = amount;
        this.year = year;
        this.interestRate = interestRate;
        if (amount <= 0 && year <= 0 && interestRate <= 0) {
            throw new LoanCalculatorException("Invalid Values - cannot calculate repayment amount");
        }
    }

    public double calculateRepaymentAmount() {
        double currentAmountPayable = amount;
        int currentYear = 1;
        while (currentYear <= year + 1) {
            currentAmountPayable = currentAmountPayable + interestRate;
            System.out.println("year" + currentYear + "AmountDue = " + currentAmountPayable);
            currentYear++;
        }
        return currentAmountPayable;
    }

    public int getAmount() {
        return amount;
    }

    public int getYear() {
        return year;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
