package com.example.rere;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppointmentTrackerActivity extends AppCompatActivity {

    private EditText etClinicName, etAppointmentDate, etAppointmentTime;
    private Button btnSaveAppointment, btnClear, btnBack;
    private LinearLayout appointmentListContainer;
    private final List<String> appointments = new ArrayList<>();
    private Calendar selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_tracker);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        etClinicName = findViewById(R.id.etClinicName);
        etAppointmentDate = findViewById(R.id.etAppointmentDate);
        etAppointmentTime = findViewById(R.id.etAppointmentTime);
        btnSaveAppointment = findViewById(R.id.btnSaveAppointment);
        btnClear = findViewById(R.id.buttonClearAppointment);
        btnBack = findViewById(R.id.buttonBack);
        appointmentListContainer = findViewById(R.id.appointmentListContainer);

        etAppointmentDate.setOnClickListener(v -> showDatePicker());
        etAppointmentTime.setOnClickListener(v -> showTimePicker());

        btnSaveAppointment.setOnClickListener(v -> saveAppointment());
        btnClear.setOnClickListener(v -> clearFields());
        btnBack.setOnClickListener(v -> finish());
    }

    private void showDatePicker() {
        Calendar now = Calendar.getInstance();

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, y, m, d) -> {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m, d);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    etAppointmentDate.setText(sdf.format(c.getTime()));
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();

        TimePickerDialog dialog = new TimePickerDialog(
                this,
                (view, h, m) -> {
                    selectedTime = Calendar.getInstance();
                    selectedTime.set(Calendar.HOUR_OF_DAY, h);
                    selectedTime.set(Calendar.MINUTE, m);

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
                    etAppointmentTime.setText(sdf.format(selectedTime.getTime()));
                },
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        dialog.show();
    }

    private void clearFields() {
        etClinicName.setText("");
        etAppointmentDate.setText("");
        etAppointmentTime.setText("");
    }

    private void saveAppointment() {
        String clinic = etClinicName.getText().toString().trim();
        String date = etAppointmentDate.getText().toString().trim();
        String time = etAppointmentTime.getText().toString().trim();

        if (clinic.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        OverviewStatsManager.incrementUpcomingAppointments(this);

        String newAppt = "Clinic: " + clinic + " | Date: " + date + " | Time: " + time;
        appointments.add(newAppt);
        refreshAppointmentList();

        NotificationHelper.showNotification(
                this,
                "Appointment Added",
                "Appointment at " + clinic + " on " + date + " at " + time
        );

        scheduleReminder(time, newAppt);
        clearFields();
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

    private void scheduleReminder(String time, String message) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
            Date date = sdf.parse(time);

            Calendar c = Calendar.getInstance();
            c.setTime(date);

            Intent intent = new Intent(this, ReminderReceiver.class);
            intent.putExtra("message", message);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    this,
                    (int) System.currentTimeMillis(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);

        } catch (Exception ignored) { }
    }
}
