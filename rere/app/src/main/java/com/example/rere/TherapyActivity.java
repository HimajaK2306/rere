package com.example.rere;

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
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;

public class TherapyActivity extends AppCompatActivity {

    private EditText etTherapyType, etTherapyTime;
    private Button btnSaveTherapy, btnBack;
    private LinearLayout therapyListContainer;
    private List<String> therapySessions = new ArrayList<>();

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
        therapyListContainer = findViewById(R.id.therapyListContainer);

        addStubTherapySessions();
        loadTherapySessions();

        btnSaveTherapy.setOnClickListener(v -> saveTherapySession());
        btnBack.setOnClickListener(v -> finish());
    }

    private void scheduleReminder(String time, String message) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
            Date date = sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            Intent intent = new Intent(this, ReminderReceiver.class);
            intent.putExtra("message", message);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this, (int) System.currentTimeMillis(), intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }

            Toast.makeText(this, "Reminder scheduled!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Invalid time format (use hh:mm AM/PM)", Toast.LENGTH_SHORT).show();
        }
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
        String time = etTherapyTime.getText().toString().trim();

        if (type.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String session = "Type: " + type + " | Time: " + time;
        therapySessions.add(session);
        loadTherapySessions();

        etTherapyType.setText("");
        etTherapyTime.setText("");

        Toast.makeText(this, "Therapy session saved!", Toast.LENGTH_SHORT).show();

        // 🔔 Notification
        NotificationHelper.showNotification(
                this,
                "Therapy Session Saved",
                type + " at " + time
        );

        // ⏰ Schedule reminder
        scheduleReminder(time, "Therapy Reminder: " + type + " at " + time);
    }
}
