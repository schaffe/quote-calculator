package com.dzidzoiev;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ApplicationUtilTest {
    @Test
    public void adjustAmountShouldReturnNextDiscreteNumber() throws Exception {
        assertEquals(1100, Application.adjustAmount(1050));
    }
}