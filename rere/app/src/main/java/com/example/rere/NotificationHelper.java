package com.example.rere;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {
    public static void showNotification(Context context, String title, String message) {
        NotificationChannelSetup.createChannel(context); // ✅ Ensure channel exists

        // ✅ Check POST_NOTIFICATIONS permission on Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                return; // 🚫 Permission not granted, don't show notification
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "health_reminder_channel")
                .setSmallIcon(R.mipmap.ic_launcher) // ✅ Use valid icon from mipmap
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify((int) System.currentTimeMillis(), builder.build());
    }
}
