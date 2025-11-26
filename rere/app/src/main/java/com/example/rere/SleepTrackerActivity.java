package com.example.rere;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SleepTrackerActivity extends AppCompatActivity {

    private EditText editTextBedTime, editTextMessage;
    private Button buttonPickTime, buttonSave, buttonBack;
    private TextView textCurrentTime;
    private LinearLayout sleepLogContainer;
    private final List<String> sleepLogs = new ArrayList<>();
    private Calendar selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tracker);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        textCurrentTime = findViewById(R.id.textCurrentTime);
        editTextBedTime = findViewById(R.id.editTextBedTime);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonPickTime = findViewById(R.id.buttonPickTime);
        buttonSave = findViewById(R.id.buttonSave);
        buttonBack = findViewById(R.id.buttonBack);
        sleepLogContainer = findViewById(R.id.sleepLogContainer);

        String currentTime = new SimpleDateFormat("hh:mma", Locale.US).format(new Date());
        textCurrentTime.setText(currentTime);

        sleepLogs.add("Bedtime: 10:00 PM | Message: Time to sleep | Logged at: 09:30 PM");
        sleepLogs.add("Bedtime: 11:00 PM | Message: Get ready for bed | Logged at: 10:30 PM");
        refreshSleepLogs();

        buttonPickTime.setOnClickListener(v -> showTimePicker());
        buttonSave.setOnClickListener(v -> saveSleepLog());
        buttonBack.setOnClickListener(v -> finish());
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        android.app.TimePickerDialog dialog = new android.app.TimePickerDialog(
                this,
                (view, selectedHour, selectedMinute) -> {
                    selectedTime = Calendar.getInstance();
                    selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                    selectedTime.set(Calendar.MINUTE, selectedMinute);
                    selectedTime.set(Calendar.SECOND, 0);
                    selectedTime.set(Calendar.MILLISECOND, 0);

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
                    editTextBedTime.setText(sdf.format(selectedTime.getTime()));
                },
                hour,
                minute,
                false
        );
        dialog.show();
    }

    private void saveSleepLog() {
        String message = editTextMessage.getText().toString().trim();

        if (selectedTime == null || message.isEmpty()) {
            Toast.makeText(this, "Select bedtime and enter a message", Toast.LENGTH_SHORT).show();
            return;
        }

        // FIXED — unified counter system
        OverviewStatsManager.increment(this, "sleep");

        String bedtimeStr = new SimpleDateFormat("hh:mm a", Locale.US).format(selectedTime.getTime());
        String loggedAtStr = new SimpleDateFormat("hh:mm a", Locale.US).format(new Date());

        String log = "Bedtime: " + bedtimeStr + " | Message: " + message + " | Logged at: " + loggedAtStr;
        sleepLogs.add(log);
        refreshSleepLogs();

        NotificationHelper.showNotification(
                this,
                "Sleep Reminder Set",
                "At " + bedtimeStr + ": " + message
        );

        scheduleReminder(selectedTime, "Sleep Reminder: " + message);

        Toast.makeText(this, "Sleep reminder saved", Toast.LENGTH_SHORT).show();

        editTextBedTime.setText("");
        editTextMessage.setText("");
        selectedTime = null;
    }

    private void scheduleReminder(Calendar calendar, String message) {
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        Intent intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("message", message);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                (int) System.currentTimeMillis(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        AlarmUtils.scheduleExactAlarm(
                alarmManager,
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                pendingIntent
        );

    }

    private void refreshSleepLogs() {
        sleepLogContainer.removeAllViews();
        for (String log : sleepLogs) {
            TextView tv = new TextView(this);
            tv.setText(log);
            tv.setPadding(8, 8, 8, 8);
            sleepLogContainer.addView(tv);
        }
    }
}
