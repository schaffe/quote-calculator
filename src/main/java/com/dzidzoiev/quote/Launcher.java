package com.dzidzoiev.quote;

public class Launcher {
    public static void main(String[] args) {
        Application application = createApplilcation();

        try {
            application.run(application, args);
        } catch (QuoteException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static Application createApplilcation() {
        Validator validator = new Validator();
        MarketOfferCsvReader marketOfferCsvReader = new MarketOfferCsvReader();
        LoanCalculator loanCalculator = new LoanCalculator();
        return new Application(marketOfferCsvReader, loanCalculator, validator);
    }

}
