package com.dzidzoiev;

import com.dzidzoiev.exception.ZopaException;

import java.util.List;

public class Launcher {
    public static void main(String[] args) {
        try {
            List<MarketOffer> marketOffers = new MarketOfferCsvReader().readFile("src/test/resources/market.csv");
            LoanOffer totalOffer = new LoanCalculator().calculate(marketOffers, 1000);
            System.out.println(totalOffer);
        } catch (ZopaException e) {
            System.out.println(e.getMessage());
        }

    }
}
