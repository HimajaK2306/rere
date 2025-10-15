package com.example.rere;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Find buttons by their ID
        Button btnAddMed = findViewById(R.id.btnAddMed);
        Button btnRemoveMed = findViewById(R.id.btnRemoveMed);
        Button btnSleepTracker = findViewById(R.id.btnSleepTracker);
        Button btnAppointmentTracker = findViewById(R.id.btnAppointmentTracker);
        Button btnTherapy = findViewById(R.id.btnTherapy);

        // Set click listeners for all buttons
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
            startActivity(intent);
        } else if (id == R.id.btnRemoveMed) {
            intent = new Intent(this, RemoveMedicationActivity.class);
            startActivity(intent);
        } else if (id == R.id.btnSleepTracker) {
            intent = new Intent(this, SleepTrackerActivity.class);
            startActivity(intent);
        } else if (id == R.id.btnAppointmentTracker) {
            intent = new Intent(this, AppointmentTrackerActivity.class);
            startActivity(intent);
        } else if (id == R.id.btnTherapy) {
            intent = new Intent(this, TherapyActivity.class);
            startActivity(intent);
        }
    }
}
