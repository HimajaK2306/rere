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

public class AddMedicationActivity extends AppCompatActivity {

    private EditText editTextName, editTextDosage, editTextFrequency, editTextTime;
    private Button buttonSave, buttonBack, buttonPickTime;
    private LinearLayout medicationListContainer;
    private List<String> medicationList = new ArrayList<>();

    private Calendar selectedTime; // store chosen time

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        editTextName = findViewById(R.id.editTextName);
        editTextDosage = findViewById(R.id.editTextDosage);
        editTextFrequency = findViewById(R.id.editTextFrequency);
        editTextTime = findViewById(R.id.editTextTime);
        buttonSave = findViewById(R.id.buttonSave);
        buttonBack = findViewById(R.id.buttonBack);
        buttonPickTime = findViewById(R.id.buttonPickTime); // extra button for time picker
        medicationListContainer = findViewById(R.id.medicationListContainer);

        // Save button click
        buttonSave.setOnClickListener(v -> saveMedication());

        // Back button click
        buttonBack.setOnClickListener(v -> finish());

        // Time picker button click
        buttonPickTime.setOnClickListener(v -> showTimePicker());
    }

    private void showTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(this,
                (view, selectedHour, selectedMinute) -> {
                    selectedTime = Calendar.getInstance();
                    selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                    selectedTime.set(Calendar.MINUTE, selectedMinute);
                    selectedTime.set(Calendar.SECOND, 0);
                    selectedTime.set(Calendar.MILLISECOND, 0);

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
                    editTextTime.setText(sdf.format(selectedTime.getTime()));
                }, hour, minute, false);
        timePicker.show();
    }

    private void saveMedication() {
        String name = editTextName.getText().toString().trim();
        String dosage = editTextDosage.getText().toString().trim();
        String frequency = editTextFrequency.getText().toString().trim();
        String time = editTextTime.getText().toString().trim();

        if (name.isEmpty() || dosage.isEmpty() || frequency.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save to list
        medicationList.add(name + " - " + dosage + " - " + frequency + " - " + time);
        refreshMedicationList();

        Toast.makeText(this, "Medication Saved!", Toast.LENGTH_SHORT).show();

        // 🔔 Instant notification
        NotificationHelper.showNotification(
                this,
                "Medication Added",
                name + " (" + dosage + ", " + frequency + ") has been saved."
        );

        // ⏰ Scheduled reminder
        if (selectedTime != null) {
            scheduleReminder(selectedTime, "Time to take " + name + " (" + dosage + ")");
        }

        // Clear fields
        editTextName.setText("");
        editTextDosage.setText("");
        editTextFrequency.setText("");
        editTextTime.setText("");
    }

    private void refreshMedicationList() {
        medicationListContainer.removeAllViews();
        for (String med : medicationList) {
            TextView tv = new TextView(this);
            tv.setText(med);
            tv.setPadding(8, 8, 8, 8);
            medicationListContainer.addView(tv);
        }
    }

    // 🔔 Schedule reminder method
    private void scheduleReminder(Calendar calendar, String message) {
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
    }
}
