package com.dzidzoiev;

import org.junit.Test;

import static org.junit.Assert.*;

public class MarketOfferTest {

    @Test
    public void testModelPositive() {
        MarketOffer item = new MarketOffer("Lucy", 0.01, 100);
        assertEquals("Lucy", item.getLender());
        assertEquals(0.01, item.getRate(), 0.01);
        assertEquals(100, item.getAvailable());
    }

    @Test
    public void testComparable() throws Exception {
        MarketOffer bigger = new MarketOffer("Lucy", 0.1, 100);
        MarketOffer smaller = new MarketOffer("Mark", 0.01, 100);
        MarketOffer smaller1 = new MarketOffer("Mark1", 0.01, 100);

        assertTrue(bigger.compareTo(smaller) > 0);
        assertTrue(smaller.compareTo(bigger) < 0);
        assertEquals(0,smaller.compareTo(smaller1));
    }

    @Test
    public void testConstructorPositive() {
        try {
            new MarketOffer(null, 0.01, 100);
            fail("Lender is null, NPE not thrown");
        } catch (NullPointerException e) {
        }

        try {
            new MarketOffer("Lucy", 0, 100);
            fail("'Rate' Exception not thrown");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Rate should be in range of 0 .. 1"));
        }

        try {
            new MarketOffer("Lucy", 1, 100);
            fail("'Rate' Exception not thrown");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Rate should be in range of 0 .. 1"));
        }

        try {
            new MarketOffer("Lucy", 0.5, 0);
            fail("'Available' Exception not thrown");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Available value should be higher than 0"));
        }

    }
}