package com.example.rere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddMed, btnRemoveMed, btnSleepTracker, btnAppointmentTracker, btnTherapy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize buttons
        btnAddMed = findViewById(R.id.btnAddMed);
        btnRemoveMed = findViewById(R.id.btnRemoveMed);
        btnSleepTracker = findViewById(R.id.btnSleepTracker);
        btnAppointmentTracker = findViewById(R.id.btnAppointmentTracker);
        btnTherapy = findViewById(R.id.btnTherapy);

        // Set click listeners
        btnAddMed.setOnClickListener(this);
        btnRemoveMed.setOnClickListener(this);
        btnSleepTracker.setOnClickListener(this);
        btnAppointmentTracker.setOnClickListener(this);
        btnTherapy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        int id = v.getId();

        if (id == R.id.btnAddMed) {
            intent = new Intent(this, AddMedicationActivity.class);
        } else if (id == R.id.btnRemoveMed) {
            intent = new Intent(this, RemoveMedicationActivity.class);
        } else if (id == R.id.btnSleepTracker) {
            intent = new Intent(this, SleepTrackerActivity.class);
        } else if (id == R.id.btnAppointmentTracker) {
            intent = new Intent(this, AppointmentTrackerActivity.class);
        } else if (id == R.id.btnTherapy) {
            intent = new Intent(this, TherapyActivity.class);
        } else {
            return; // no match
        }

        startActivity(intent);
    }
}
