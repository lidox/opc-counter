package com.futuzon.opccounter.controller.opc;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.futuzon.opccounter.controller.utils.Global;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class GlobalOpcTest {

    @Test
    public void useOpcShare() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        double share = Math.random() * 10;
        boolean isSuccessful = GlobalOpc.setOpcShareWithinGrapeSeedExtract(share, appContext);
        assertTrue(isSuccessful);

        double resultShare = GlobalOpc.getOpcShareWithinGrapeSeedExtract(appContext);

        assertEquals(share, resultShare, 0.001);
    }

    @Test
    public void useBodyWeight() {
        Context appContext = InstrumentationRegistry.getTargetContext();

        int bodyWeight = 100;
        boolean isSuccessful = GlobalOpc.setOpcBodyWeight(bodyWeight, appContext);
        assertTrue(isSuccessful);

        double bodyWeightRead = GlobalOpc.getOpcBodyWeight(appContext);

        assertEquals(bodyWeight, bodyWeightRead, 0);
    }

    @Test
    public void useAmountPerBodyWeight() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        int newAmountPerBodyWeight = 88;
        boolean isSuccessful = GlobalOpc.setOpcAmountPerBodyWeightInKg(newAmountPerBodyWeight, appContext);
        assertTrue(isSuccessful);

        double readAmount = GlobalOpc.getOpcAmountPerBodyWeight(appContext);

        assertEquals(newAmountPerBodyWeight, readAmount, 0);
    }

    @Test
    public void useAppContext() throws Exception {

        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        // get global instance
        Global g = new Global(appContext);
        assertEquals(g.getAppContext(), appContext);
    }

}
