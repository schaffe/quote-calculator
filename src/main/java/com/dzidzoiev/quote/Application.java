package com.dzidzoiev.quote;

import com.dzidzoiev.quote.model.MarketOffer;
import com.dzidzoiev.quote.model.QuoteOffer;

import java.util.List;
import java.util.Optional;

class Application {
    private final MarketOfferCsvReader marketOfferCsvReader;
    private final LoanCalculator loanCalculator;
    private final Validator validator;

    Application(MarketOfferCsvReader marketOfferCsvReader, LoanCalculator loanCalculator, Validator validator) {
        this.marketOfferCsvReader = marketOfferCsvReader;
        this.loanCalculator = loanCalculator;
        this.validator = validator;
    }

    void run(Application application, String... args) {
        validator.validateArguments(args);
        long amount = validator.parseAndValidateAmount(args[1]);
        String filePath = args[0];

        QuoteOffer totalOffer = application.calculate(amount, filePath)
                .orElseThrow(() -> new QuoteException("It is not possible to provide a quote at that time. " +
                        "The market does not have sufficient offers from lenders to satisfy the loan."));

        System.out.println(totalOffer);
    }

    Optional<QuoteOffer> calculate(long originalAmount, String filePath) {
        long amount = adjustAmount(originalAmount);
        List<MarketOffer> marketOffers = marketOfferCsvReader.readFile(filePath);
        return loanCalculator.calculate(marketOffers, amount);
    }

    static long adjustAmount(long amount) {
        if (amount % Constants.INCREMENT != 0) {
            System.out.println("You requested amount that is not an increment of 100. System will adjust your amount to correct value.");
            return amount - amount % Constants.INCREMENT + Constants.INCREMENT;
        }
        return amount;
    }
}
