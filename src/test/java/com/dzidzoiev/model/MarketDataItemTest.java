package com.dzidzoiev.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MarketDataItemTest {

    @Test
    public void testModelPositive() {
        MarketDataItem item = new MarketDataItem("Lucy", 0.01, 100);
        assertEquals("Lucy", item.getLender());
        assertEquals(0.01, item.getRate(), 0.01);
        assertEquals(100, item.getAvailable());
    }

    @Test
    public void testConstructorPositive() {
        try {
            new MarketDataItem(null, 0.01, 100);
            fail("Lender is null, NPE not thrown");
        } catch (NullPointerException e) {
        }

        try {
            new MarketDataItem("Lucy", 0, 100);
            fail("'Rate' Exception not thrown");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Rate should be in range of 0 .. 1"));
        }

        try {
            new MarketDataItem("Lucy", 1, 100);
            fail("'Rate' Exception not thrown");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Rate should be in range of 0 .. 1"));
        }

        try {
            new MarketDataItem("Lucy", 0.5, 0);
            fail("'Available' Exception not thrown");
        } catch (IllegalStateException e) {
            assertTrue(e.getMessage().contains("Available value should be higher than 0"));
        }

    }
}