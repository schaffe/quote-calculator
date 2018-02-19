package com.dzidzoiev.quote.model;

import java.math.BigDecimal;

public class Loan {
    private final BigDecimal amount;
    private final MarketOffer lender;
    private final BigDecimal monthlyRepayment;

    public Loan(BigDecimal amount, MarketOffer lender, BigDecimal monthlyRepayment) {
        this.amount = amount;
        this.lender = lender;
        this.monthlyRepayment = monthlyRepayment;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public BigDecimal getRate() {
        return lender.getRate();
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
