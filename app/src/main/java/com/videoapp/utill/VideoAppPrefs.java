package com.videoapp.utill;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rbpatel on 4/25/2017
 */

public class VideoAppPrefs {
    private static SharedPreferences sharedPreferences = null;
    private static final String KENT_SECURITY_PREFS = "VideoApp";


    public static SharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KENT_SECURITY_PREFS, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }


    public static String getString(Context context, String key) {
        getInstance(context);
        return sharedPreferences.getString(key, "");
    }

    public static void putString(Context context, String key, String value) {
        getInstance(context);
        sharedPreferences.edit().putString(key, value).apply();
    }


    public static int getInt(Context context, String key, int _default) {
        getInstance(context);
        return sharedPreferences.getInt(key, _default);
    }

    public static void putInt(Context context, String key, int value) {
        getInstance(context);
        sharedPreferences.edit().putInt(key, value).apply();
    }


    public static boolean getBoolean(Context context, String key) {
        getInstance(context);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getInstance(context);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }


    public static void clear(Context context) {
        getInstance(context);
        sharedPreferences.edit().clear().apply();
    }

    public static void putLong(Context context, String key, long value) {
        getInstance(context);
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key) {
        getInstance(context);
        return sharedPreferences.getLong(key, 0);
    }

    /**
     * Remove Value of Corresponding KEY
     *
     * @param context
     * @param key
     */
    public static void removeValue(Context context,String key){
        getInstance(context);
        sharedPreferences.edit().remove(key).apply();
    }

}
