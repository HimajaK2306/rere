// File: app/src/main/java/com/example/rere/HomePageActivity.java

package com.example.rere;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private static final int REQ_NOTIF_PERMISSION = 1001;

    private ImageButton btnAddMed, btnRemoveMed, btnSleepTracker, btnAppointmentTracker, btnTherapy;
    private TextView tvActiveMedCount, tvSleepCount, tvAppointmentCount, tvTherapyCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // ✅ Ask for notification permission on Android 13+
        requestNotificationPermissionIfNeeded();

        btnAddMed = findViewById(R.id.btnAddMed);
        btnRemoveMed = findViewById(R.id.btnRemoveMed);
        btnSleepTracker = findViewById(R.id.btnSleepTracker);
        btnAppointmentTracker = findViewById(R.id.btnAppointmentTracker);
        btnTherapy = findViewById(R.id.btnTherapy);

        tvActiveMedCount = findViewById(R.id.tvActiveMedCount);
        tvSleepCount = findViewById(R.id.tvSleepCount);
        tvAppointmentCount = findViewById(R.id.tvAppointmentCount);
        tvTherapyCount = findViewById(R.id.tvTherapyCount);

        btnAddMed.setOnClickListener(v ->
                startActivity(new Intent(this, AddMedicationActivity.class)));

        btnRemoveMed.setOnClickListener(v ->
                startActivity(new Intent(this, RemoveMedicationActivity.class)));

        btnSleepTracker.setOnClickListener(v ->
                startActivity(new Intent(this, SleepTrackerActivity.class)));

        btnAppointmentTracker.setOnClickListener(v ->
                startActivity(new Intent(this, AppointmentTrackerActivity.class)));

        btnTherapy.setOnClickListener(v ->
                startActivity(new Intent(this, TherapyActivity.class)));

        updateOverviewCounts();
    }

    private void requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        REQ_NOTIF_PERMISSION
                );
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateOverviewCounts();
    }

    private void updateOverviewCounts() {
        // Medication count = size of stored list
        List<String> meds = MedicationStorage.getMedications(this);
        int activeMeds = meds.size();
        tvActiveMedCount.setText(String.valueOf(activeMeds));

        // Other stats
        int sleep = OverviewStatsManager.get(this, "sleep");
        tvSleepCount.setText(String.valueOf(sleep));

        int appointments = OverviewStatsManager.get(this, "appointments");
        tvAppointmentCount.setText(String.valueOf(appointments));

        int therapy = OverviewStatsManager.get(this, "therapy");
        tvTherapyCount.setText(String.valueOf(therapy));
    }
}
