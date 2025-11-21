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

public class AppointmentTrackerActivity extends AppCompatActivity {

    private EditText etClinicName, etAppointmentDate, etAppointmentTime;
    private Button btnSaveAppointment, btnBack;
    private LinearLayout appointmentListContainer;
    private List<String> appointments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_tracker);

        // Hide ActionBar if present
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        etClinicName = findViewById(R.id.etClinicName);
        etAppointmentDate = findViewById(R.id.etAppointmentDate);
        etAppointmentTime = findViewById(R.id.etAppointmentTime);
        btnSaveAppointment = findViewById(R.id.btnSaveAppointment);
        btnBack = findViewById(R.id.buttonBack);
        appointmentListContainer = findViewById(R.id.appointmentListContainer);

        // Save button click
        btnSaveAppointment.setOnClickListener(v -> saveAppointment());

        // Back button click
        btnBack.setOnClickListener(v -> finish());
    }

    private void saveAppointment() {
        String clinic = etClinicName.getText().toString().trim();
        String date = etAppointmentDate.getText().toString().trim();
        String time = etAppointmentTime.getText().toString().trim();

        if (clinic.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String newAppt = "Clinic: " + clinic + " | Date: " + date + " | Time: " + time;
        appointments.add(newAppt);
        refreshAppointmentList();

        Toast.makeText(this, "Appointment Saved!", Toast.LENGTH_SHORT).show();

        // 🔔 Instant notification
        NotificationHelper.showNotification(
                this,
                "Appointment Added",
                "Appointment at " + clinic + " on " + date + " at " + time
        );

        // ⏰ Scheduled reminder
        scheduleReminder(time, "Appointment at " + clinic + " on " + date + " at " + time);

        // Clear fields
        etClinicName.setText("");
        etAppointmentDate.setText("");
        etAppointmentTime.setText("");
    }

    private void refreshAppointmentList() {
        appointmentListContainer.removeAllViews();
        for (String appt : appointments) {
            TextView tv = new TextView(this);
            tv.setText(appt);
            tv.setPadding(8, 8, 8, 8);
            appointmentListContainer.addView(tv);
        }
    }

    // 🔔 Schedule reminder method
    private void scheduleReminder(String time, String message) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
            Date date = sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

            // If time is earlier than now, schedule for tomorrow
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
}
