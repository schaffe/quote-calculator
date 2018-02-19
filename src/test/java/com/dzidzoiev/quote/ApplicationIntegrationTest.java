package com.dzidzoiev.quote;

import com.dzidzoiev.quote.model.QuoteOffer;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ApplicationIntegrationTest {

    private static final double DELTA = 0.01;
    private Application application;

    @Before
    public void setUp() throws Exception {
        application = new Application(new MarketOfferCsvReader(), new LoanCalculator(), new Validator());
    }

    @Test
    public void applicationShouldReturnValidQuoteOnExample() throws Exception {
        String path = ClassLoader.getSystemResource("market.csv").getPath();

        Optional<QuoteOffer> calculate = application.calculate(1000, path);
        assertTrue(calculate.isPresent());
        assertEquals(1000, calculate.get().getRequestedAmount());
        assertEquals(0.07, calculate.get().getRate(), DELTA);
        assertEquals(30.87, calculate.get().getMonthlyRepayment(), DELTA);
        assertEquals(1111.64, calculate.get().getTotalRepayment(), DELTA);
    }

    @Test
    public void applicationShouldReturnValidQuotePositive() throws Exception {
        String path = ClassLoader.getSystemResource("market.csv").getPath();

        Optional<QuoteOffer> calculate = application.calculate(2000, path);
        assertTrue(calculate.isPresent());
        assertEquals(2000, calculate.get().getRequestedAmount());
        assertEquals(0.07, calculate.get().getRate(), DELTA);
        assertEquals(62.01, calculate.get().getMonthlyRepayment(), DELTA);
        assertEquals(2232.42, calculate.get().getTotalRepayment(), DELTA);
    }

    @Test
    public void applicationShouldReturnEmptyOnEmptyCsv() throws Exception {
        String path = ClassLoader.getSystemResource("empty.csv").getPath();

        Optional<QuoteOffer> calculate = application.calculate(1000, path);
        assertFalse(calculate.isPresent());
    }

    @Test
    public void applicationShouldReturnEmptyOnUnsutisfied() throws Exception {
        String path = ClassLoader.getSystemResource("market.csv").getPath();

        Optional<QuoteOffer> calculate = application.calculate(10000, path);
        assertFalse(calculate.isPresent());
    }

    @Test
    public void applicationShouldFailOnInvalidAmount() throws Exception {
        try {
            application.run(application, "test", "invalid");
            fail("Exception not thrown");
        } catch (QuoteException e) {
            assertEquals("Requested amount is not a valid integer.", e.getMessage());
        }
    }

    @Test
    public void applicationShouldFailOnIncorrectAmountOfArgs() throws Exception {
        try {
            application.run(application, "test");
            fail("Exception not thrown");
        } catch (QuoteException e) {
            assertTrue(e.getMessage().startsWith("The application should take arguments in the form"));
        }
    }

    @Test
    public void applicationShouldFailOnIncorrectAmountOfArgs1() throws Exception {
        try {
            application.run(application);
            fail("Exception not thrown");
        } catch (QuoteException e) {
            assertTrue(e.getMessage().startsWith("The application should take arguments in the form"));
        }
    }
}