package com.dzidzoiev;

import java.util.Objects;

public final class MarketOffer implements Comparable<MarketOffer> {
    private final String lender;
    private final double rate;
    private final long available;

    public MarketOffer(String lender, double rate, int available) {
        Objects.requireNonNull(lender);
        Validator.validateRate(rate);
        Validator.validateAvailable(available);

        this.lender = lender;
        this.rate = rate;
        this.available = available;
    }

    public String getLender() {
        return lender;
    }

    public double getRate() {
        return rate;
    }

    public long getAvailable() {
        return available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MarketOffer item = (MarketOffer) o;

        if (Double.compare(item.rate, rate) != 0) return false;
        if (available != item.available) return false;
        return lender != null ? lender.equals(item.lender) : item.lender == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = lender != null ? lender.hashCode() : 0;
        temp = Double.doubleToLongBits(rate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (available ^ (available >>> 32));
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
        return Double.compare(rate, o.rate);
    }
}
