package com.dzidzoiev.quote;

import com.dzidzoiev.quote.model.MarketOffer;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MarketOfferTest {

    @Test
    public void testModelPositive() {
        MarketOffer item = new MarketOffer("Lucy", new BigDecimal("0.01"), new BigDecimal("100"));
        assertEquals("Lucy", item.getLender());
        assertEquals(new BigDecimal("0.01"), item.getRate());
        assertEquals(new BigDecimal("100"), item.getAvailable());
    }

    @Test
    public void testComparable() throws Exception {
        MarketOffer bigger = new MarketOffer("Lucy", new BigDecimal("0.1"), new BigDecimal("100"));
        MarketOffer smaller = new MarketOffer("Mark", new BigDecimal("0.01"), new BigDecimal("100"));
        MarketOffer smaller1 = new MarketOffer("Mark1", new BigDecimal("0.01"), new BigDecimal("100"));

        assertTrue(bigger.compareTo(smaller) > 0);
        assertTrue(smaller.compareTo(bigger) < 0);
        assertEquals(0,smaller.compareTo(smaller1));
    }

    @Test
    public void testConstructorPositive() {
        try {
            new MarketOffer(null, new BigDecimal("0.01"), new BigDecimal("100"));
            fail("Lender is null, NPE not thrown");
        } catch (NullPointerException e) {
        }

        try {
            new MarketOffer("Lucy", BigDecimal.ZERO, new BigDecimal("100"));
            fail("'Rate' Exception not thrown");
        } catch (Validator.ValidationException e) {
            assertTrue(e.getMessage().contains("Rate should be in range of 0 .. 1"));
        }

        try {
            new MarketOffer("Lucy", BigDecimal.ONE, new BigDecimal("100"));
            fail("'Rate' Exception not thrown");
        } catch (Validator.ValidationException e) {
            assertTrue(e.getMessage().contains("Rate should be in range of 0 .. 1"));
        }

        try {
            new MarketOffer("Lucy", new BigDecimal("0.5"), BigDecimal.ZERO);
            fail("'Available' Exception not thrown");
        } catch (Validator.ValidationException e) {
            assertTrue(e.getMessage().contains("Available value should be higher than 0"));
        }

    }
}