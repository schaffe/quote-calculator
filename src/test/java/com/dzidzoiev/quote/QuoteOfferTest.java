package com.dzidzoiev.quote;

import com.dzidzoiev.quote.model.QuoteOffer;
import org.junit.Assert;
import org.junit.Test;

public class QuoteOfferTest {
    @Test
    public void testToString() throws Exception {
        String expected =
                "Requested amount: £1000\n" +
                "Rate: 0.1%\n" +
                "Monthly repayment: £100.00\n" +
                "Total repayment: £1000.01";
        QuoteOffer actual = new QuoteOffer(1000, 0.001, 100, 1000.01);
        Assert.assertEquals(expected, actual.toString());
    }

}