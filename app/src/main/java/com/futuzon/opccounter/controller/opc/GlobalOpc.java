package com.futuzon.opccounter.controller.opc;

import android.content.Context;

import com.futuzon.opccounter.R;
import com.futuzon.opccounter.controller.utils.Global;

/**
 * Gives access to values used for the OPC calculations
 */
public class GlobalOpc {

    /**
     * Sets new body weight value
     *
     * @param newBodyWeight the new body weight to set
     * @return True on success setting the new value. Otherwise false.
     */
    public static boolean setOpcBodyWeight(double newBodyWeight, Context context) {
        return new Global(context).putDouble(R.string.c_opc_body_weight, newBodyWeight);
    }

    /**
     * Get the body weight saved in the shared preferences
     *
     * @return the body weight saved in the shared preferences
     */
    public static Double getOpcBodyWeight(Context context) {
        return new Global(context).getDoubleByKey(R.string.c_opc_body_weight, 60);
    }

    /**
     * Sets new value for the amount per body weight in kg
     *
     * @param newAmountPerBodyWeight the new new 'Amount Per Body Weight' in kg to set
     * @return True on success setting the new value. Otherwise false.
     */
    public static boolean setOpcAmountPerBodyWeightInKg(double newAmountPerBodyWeight, Context context) {
        return new Global(context).putDouble(R.string.c_opc_amount_per_body_weight, newAmountPerBodyWeight);
    }

    /**
     * Get the body weight saved in the shared preferences
     *
     * @return the body weight saved in the shared preferences
     */
    public static Double getOpcAmountPerBodyWeight(Context context) {
        return new Global(context).getDoubleByKey(R.string.c_opc_amount_per_body_weight, 3);
    }

    /**
     * Sets new value for the OPC share within the grape seed extract
     *
     * @param newOpcShareWithinGrapeSeedExtract the new 'Opc Share Within the Grape Seed Extract' in % to set
     * @return True on success setting the new value. Otherwise false.
     */
    public static boolean setOpcShareWithinGrapeSeedExtract(double newOpcShareWithinGrapeSeedExtract, Context context) {
        return new Global(context).putDouble(R.string.c_opc_share_within_grape_seed_extract, newOpcShareWithinGrapeSeedExtract);
    }

    /**
     * Get the 'Opc Share Within Grape Seed Extract' saved in the shared preferences
     *
     * @return the 'Opc Share Within Grape Seed Extract' saved in the shared preferences
     */
    public static Double getOpcShareWithinGrapeSeedExtract(Context context) {
        return new Global(context).getDoubleByKey(R.string.c_opc_share_within_grape_seed_extract, 40);
    }
}
