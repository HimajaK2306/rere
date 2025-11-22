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
import java.util.List;
import java.util.Locale;

public class AddMedicationActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextDosage;
    private EditText editTextStartDate;
    private EditText editTextEndDate;
    private EditText editTextTime;
    private EditText editTextNotes;

    private Button buttonSave;
    private Button buttonCancel;
    private Button buttonBack;
    private Button buttonPickTime;

    private LinearLayout medicationListContainer;

    private final List<String> medicationList = new ArrayList<>();
    private Calendar selectedTime;   // for reminder scheduling
    private Calendar selectedStart;  // optional date usage
    private Calendar selectedEnd;    // optional date usage

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        editTextName = findViewById(R.id.editTextName);
        editTextDosage = findViewById(R.id.editTextDosage);
        editTextStartDate = findViewById(R.id.editTextStartDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextNotes = findViewById(R.id.editTextNotes);

        buttonSave = findViewById(R.id.buttonSave);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonBack = findViewById(R.id.buttonBack);
        buttonPickTime = findViewById(R.id.buttonPickTime);

        medicationListContainer = findViewById(R.id.medicationListContainer);

        medicationList.clear();
        medicationList.addAll(MedicationStorage.getMedications(this));
        refreshMedicationList();

        if (buttonSave != null) {
            buttonSave.setOnClickListener(v -> saveMedication());
        }

        if (buttonCancel != null) {
            buttonCancel.setOnClickListener(v -> clearFields());
        }

        if (buttonBack != null) {
            buttonBack.setOnClickListener(v -> finish());
        }

        if (buttonPickTime != null) {
            buttonPickTime.setOnClickListener(v -> showTimePicker());
        }

        if (editTextStartDate != null) {
            editTextStartDate.setOnClickListener(v -> showDatePicker(true));
        }

        if (editTextEndDate != null) {
            editTextEndDate.setOnClickListener(v -> showDatePicker(false));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        medicationList.clear();
        medicationList.addAll(MedicationStorage.getMedications(this));
        refreshMedicationList();
    }

    private void showTimePicker() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(
                this,
                (view, h, m) -> {
                    selectedTime = Calendar.getInstance();
                    selectedTime.set(Calendar.HOUR_OF_DAY, h);
                    selectedTime.set(Calendar.MINUTE, m);
                    selectedTime.set(Calendar.SECOND, 0);
                    selectedTime.set(Calendar.MILLISECOND, 0);

                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
                    editTextTime.setText(sdf.format(selectedTime.getTime()));
                },
                hour,
                minute,
                false
        );
        dialog.show();
    }

    private void showDatePicker(boolean isStart) {
        Calendar base = Calendar.getInstance();
        int year = base.get(Calendar.YEAR);
        int month = base.get(Calendar.MONTH);
        int day = base.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, y, m, d) -> {
                    Calendar picked = Calendar.getInstance();
                    picked.set(Calendar.YEAR, y);
                    picked.set(Calendar.MONTH, m);
                    picked.set(Calendar.DAY_OF_MONTH, d);

                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    String dateText = sdf.format(picked.getTime());

                    if (isStart) {
                        selectedStart = picked;
                        editTextStartDate.setText(dateText);
                    } else {
                        selectedEnd = picked;
                        editTextEndDate.setText(dateText);
                    }
                },
                year,
                month,
                day
        );
        dialog.show();
    }

    private void saveMedication() {
        String name = safeText(editTextName);
        String dosage = safeText(editTextDosage);
        String startDate = safeText(editTextStartDate);
        String endDate = safeText(editTextEndDate);
        String time = safeText(editTextTime);
        String notes = safeText(editTextNotes);

        if (name.isEmpty() || dosage.isEmpty() || startDate.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder entryBuilder = new StringBuilder();
        entryBuilder.append(name)
                .append(" - ")
                .append(dosage)
                .append(" - ")
                .append(startDate);

        if (!endDate.isEmpty()) {
            entryBuilder.append(" → ").append(endDate);
        }

        entryBuilder.append(" - ").append(time);

        if (!notes.isEmpty()) {
            entryBuilder.append(" (").append(notes).append(")");
        }

        String entry = entryBuilder.toString();

        medicationList.add(entry);
        MedicationStorage.saveMedications(this, medicationList);
        refreshMedicationList();

        OverviewStatsManager.incrementActiveMedications(this);

        NotificationHelper.showNotification(
                this,
                "Medication Added",
                name + " (" + dosage + ") has been saved."
        );

        if (selectedTime != null) {
            scheduleReminder(selectedTime, "Time to take " + name + " (" + dosage + ")");
        }

        clearFields();
        Toast.makeText(this, "Medication Saved!", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        if (editTextName != null) editTextName.setText("");
        if (editTextDosage != null) editTextDosage.setText("");
        if (editTextStartDate != null) editTextStartDate.setText("");
        if (editTextEndDate != null) editTextEndDate.setText("");
        if (editTextTime != null) editTextTime.setText("");
        if (editTextNotes != null) editTextNotes.setText("");

        selectedTime = null;
        selectedStart = null;
        selectedEnd = null;
    }

    private void refreshMedicationList() {
        if (medicationListContainer == null) return;

        medicationListContainer.removeAllViews();

        for (String med : medicationList) {
            TextView tv = new TextView(this);
            tv.setText(med);
            tv.setPadding(8, 8, 8, 8);
            tv.setTextColor(0xFF0F3D2E);
            medicationListContainer.addView(tv);
        }

        if (medicationList.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("No medications saved yet.");
            empty.setPadding(8, 8, 8, 8);
            empty.setTextColor(0xFF88A79A);
            medicationListContainer.addView(empty);
        }
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
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }

    private String safeText(EditText et) {
        if (et == null) return "";
        CharSequence cs = et.getText();
        return cs == null ? "" : cs.toString().trim();
    }
}
