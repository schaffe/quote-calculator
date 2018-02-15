package com.dzidzoiev;

import com.dzidzoiev.exception.InsufficientAmountOnMarketException;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class LoanCalculator {
    private static final int DEFAULT_LOAN_TIME_MONTH = 36;
    public static final int PAYMENTS_PER_YEAR = 12;

    public LoanOffer calculate(List<MarketOffer> dataItems, long requested) {
        List<Subloan> subloans = composeSubloans(requested, dataItems);

        double montlyRepayment = subloans.stream()
                .mapToDouble(Subloan::getMonthlyRepayment)
                .sum();

        double finalRate = montlyRepayment / requested;

        return new LoanOffer(1000, finalRate, montlyRepayment, montlyRepayment * DEFAULT_LOAN_TIME_MONTH);
    }

    List<Subloan> composeSubloans(long requested, List<MarketOffer> dataItems) {
        Queue<MarketOffer> priorityQueue = new PriorityQueue<>(dataItems);
        List<Subloan> subloans = new ArrayList<>();
        long toFind = requested;
        while (toFind > 0) {
            MarketOffer lender = priorityQueue.poll();
            if (lender == null)
                throw new InsufficientAmountOnMarketException();
            long canOffer = lender.getAvailable();
            if(toFind - canOffer < 0) {
                //we take only amount which is needed to get requested amount (toFind = 0)
                subloans.add(new Subloan(toFind, lender));
            } else {
                //we take all amount from lender
                subloans.add(new Subloan(canOffer, lender));
            }
            toFind -= canOffer;
        }
        return subloans;
    }

    static double compoundInterest(long principal, double rate) {
        return principal * Math.pow((1 + rate / PAYMENTS_PER_YEAR), DEFAULT_LOAN_TIME_MONTH);
    }

    static double calculateMonthlyPayment(double amount, double rate) {
        double monthlyRate = rate / PAYMENTS_PER_YEAR;
        return monthlyRate * amount / (1 - Math.pow(1 + monthlyRate, -DEFAULT_LOAN_TIME_MONTH));
    }

    class Subloan {
        long amount;
        MarketOffer lender;
        double totalCompoundInterest;
        double monthlyRepayment;

        public Subloan(long amount, MarketOffer lender) {
            this.amount = amount;
            this.lender = lender;
            this.monthlyRepayment = calculateMonthlyPayment(amount, lender.getRate());
            this.totalCompoundInterest = monthlyRepayment * PAYMENTS_PER_YEAR;
        }

        public double getTotalCompoundInterest() {
            return totalCompoundInterest;
        }

        public double getMonthlyRepayment() {
            return monthlyRepayment;
        }
    }
}
