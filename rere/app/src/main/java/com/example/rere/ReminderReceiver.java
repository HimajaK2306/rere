package com.example.rere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        Log.d("ReminderReceiver", "Triggered with message: " + message); // ✅ Log for debugging
        NotificationHelper.showNotification(context, "Health Reminder", message);
    }
}
