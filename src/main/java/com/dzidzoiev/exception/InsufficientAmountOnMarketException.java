package com.dzidzoiev.exception;

public class InsufficientAmountOnMarketException extends ZopaException {
    public InsufficientAmountOnMarketException() {
        super("We cannot offer the loan at the moment. There is insufficient amount of lenders at the market.");
    }
}
