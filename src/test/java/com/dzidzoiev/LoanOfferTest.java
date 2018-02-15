package com.dzidzoiev;

import org.junit.Assert;
import org.junit.Test;

public class LoanOfferTest {
    @Test
    public void testToString() throws Exception {
        String expected =
                "Requested amount: £1000\n" +
                "Rate: 0.1%\n" +
                "Monthly repayment: £100.00\n" +
                "Total repayment: £1000.01";
        LoanOffer actual = new LoanOffer(1000, 0.001, 100, 1000.01);
        Assert.assertEquals(expected, actual.toString());
    }

}