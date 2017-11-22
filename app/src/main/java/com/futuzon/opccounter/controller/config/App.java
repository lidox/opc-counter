package com.futuzon.opccounter.controller.config;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Dependencies:
 * 1. Add manifest:
 * <application
 *    android:name="com.artursworld.playground.App"
 */
public class App extends Application {

    private static Context ctx = null;

    @Override
    public void onCreate() {
        Log.i(App.class.getSimpleName(), "running application");
        super.onCreate();
        ctx = this.getApplicationContext();
    }

    public static Context getAppContext(){
        return ctx;
    }

    /**
     * Get the string by resource id
     * @param resId the resource id
     * @return the string by resource id
     */
    public static String getStringByRId(int resId) {
        return ctx.getResources().getString(resId);
    }
}
