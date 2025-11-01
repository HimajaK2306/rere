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

public class TherapyActivity extends AppCompatActivity {

    private EditText etTherapyType, etTherapyTime;
    private Button btnSaveTherapy, btnBack;
    private LinearLayout therapyListContainer;

    private List<String> therapySessions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);

        // ✅ Remove the top ActionBar/Toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        etTherapyType = findViewById(R.id.etTherapyType);
        etTherapyTime = findViewById(R.id.etTherapyTime);
        btnSaveTherapy = findViewById(R.id.btnSaveTherapy);
        btnBack = findViewById(R.id.buttonBack);
        therapyListContainer = findViewById(R.id.therapyListContainer);

        // Add stub therapy sessions
        addStubTherapySessions();

        // Load sessions into UI
        loadTherapySessions();

        // Save button click
        btnSaveTherapy.setOnClickListener(v -> saveTherapySession());

        // Back button click
        btnBack.setOnClickListener(v -> finish());
    }

    private void addStubTherapySessions() {
        therapySessions.add("Type: Physical Therapy | Time: 4:00 PM");
        therapySessions.add("Type: Occupational Therapy | Time: 10:00 AM");
        therapySessions.add("Type: Speech Therapy | Time: 2:00 PM");
    }

    private void loadTherapySessions() {
        therapyListContainer.removeAllViews();
        for (String session : therapySessions) {
            TextView tv = new TextView(this);
            tv.setText(session);
            tv.setPadding(8, 8, 8, 8);
            therapyListContainer.addView(tv);
        }
    }

    private void saveTherapySession() {
        String type = etTherapyType.getText().toString().trim();
        String time = etTherapyTime.getText().toString().trim();

        if (type.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String session = "Type: " + type + " | Time: " + time;
        therapySessions.add(session);

        loadTherapySessions();

        etTherapyType.setText("");
        etTherapyTime.setText("");

        Toast.makeText(this, "Therapy session saved!", Toast.LENGTH_SHORT).show();
    }
}
