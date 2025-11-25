package com.example.rere;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;

public class AlarmUtils {

    public static void scheduleExactAlarm(AlarmManager alarmManager,
                                          int alarmType,
                                          long triggerAtMillis,
                                          PendingIntent pendingIntent) {
        if (alarmManager == null) return;

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // On Android 12+ exact alarms can be restricted
                if (alarmManager.canScheduleExactAlarms()) {
                    alarmManager.setExact(alarmType, triggerAtMillis, pendingIntent);
                } else {
                    // Fallback: inexact alarm if exact is not allowed
                    alarmManager.set(alarmType, triggerAtMillis, pendingIntent);
                }
            } else {
                // Pre-Android 12: safe to call setExact
                alarmManager.setExact(alarmType, triggerAtMillis, pendingIntent);
            }
        } catch (SecurityException e) {
            // If something still goes wrong, fallback to inexact alarm
            alarmManager.set(alarmType, triggerAtMillis, pendingIntent);
        }
    }
}
