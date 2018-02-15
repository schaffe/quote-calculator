package com.dzidzoiev;

public class LoanOffer {
    private final long requestedAmount;
    private final double rate;
    private final double monthlyRepayment;
    private final double totalRepayment;

    public LoanOffer(long requestedAmount, double rate, double monthlyRepayment, double totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.rate = rate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public long getRequestedAmount() {
        return requestedAmount;
    }

    public double getRate() {
        return rate;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public double getTotalRepayment() {
        return totalRepayment;
    }

    @Override
    public String toString() {
        return "Requested amount: £" + requestedAmount +
                "\nRate: " + String.format("%.1f", rate * 100) + "%" +
                "\nMonthly repayment: £" + String.format("%.2f", monthlyRepayment) +
                "\nTotal repayment: £" + String.format("%.2f", totalRepayment);
    }
}
