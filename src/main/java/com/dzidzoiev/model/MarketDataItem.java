package com.dzidzoiev.model;

import java.util.Objects;

public final class MarketDataItem implements Comparable<MarketDataItem> {
    private final String lender;
    private final double rate;
    private final long available;

    public MarketDataItem(String lender, double rate, int available) {
        Objects.requireNonNull(lender);
        if(rate >= 1 || rate <= 0)
            throw new IllegalStateException("Rate should be in range of 0 .. 1 exclusive instead of " + rate);
        if (available <= 0)
            throw new IllegalStateException("Available value should be higher than 0 instead of " + available);

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

        MarketDataItem item = (MarketDataItem) o;

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
        return "MarketDataItem{" +
                "lender='" + lender + '\'' +
                ", rate=" + rate +
                ", available=" + available +
                '}';
    }

    @Override
    public int compareTo(MarketDataItem o) {
        return Double.compare(rate, o.rate);
    }
}
