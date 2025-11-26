// File: app/src/main/java/com/example/rere/TherapyActivity.java

package com.example.rere;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
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
import java.util.List;
import java.util.Locale;

public class TherapyActivity extends AppCompatActivity {

    private EditText etTherapyType, etTherapyTime;
    private Button btnSaveTherapy, btnBack, btnPickTime;
    private LinearLayout therapyListContainer;
    private List<String> therapySessions = new ArrayList<>();
    private Calendar selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        etTherapyType = findViewById(R.id.etTherapyType);
        etTherapyTime = findViewById(R.id.etTherapyTime);
        btnSaveTherapy = findViewById(R.id.btnSaveTherapy);
        btnBack = findViewById(R.id.buttonBack);
        btnPickTime = findViewById(R.id.buttonPickTime);
        therapyListContainer = findViewById(R.id.therapyListContainer);

        addStubTherapySessions();
        loadTherapySessions();

        btnPickTime.setOnClickListener(v -> showTimePicker());
        btnSaveTherapy.setOnClickListener(v -> saveTherapySession());
        btnBack.setOnClickListener(v -> finish());
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(
                this,
                (view, selectedHour, selectedMinute) -> {
                    selectedTime = Calendar.getInstance();
                    selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                    selectedTime.set(Calendar.MINUTE, selectedMinute);
                    selectedTime.set(Calendar.SECOND, 0);
                    selectedTime.set(Calendar.MILLISECOND, 0);

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
                    etTherapyTime.setText(sdf.format(selectedTime.getTime()));
                },
                hour,
                minute,
                false
        );
        dialog.show();
    }

    private void addStubTherapySessions() {
        therapySessions.add("Type: Physical Therapy | Time: 4:00 PM");
        therapySessions.add("Type: Occupational Therapy | Time: 10:00 AM");
        therapySessions.add("Type: Speech Therapy | Time: 2:00 PM");
    }

    private void loadTherapySessions() {
        therapyListContainer.removeAllViews();
        for (String session : therapySessions) {
            TextView tv = new TextView(this);
            tv.setText(session);
            tv.setPadding(8, 8, 8, 8);
            therapyListContainer.addView(tv);
        }
    }

    private void saveTherapySession() {
        String type = etTherapyType.getText().toString().trim();

        if (type.isEmpty() || selectedTime == null) {
            Toast.makeText(this, "Enter therapy type and pick a time", Toast.LENGTH_SHORT).show();
            return;
        }

        // increment therapy counter for Quick Overview
        OverviewStatsManager.increment(this, "therapy");

        String timeStr = new SimpleDateFormat("hh:mm a", Locale.US).format(selectedTime.getTime());
        String session = "Type: " + type + " | Time: " + timeStr;
        therapySessions.add(session);
        loadTherapySessions();

        // ✅ Schedule reminder BEFORE clearing selectedTime
        Calendar reminderCal = (Calendar) selectedTime.clone();
        if (reminderCal.getTimeInMillis() < System.currentTimeMillis()) {
            reminderCal.add(Calendar.DAY_OF_YEAR, 1);
        }

        NotificationHelper.showNotification(
                this,
                "Therapy Session Saved",
                type + " at " + timeStr
        );

        scheduleReminder(reminderCal, "Therapy Reminder: " + type + " at " + timeStr);

        etTherapyType.setText("");
        etTherapyTime.setText("");
        selectedTime = null;

        Toast.makeText(this, "Therapy session saved", Toast.LENGTH_SHORT).show();
    }

    private void scheduleReminder(Calendar calendar, String message) {
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
}
