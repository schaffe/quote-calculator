package com.dzidzoiev.quote;

public class Validator {

    public static void validateRate(double rate) {
        if(rate >= 1 || rate <= 0)
            throw new ValidationException("Rate should be in range of 0 .. 1 exclusive instead of " + rate);
    }

    public static void validateAvailable(long available) {
        if (available <= 0)
            throw new ValidationException("Available value should be higher than 0 instead of " + available);
    }

    void validateArguments(String[] args) {
        if(args.length != 2) {
            throw new ValidationException("The application should take arguments in the form:\n" +
                    "[application] [file_path] [loan amount].");
        }
    }

    long parseAndValidateAmount(String str) {
        try {
            long amount = Long.parseLong(str);
            if (amount < Constants.MIN_AMOUNT || amount > Constants.MAX_AMOUNT)
                throw new ValidationException("Requested amount should be between £1000 and £15000 inclusive.");
            else
                return amount;
        } catch (NumberFormatException e) {
            throw new ValidationException("Requested amount is not a valid integer.");
        }
    }

    static class ValidationException extends QuoteException {
        private ValidationException(String message) {
            super(message);
        }
    }
}
