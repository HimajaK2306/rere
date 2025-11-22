package com.example.rere;

import android.content.Context;
import android.content.SharedPreferences;

public class OverviewStatsManager {

    private static final String PREF_NAME = "overview_stats";
    private static final String KEY_ACTIVE_MED = "active_medications";
    private static final String KEY_SLEEP = "sleep_records";
    private static final String KEY_APPOINTMENTS = "upcoming_appointments";
    private static final String KEY_THERAPY = "therapy_sessions";

    private static SharedPreferences getPrefs(Context context) {
        return context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    private static void increment(Context context, String key) {
        SharedPreferences prefs = getPrefs(context);
        int value = prefs.getInt(key, 0);
        prefs.edit()
                .putInt(key, value + 1)
                .commit(); // force synchronous write
    }

    public static void incrementActiveMedications(Context context) {
        increment(context, KEY_ACTIVE_MED);
    }

    public static void incrementSleepRecords(Context context) {
        increment(context, KEY_SLEEP);
    }

    public static void incrementUpcomingAppointments(Context context) {
        increment(context, KEY_APPOINTMENTS);
    }

    public static void incrementTherapySessions(Context context) {
        increment(context, KEY_THERAPY);
    }

    public static int getActiveMedications(Context context) {
        return getPrefs(context).getInt(KEY_ACTIVE_MED, 0);
    }

    public static int getSleepRecords(Context context) {
        return getPrefs(context).getInt(KEY_SLEEP, 0);
    }

    public static int getUpcomingAppointments(Context context) {
        return getPrefs(context).getInt(KEY_APPOINTMENTS, 0);
    }

    public static int getTherapySessions(Context context) {
        return getPrefs(context).getInt(KEY_THERAPY, 0);
    }
}
