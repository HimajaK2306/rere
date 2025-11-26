package com.example.rere;

import android.content.Context;
import android.content.SharedPreferences;

public class OverviewStatsManager {

    private static SharedPreferences prefsForUser(Context context) {
        SharedPreferences session =
                context.getSharedPreferences("session", Context.MODE_PRIVATE);

        String active = session.getString("active_user", "default");

        String prefsName = "overview_stats_" + active;

        return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
    }

    public static int get(Context context, String key) {
        return prefsForUser(context).getInt(key, 0);
    }

    public static void set(Context context, String key, int value) {
        prefsForUser(context).edit().putInt(key, value).apply();
    }

    public static void increment(Context context, String key) {
        SharedPreferences p = prefsForUser(context);
        int current = p.getInt(key, 0);
        p.edit().putInt(key, current + 1).apply();
    }

    public static void clear(Context context, String key) {
        prefsForUser(context).edit().remove(key).apply();
    }
}
