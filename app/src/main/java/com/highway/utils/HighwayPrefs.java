package com.highway.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class HighwayPrefs {
    private static final String KENT_SECURITY_PREFS = "HFCSecurity";
    private static SharedPreferences sharedPreferences;

    public static SharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(KENT_SECURITY_PREFS, 0);
        }
        return sharedPreferences;
    }

    public static String getString(Context context, String str) {
        getInstance(context);
        return sharedPreferences.getString(str, "");
    }

    public static void putString(Context context, String str, String str2) {
        getInstance(context);
        sharedPreferences.edit().putString(str, String.valueOf(str2)).apply();
    }

    public static int getInt(Context context, String str, int i) {
        getInstance(context);
        return sharedPreferences.getInt(str, i);
    }

    public static void putInt(Context context, String str, int i) {
        getInstance(context);
        sharedPreferences.edit().putInt(str, i).apply();
    }

    public static boolean getBoolean(Context context, String str) {
        getInstance(context);
        return sharedPreferences.getBoolean(str, false);
    }

    public static void putBoolean(Context context, String str, boolean z) {
        getInstance(context);
        sharedPreferences.edit().putBoolean(str, z).apply();
    }

    public static void clear(Context context) {
        getInstance(context);
        sharedPreferences.edit().clear().apply();
    }

    public static void putLong(Context context, String str, long j) {
        getInstance(context);
        sharedPreferences.edit().putLong(str, j).apply();
    }

    public static long getLong(Context context, String str) {
        getInstance(context);
        return sharedPreferences.getLong(str, 0);
    }


    public static boolean isFirstTimeLaunch(Context context,String key) {
        getInstance(context);
        return sharedPreferences.getBoolean(key, true);
    }

    public static boolean setFirstTimeLaunch(Context context,String key,boolean value) {
        getInstance(context);
        return sharedPreferences.edit().putBoolean(key, true).commit();
    }

}
