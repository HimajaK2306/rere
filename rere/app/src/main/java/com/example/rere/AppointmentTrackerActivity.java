package com.example.rere;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AppointmentTrackerActivity extends AppCompatActivity {

    private EditText etClinicName, etAppointmentDate, etAppointmentTime;
    private Button btnSaveAppointment, btnBack;
    private LinearLayout appointmentListContainer;

    private List<String> appointments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_tracker);

        // ✅ Hide the default top ActionBar
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

        // Add stub appointments
        addStubAppointments();

        // Load appointments into UI
        loadAppointments();

        // Save button click
        btnSaveAppointment.setOnClickListener(v -> saveAppointment());

        // Back button click
        btnBack.setOnClickListener(v -> finish());
    }

    private void addStubAppointments() {
        appointments.add("Clinic: City Hospital | Date: 25-12-2025 | Time: 2:00 PM");
        appointments.add("Clinic: ABC Clinic | Date: 28-12-2025 | Time: 10:30 AM");
        appointments.add("Clinic: HealthCare Center | Date: 31-12-2025 | Time: 4:00 PM");
    }

    private void loadAppointments() {
        appointmentListContainer.removeAllViews();
        for (String appt : appointments) {
            TextView tv = new TextView(this);
            tv.setText(appt);
            tv.setPadding(8, 8, 8, 8);
            appointmentListContainer.addView(tv);
        }
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

        // Refresh UI
        loadAppointments();

        // Clear input fields
        etClinicName.setText("");
        etAppointmentDate.setText("");
        etAppointmentTime.setText("");

        Toast.makeText(this, "Appointment saved!", Toast.LENGTH_SHORT).show();
    }
}