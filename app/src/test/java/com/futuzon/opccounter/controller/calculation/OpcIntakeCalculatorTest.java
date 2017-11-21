package com.futuzon.opccounter.controller.calculation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OpcIntakeCalculatorTest {

    @Test
    public void getRecommendedOpcDailyRation1() {
        OpcIntakeCalculator calculator = new OpcIntakeCalculator();
        assertEquals(630, calculator.getRecommendedGrapeSeedExtractDailyRation(63,40,4));
    }

    @Test
    public void getRecommendedOpcDailyRation2() {
        OpcIntakeCalculator calculator = new OpcIntakeCalculator();
        assertEquals(615, calculator.getRecommendedGrapeSeedExtractDailyRation(63,41,4));
    }

}
