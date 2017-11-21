package com.futuzon.opccounter.controller.calculation;


import android.content.Context;

import com.futuzon.opccounter.controller.opc.GlobalOpc;

public class OpcIntakeCalculator {

    /**
     * Calculate the recommended daily 'grape seed extract' intake ration
     *
     * @param bodyWeight             the persons body weight
     * @param realOpcSharePercentage the real OPC share of the 'grape seed extract' in percentage
     * @param opcPerKgOfBodyWeight   the OPC amount per kg of the persons body weight
     * @return the recommended daily grape seed extract (Traubenkernextrakt) intake ration in mg
     */
    public int getRecommendedGrapeSeedExtractDailyRation(double bodyWeight, double realOpcSharePercentage, double opcPerKgOfBodyWeight) {
        return (int) Math.round(opcPerKgOfBodyWeight * bodyWeight / (realOpcSharePercentage / 100.));
    }

    /**
     * Calculate the recommended daily 'grape seed extract' intake ration
     *
     * @return the recommended daily grape seed extract (Traubenkernextrakt) intake ration in mg
     */
    public int getRecommendedGrapeSeedExtractDailyRation(Context ctx) {
        double bodyWeight = GlobalOpc.getOpcBodyWeight(ctx);
        double realOpcSharePercentage = GlobalOpc.getOpcShareWithinGrapeSeedExtract(ctx);
        double opcPerKgOfBodyWeight = GlobalOpc.getOpcAmountPerBodyWeight(ctx);
        return getRecommendedGrapeSeedExtractDailyRation(bodyWeight, realOpcSharePercentage, opcPerKgOfBodyWeight);
    }

    /**
     * Calculate the recommended daily 'oligomeric proanthocyanidins' intake ration
     *
     * @return the recommended daily oligomeric proanthocyanidins (OPC) intake ration in mg
     */
    public int getRecommendedOpcDailyRation(Context ctx) {
        double bodyWeight = GlobalOpc.getOpcBodyWeight(ctx);
        double opcPerKgOfBodyWeight = GlobalOpc.getOpcAmountPerBodyWeight(ctx);
        return (int) Math.round(bodyWeight * opcPerKgOfBodyWeight);
    }

}
