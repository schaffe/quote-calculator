package com.dzidzoiev;

import com.dzidzoiev.model.Loan;
import com.dzidzoiev.model.MarketOffer;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoanCalculatorTest {

    private static final BigDecimal thousand = new BigDecimal("1000");
    private static final BigDecimal tenPercent = new BigDecimal("0.1");

    @Test
    public void sumOfSubloansShouldBeEqualRequestedAmount() throws Exception {
        List<MarketOffer> testSet = new ArrayList<>();
        testSet.add(new MarketOffer("Lucy", tenPercent, thousand));
        testSet.add(new MarketOffer("Mark", tenPercent, thousand));
        testSet.add(new MarketOffer("Mark1", tenPercent, thousand));

        LoanCalculator calculator = new LoanCalculator();
        List<Loan> calculate = calculator.composeLoans(1500, testSet);

        long sum = 0;
        for (Loan loan : calculate) {
            sum += loan.getAmount().longValue();
        }

        assertEquals(1500, sum);
    }

    @Test
    public void sumOfSubloansShouldReturnEmptyOnInsufficientMarket() throws Exception {
        LoanCalculator calculator = new LoanCalculator();
        List<Loan> loans = calculator.composeLoans(1500, Collections.emptyList());
        assertTrue(loans.isEmpty());
    }
}