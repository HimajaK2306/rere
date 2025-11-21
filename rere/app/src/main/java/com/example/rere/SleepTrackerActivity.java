package com.example.rere;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SleepTrackerActivity extends AppCompatActivity {

    private EditText editTextGoal, editTextBedTime, editTextWakeUpTime;
    private Button buttonSave, buttonBack;
    private TextView textCurrentTime;
    private LinearLayout sleepLogContainer;
    private final List<String> sleepLogs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tracker);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        textCurrentTime = findViewById(R.id.textCurrentTime);
        editTextGoal = findViewById(R.id.editTextGoal);
        editTextBedTime = findViewById(R.id.editTextBedTime);
        editTextWakeUpTime = findViewById(R.id.editTextWakeUpTime);
        buttonSave = findViewById(R.id.buttonSave);
        buttonBack = findViewById(R.id.buttonBack);
        sleepLogContainer = findViewById(R.id.sleepLogContainer);

        // Show current time
        String currentTime = new SimpleDateFormat("hh:mma", Locale.US).format(new Date());
        textCurrentTime.setText(currentTime);

        // 🔹 Seed sample logs (hard-coded)
        sleepLogs.add("Goal: 8h | Bedtime: 10:00 PM | Wake-up: 6:00 AM");
        sleepLogs.add("Goal: 7h | Bedtime: 11:00 PM | Wake-up: 6:00 AM");
        sleepLogs.add("Goal: 6h | Bedtime: 12:00 AM | Wake-up: 6:00 AM");
        sleepLogs.add("Goal: 2 hr | Bedtime: 04:29pm | Wake-up: 04:30pm");

        refreshSleepLogs();

        buttonSave.setOnClickListener(v -> saveSleepLog());
        buttonBack.setOnClickListener(v -> finish());
    }


    private void saveSleepLog() {
        String goal = editTextGoal.getText().toString().trim();
        String bedTime = editTextBedTime.getText().toString().trim();
        String wakeUpTime = editTextWakeUpTime.getText().toString().trim();

        if (goal.isEmpty() || bedTime.isEmpty() || wakeUpTime.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String log = "Goal: " + goal + " | Bedtime: " + bedTime + " | Wake-up: " + wakeUpTime;
        sleepLogs.add(log);
        refreshSleepLogs();

        NotificationHelper.showNotification(this, "Sleep Log Saved", log);
        Toast.makeText(this, "Sleep log saved!", Toast.LENGTH_SHORT).show();

        // Clear fields after save
        editTextGoal.setText("");
        editTextBedTime.setText("");
        editTextWakeUpTime.setText("");
    }

    private void refreshSleepLogs() {
        sleepLogContainer.removeAllViews();
        for (String log : sleepLogs) {
            TextView tv = new TextView(this);
            tv.setText(log);
            tv.setPadding(8, 8, 8, 8);
            sleepLogContainer.addView(tv);
        }
    }
}
