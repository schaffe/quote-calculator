package com.dzidzoiev.quote;

import com.dzidzoiev.quote.model.Loan;
import com.dzidzoiev.quote.model.MarketOffer;
import org.junit.Before;
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
    private static final BigDecimal hundred = new BigDecimal(100);

    private LoanCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new LoanCalculator();
    }

    @Test
    public void sumOfSubloansShouldBeEqualRequestedAmount() throws Exception {
        List<MarketOffer> testSet = new ArrayList<>();
        testSet.add(new MarketOffer("Lucy", tenPercent, thousand));
        testSet.add(new MarketOffer("Mark", tenPercent, thousand));
        testSet.add(new MarketOffer("Mark1", tenPercent, thousand));

        List<Loan> calculate = calculator.composeLoans(1500, testSet);

        long sum = 0;
        for (Loan loan : calculate) {
            sum += loan.getAmount().longValue();
        }

        assertEquals(1500, sum);
    }

    @Test
    public void sumOfSubloansShouldReturnEmptyOnInsufficientMarket() throws Exception {
        List<Loan> loans = calculator.composeLoans(1500, Collections.emptyList());
        assertTrue(loans.isEmpty());
    }

    @Test
    public void calculateWeightedRateShouldReturnTheSameForEqualRates() throws Exception {
        MarketOffer lucy = new MarketOffer("Lucy", tenPercent, thousand);

        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(thousand, lucy, hundred));
        loans.add(new Loan(thousand.multiply(BigDecimal.TEN), lucy, hundred));
        assertEquals(tenPercent, calculator.calculateWeightedRate(loans));
    }

    @Test
    public void calculateWeightedRateShouldReturnScaledResult() throws Exception {
        MarketOffer lucy = new MarketOffer("Lucy", tenPercent, thousand);
        MarketOffer mark = new MarketOffer("Mark", new BigDecimal("0.05"), thousand);

        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(thousand, lucy, hundred));
        loans.add(new Loan(thousand.multiply(BigDecimal.TEN), mark, hundred));
        assertEquals(new BigDecimal("0.05454545"), calculator.calculateWeightedRate(loans));
    }

    @Test
    public void calculateWeightedRateShouldReturnArithmeticMeanForEqualAmounts() throws Exception {
        MarketOffer lucy = new MarketOffer("Lucy", new BigDecimal("0.06"), thousand);
        MarketOffer mark = new MarketOffer("Mark", new BigDecimal("0.05"), thousand);
        MarketOffer alex = new MarketOffer("Alex", new BigDecimal("0.04"), thousand);

        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(thousand, lucy, hundred));
        loans.add(new Loan(thousand, mark, hundred));
        loans.add(new Loan(thousand, alex, hundred));
        assertEquals(new BigDecimal("0.05"), calculator.calculateWeightedRate(loans));
    }

    @Test
    public void testCalculateMonthlyRepaymentPositive1() throws Exception {
        BigDecimal bigDecimal = calculator.calculateMonthlyRepayment(thousand, tenPercent);
        assertEquals(new BigDecimal("32.267182042646"), bigDecimal);
    }

    @Test
    public void testCalculateMonthlyRepaymentPositive2() throws Exception {
        BigDecimal bigDecimal = calculator.calculateMonthlyRepayment(thousand, new BigDecimal(0.07));
        assertEquals(new BigDecimal("30.877103235594"), bigDecimal);
    }
}