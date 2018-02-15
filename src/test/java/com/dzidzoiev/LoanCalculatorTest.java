package com.dzidzoiev;

import com.dzidzoiev.exception.InsufficientAmountOnMarketException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoanCalculatorTest {


    @Test
    public void sumOfSubloansShouldBeEqualRequestedAmount() throws Exception {
        List<MarketOffer> testSet = new ArrayList<>();
        testSet.add(new MarketOffer("Lucy", 0.1, 1000));
        testSet.add(new MarketOffer("Mark", 0.01, 1000));
        testSet.add(new MarketOffer("Mark1", 0.01, 1000));

        LoanCalculator calculator = new LoanCalculator();
        List<LoanCalculator.Subloan> calculate = calculator.composeSubloans(1500, testSet);

        long sum = 0;
        for (LoanCalculator.Subloan subloan : calculate) {
            sum += subloan.amount;
        }

        assertEquals(1500, sum);
    }

    @Test(expected = InsufficientAmountOnMarketException.class)
    public void sumOfSubloansShouldThrowExceptionOnInsufficientMarket() throws Exception {
        LoanCalculator calculator = new LoanCalculator();
        calculator.composeSubloans(1500, Collections.emptyList());
    }

    @Test
    public void testCompoundInterestePositive() throws Exception {
        assertEquals(1232.93d, LoanCalculator.compoundInterest(1000, 0.07), 0.01);
    }
}