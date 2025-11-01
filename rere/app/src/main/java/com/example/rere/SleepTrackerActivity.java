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

public class SleepTrackerActivity extends AppCompatActivity {

    private EditText etSleepGoal, etBedtime, etWakeUpTime;
    private Button btnSaveSleep, btnBack;
    private LinearLayout sleepLogContainer;

    private List<String> sleepLogs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tracker);

        // ✅ Hide the default top ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        etSleepGoal = findViewById(R.id.etSleepGoal);
        etBedtime = findViewById(R.id.etBedtime);
        etWakeUpTime = findViewById(R.id.etWakeUpTime);
        btnSaveSleep = findViewById(R.id.btnSaveSleep);
        btnBack = findViewById(R.id.buttonBack);
        sleepLogContainer = findViewById(R.id.sleepLogContainer);

        // Add stub logs
        addStubSleepLogs();

        // Load logs into UI
        loadSleepLogs();

        // Save button click
        btnSaveSleep.setOnClickListener(v -> saveSleepLog());

        // Back button click
        btnBack.setOnClickListener(v -> finish());
    }

    private void addStubSleepLogs() {
        sleepLogs.add("Goal: 8h | Bedtime: 10:00 PM | Wake-up: 6:00 AM");
        sleepLogs.add("Goal: 7h | Bedtime: 11:00 PM | Wake-up: 6:00 AM");
        sleepLogs.add("Goal: 6h | Bedtime: 12:00 AM | Wake-up: 6:00 AM");
    }

    private void loadSleepLogs() {
        sleepLogContainer.removeAllViews();
        for (String log : sleepLogs) {
            TextView tv = new TextView(this);
            tv.setText(log);
            tv.setPadding(8, 8, 8, 8);
            sleepLogContainer.addView(tv);
        }
    }

    private void saveSleepLog() {
        String goal = etSleepGoal.getText().toString().trim();
        String bedtime = etBedtime.getText().toString().trim();
        String wakeup = etWakeUpTime.getText().toString().trim();

        if (goal.isEmpty() || bedtime.isEmpty() || wakeup.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String log = "Goal: " + goal + " | Bedtime: " + bedtime + " | Wake-up: " + wakeup;
        sleepLogs.add(log);

        // Refresh UI
        loadSleepLogs();

        // Clear fields
        etSleepGoal.setText("");
        etBedtime.setText("");
        etWakeUpTime.setText("");

        Toast.makeText(this, "Sleep log saved!", Toast.LENGTH_SHORT).show();
    }
}
