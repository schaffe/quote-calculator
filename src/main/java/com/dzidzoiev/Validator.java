package com.dzidzoiev;

import com.dzidzoiev.exception.ZopaException;

public class Validator {
    static void validateRate(double rate) {
        if(rate >= 1 || rate <= 0)
            throw new ValidationException("Rate should be in range of 0 .. 1 exclusive instead of " + rate);
    }

    static void validateAvailable(int available) {
        if (available <= 0)
            throw new ValidationException("Available value should be higher than 0 instead of " + available);
    }

    static class ValidationException extends ZopaException {
        private ValidationException(String message) {
            super(message);
        }
    }
}
