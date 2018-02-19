package com.dzidzoiev.quote.model;

import java.math.BigDecimal;

public class QuoteOffer {
    private final long requestedAmount;
    private final double rate;
    private final double monthlyRepayment;
    private final double totalRepayment;

    public QuoteOffer(long requestedAmount, double rate, double monthlyRepayment, double totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.rate = rate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public static QuoteOffer of(long requestedAmount, BigDecimal rate, BigDecimal montlyRepayment, BigDecimal totalRepayment) {
        return new QuoteOffer(
                requestedAmount,
                rate.doubleValue(),
                montlyRepayment.doubleValue(),
                totalRepayment.doubleValue()
        );
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
