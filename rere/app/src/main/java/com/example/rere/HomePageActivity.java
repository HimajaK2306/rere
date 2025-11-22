package com.example.rere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnAddMed, btnRemoveMed, btnSleepTracker, btnAppointmentTracker, btnTherapy;
    private TextView tvActiveMedCount, tvSleepCount, tvAppointmentCount, tvTherapyCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btnAddMed = findViewById(R.id.btnAddMed);
        btnRemoveMed = findViewById(R.id.btnRemoveMed);
        btnSleepTracker = findViewById(R.id.btnSleepTracker);
        btnAppointmentTracker = findViewById(R.id.btnAppointmentTracker);
        btnTherapy = findViewById(R.id.btnTherapy);


        tvActiveMedCount = findViewById(R.id.tvActiveMedCount);
        tvSleepCount = findViewById(R.id.tvSleepCount);
        tvAppointmentCount = findViewById(R.id.tvAppointmentCount);
        tvTherapyCount = findViewById(R.id.tvTherapyCount);

        btnAddMed.setOnClickListener(this);
        btnRemoveMed.setOnClickListener(this);
        btnSleepTracker.setOnClickListener(this);
        btnAppointmentTracker.setOnClickListener(this);
        btnTherapy.setOnClickListener(this);

        updateOverviewCounts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateOverviewCounts();
    }

    private void updateOverviewCounts() {
        if (tvActiveMedCount == null) {
            tvActiveMedCount = findViewById(R.id.tvActiveMedCount);
            tvSleepCount = findViewById(R.id.tvSleepCount);
            tvAppointmentCount = findViewById(R.id.tvAppointmentCount);
            tvTherapyCount = findViewById(R.id.tvTherapyCount);
        }

        tvActiveMedCount.setText(String.valueOf(OverviewStatsManager.getActiveMedications(this)));
        tvSleepCount.setText(String.valueOf(OverviewStatsManager.getSleepRecords(this)));
        tvAppointmentCount.setText(String.valueOf(OverviewStatsManager.getUpcomingAppointments(this)));
        tvTherapyCount.setText(String.valueOf(OverviewStatsManager.getTherapySessions(this)));
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
            return;
        }

        startActivity(intent);
    }
}
