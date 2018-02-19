package com.dzidzoiev;

import com.dzidzoiev.model.Loan;
import com.dzidzoiev.model.MarketOffer;
import com.dzidzoiev.model.QuoteOffer;

import java.math.BigDecimal;
import java.util.*;

class LoanCalculator {

    /**
     * Calculates quote taking into account market offers and requested amount.
     *
     * If offers list is empty or program was unable to find required amount after reviewing
     * offers it returns Optional.empty() indicating insufficient amount of investors on market.
     */
    @SuppressWarnings("ConstantConditions")
    Optional<QuoteOffer> calculate(List<MarketOffer> dataItems, long requested) {
        List<Loan> loans = composeLoans(requested, dataItems);
        if(loans.isEmpty())
            return Optional.empty();

        BigDecimal finalRate = calculateWeightedRate(loans);
        BigDecimal monthlyRepayment = loans.stream()
                .map(Loan::getMonthlyRepayment)
                .reduce(BigDecimal::add)
                .get();
        QuoteOffer quote = QuoteOffer.of(requested, finalRate, monthlyRepayment, monthlyRepayment.multiply(Constants.DEFAULT_LOAN_TIME_MONTH_BD));
        return Optional.of(quote);
    }

    /**
     * Composes List of loans by picking offers by increasing order of rate so that first offers with the lowest
     * rate will be used.
     *
     * If offers list is empty or program was unable to find required amount after reviewing
     * offers it returns empty list indicating insufficient amount of investors on market.
     *
     * @param requested requested amount to borrow
     * @param offers    offers on market
     * @return  composed loans to offer.
     */
    List<Loan> composeLoans(long requested, List<MarketOffer> offers) {
        Queue<MarketOffer> priorityQueue = new PriorityQueue<>(offers);
        List<Loan> loans = new ArrayList<>();
        long toFind = requested;
        while (toFind > 0) {
            MarketOffer lender = priorityQueue.poll();
            if (lender == null)
                return Collections.emptyList();
            long canOffer = lender.getAvailable().longValue();
            if (toFind - canOffer < 0) {
                //we take only amount which is needed to get requested amount (toFind = 0)
                BigDecimal amount = new BigDecimal(toFind);
                Loan loan = new Loan(amount, lender, calculateMonthlyRepayment(amount, lender.getRate()));
                loans.add(loan);
            } else {
                //we take all amount from lender
                BigDecimal amount = new BigDecimal(canOffer);
                Loan loan = new Loan(amount, lender, calculateMonthlyRepayment(amount, lender.getRate()));
                loans.add(loan);
            }
            toFind -= canOffer;
        }
        return loans;
    }

    /**
     * Calculates monthly repayment by formula
     * http://financeformulas.net/Loan_Payment_Formula.html
     */
    BigDecimal calculateMonthlyRepayment(BigDecimal amount, BigDecimal rate) {
        BigDecimal monthlyRate = rate.divide(Constants.PAYMENTS_PER_YEAR_BD, Constants.divContext);
        BigDecimal pow = BigDecimal.ONE
                .add(monthlyRate)
                .pow(Constants.DEFAULT_LOAN_TIME_MONTH_BD.intValue());
        BigDecimal dividePow = BigDecimal.ONE.divide(pow, Constants.divContext);
        return monthlyRate.multiply(amount.divide(BigDecimal.ONE.subtract(dividePow), Constants.divContext));
    }

    /**
     * Used weighted arithmetic mean formula from
     * https://en.wikipedia.org/wiki/Weighted_arithmetic_mean
     *
     * Calculates mean for collection of loans taking into account
     * contribution to the quote
     */
    @SuppressWarnings("ConstantConditions")
    BigDecimal calculateWeightedRate(Collection<Loan> loans) {
        BigDecimal weightedSum = loans.stream()
                .map(loan -> loan.getRate().multiply(loan.getAmount()))
                .reduce(BigDecimal::add)
                .get();
        BigDecimal weights = loans.stream()
                .map(Loan::getAmount)
                .reduce(BigDecimal::add)
                .get();
        return weightedSum.divide(weights, Constants.divContext);
    }

}
