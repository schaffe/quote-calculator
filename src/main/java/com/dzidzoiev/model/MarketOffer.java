package com.dzidzoiev.model;

import com.dzidzoiev.Validator;

import java.math.BigDecimal;
import java.util.Objects;

public class MarketOffer implements Comparable<MarketOffer> {
    private final String lender;
    private final BigDecimal rate;
    private final BigDecimal available;

    public MarketOffer(String lender, BigDecimal rate, BigDecimal available) {
        Objects.requireNonNull(lender);
        Validator.validateRate(rate.doubleValue());
        Validator.validateAvailable(available.longValue());

        this.lender = lender;
        this.rate = rate;
        this.available = available;
    }

    public String getLender() {
        return lender;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketOffer that = (MarketOffer) o;

        if (lender != null ? !lender.equals(that.lender) : that.lender != null) return false;
        if (rate != null ? !rate.equals(that.rate) : that.rate != null) return false;
        return available != null ? available.equals(that.available) : that.available == null;
    }

    @Override
    public int hashCode() {
        int result = lender != null ? lender.hashCode() : 0;
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MarketOffer{" +
                "lender='" + lender + '\'' +
                ", rate=" + rate +
                ", available=" + available +
                '}';
    }

    @Override
    public int compareTo(MarketOffer o) {
        return rate.compareTo(o.rate);
    }
}
