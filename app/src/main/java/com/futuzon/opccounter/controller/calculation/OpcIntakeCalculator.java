package com.futuzon.opccounter.controller.calculation;


public class OpcIntakeCalculator {

    /**
     * Calculate the recommended daily 'grape seed extract' intake ration
     *
     * @param bodyWeight             the persons body weight
     * @param realOpcSharePercentage the real OPC share of the 'grape seed extract' in percentage
     * @param opcPerKgOfBodyWeight   the OPC amount per kg of the persons body weight
     * @return the recommended daily grape seed extract (Traubenkernextrakt) intake ration in mg
     */
    public int getRecommendedOpcDailyRation(int bodyWeight, int realOpcSharePercentage, int opcPerKgOfBodyWeight) {
        return (int) Math.round(opcPerKgOfBodyWeight * bodyWeight / (realOpcSharePercentage / 100.));
    }

}
