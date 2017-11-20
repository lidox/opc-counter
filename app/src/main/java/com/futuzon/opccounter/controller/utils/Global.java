package com.futuzon.opccounter.controller.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.futuzon.opccounter.controller.config.App;

public class Global {

    private static String className = Global.class.getSimpleName();
    private static Context ctx = null;

    public Global(Context ctx){
        this.ctx = ctx;
    }

    /**
     * Sets the context in order to be able to run unit tests
     *
     * @param ctx the context to set
     */
    public void setAppContext(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Get the App Context by Object in order to be able to run unit tests
     *
     * @return the App context
     */
    @Nullable
    public static Context getAppContext() {
        Context appContext = ctx;
        if (appContext == null)
            ctx = App.getAppContext();
        return appContext;
    }

    /**
     * Returns the double value standing behind the key from shared preferences
     *
     * @param keyId    the key id to be used
     * @param defValue the default value to return if no value set
     * @return the double value standing behind the key from shared preferences
     */
    public static double getDoubleByKey(int keyId, double defValue) {
        Context appContext = getAppContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(appContext);
        String key = appContext.getResources().getString(keyId);
        return (double) prefs.getFloat(key, (float) defValue);
    }

    /**
     * Writes double into shared preferences using a key
     *
     * @param keyId    the key id to be used
     * @param newValue the new value to be set by the key
     * @return true if the new value has been set successfully. Otherwise false.
     */
    public static boolean putDouble(int keyId, double newValue) {
        boolean isFunctionSuccessful = false;
        Context appContext = getAppContext();
        SharedPreferences.Editor prefs = PreferenceManager.getDefaultSharedPreferences(appContext).edit();
        if (keyId != 0) {
            String key = appContext.getResources().getString(keyId);
            prefs.putFloat(key, (float) newValue);
            prefs.commit();
            isFunctionSuccessful = true;
            Log.d(className, "set new value for key(" + key + " ) = " + newValue);
        }
        return isFunctionSuccessful;
    }
}
