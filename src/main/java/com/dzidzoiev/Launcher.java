package com.dzidzoiev;

import com.dzidzoiev.model.MarketOffer;
import com.dzidzoiev.model.QuoteOffer;

import java.util.List;
import java.util.Optional;

public class Launcher {
    public static void main(String[] args) {
        List<MarketOffer> marketOffers = new MarketOfferCsvReader().readFile("src/test/resources/market.csv");
        Optional<QuoteOffer> totalOffer = new LoanCalculator().calculate(marketOffers, 10000);
        if (totalOffer.isPresent()) {
            System.out.println(totalOffer.get());
            System.exit(0);
        } else {
            System.err.println("We cannot offer the loan at the moment. Please decrease the requested amount.");
            System.exit(1);
        }

    }
}
