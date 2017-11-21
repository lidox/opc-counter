package com.futuzon.opccounter.controller.calculation;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.futuzon.opccounter.controller.opc.GlobalOpc;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class OpcIntakeCalculatorTest {

    @Test
    public void getRecommendedOpcDailyRation1() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        OpcIntakeCalculator calculator = new OpcIntakeCalculator();

        GlobalOpc.setOpcAmountPerBodyWeightInKg(4, appContext);
        GlobalOpc.setOpcBodyWeight(63, appContext);
        GlobalOpc.setOpcShareWithinGrapeSeedExtract(40, appContext);

        assertEquals(630, calculator.getRecommendedGrapeSeedExtractDailyRation(appContext));
    }

}
