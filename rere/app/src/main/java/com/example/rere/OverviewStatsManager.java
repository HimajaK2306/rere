package com.example.rere;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Simple integer counters for Quick Overview (sleep, appointments, therapy).
 */
public class OverviewStatsManager {

    private static final String PREFS_NAME = "overview_stats";

    private static SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static int get(Context context, String key) {
        return prefs(context).getInt(key, 0);
    }

    public static void set(Context context, String key, int value) {
        prefs(context).edit().putInt(key, value).apply();
    }

    public static void increment(Context context, String key) {
        SharedPreferences p = prefs(context);
        int current = p.getInt(key, 0);
        p.edit().putInt(key, current + 1).apply();
    }

    public static void clear(Context context, String key) {
        prefs(context).edit().remove(key).apply();
    }
}
 