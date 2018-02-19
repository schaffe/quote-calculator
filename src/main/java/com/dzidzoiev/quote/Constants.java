package com.dzidzoiev.quote;

import java.math.BigDecimal;
import java.math.MathContext;

class Constants {

    static final int INCREMENT = 100;
    static final BigDecimal DEFAULT_LOAN_TIME_MONTH_BD = new BigDecimal("36");
    static final BigDecimal PAYMENTS_PER_YEAR_BD = new BigDecimal("12");
    static final MathContext divContext = MathContext.DECIMAL32;
    static final long MIN_AMOUNT = 1000;
    static final long MAX_AMOUNT = 15000;

    private Constants() {
        throw new IllegalStateException();
    }
}
