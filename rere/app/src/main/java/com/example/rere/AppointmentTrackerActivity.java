package com.example.rere;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AppointmentTrackerActivity extends AppCompatActivity {

    private EditText etClinicName, etAppointmentDate, etAppointmentTime;
    private Button btnSaveAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_tracker);

        // Set up custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Appointment Tracker");
        }

        // Initialize views
        etClinicName = findViewById(R.id.etClinicName);
        etAppointmentDate = findViewById(R.id.etAppointmentDate);
        etAppointmentTime = findViewById(R.id.etAppointmentTime);
        btnSaveAppointment = findViewById(R.id.btnSaveAppointment);

        btnSaveAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String clinicName = etClinicName.getText().toString();
                if (clinicName.isEmpty()) {
                    Toast.makeText(AppointmentTrackerActivity.this, "Please enter a clinic name", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AppointmentTrackerActivity.this, "Appointment saved!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
