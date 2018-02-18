package com.dzidzoiev;

public class Validator {
    public static void validateRate(double rate) {
        if(rate >= 1 || rate <= 0)
            throw new ValidationException("Rate should be in range of 0 .. 1 exclusive instead of " + rate);
    }

    public static void validateAvailable(long available) {
        if (available <= 0)
            throw new ValidationException("Available value should be higher than 0 instead of " + available);
    }

    public static class ValidationException extends RuntimeException {
        private ValidationException(String message) {
            super(message);
        }
    }
}
