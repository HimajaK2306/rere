package com.example.rere;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RemoveMedicationActivity extends AppCompatActivity {

    private EditText etMedicationNameToRemove;
    private Button btnConfirmRemove, buttonBack;
    private LinearLayout medicationListContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_medication);

        // Optional: hide the ActionBar for a cleaner look
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // Initialize views
        etMedicationNameToRemove = findViewById(R.id.etMedicationNameToRemove);
        btnConfirmRemove = findViewById(R.id.btnConfirmRemove);
        buttonBack = findViewById(R.id.buttonBack);
        medicationListContainer = findViewById(R.id.medicationListContainer);

        // Add some sample medications to the list
        addStubMedications();

        // Set button listeners
        btnConfirmRemove.setOnClickListener(v -> removeMedication());
        buttonBack.setOnClickListener(v -> finish());
    }

    private void addStubMedications() {
        String[] stubs = {
                "Paracetamol - 500mg - Twice a day",
                "Ibuprofen - 200mg - Once a day",
                "Vitamin C - 1000mg - Once a day"
        };

        for (String med : stubs) {
            TextView medView = new TextView(this);
            medView.setText(med);
            medView.setPadding(8, 8, 8, 8);
            medicationListContainer.addView(medView);
        }
    }

    private void removeMedication() {
        String medName = etMedicationNameToRemove.getText().toString().trim();

        if (medName.isEmpty()) {
            Toast.makeText(this, "Please enter a medication name", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean removed = false;
        int childCount = medicationListContainer.getChildCount();

        for (int i = 0; i < childCount; i++) {
            TextView child = (TextView) medicationListContainer.getChildAt(i);
            if (child.getText().toString().toLowerCase().contains(medName.toLowerCase())) {
                medicationListContainer.removeView(child);
                removed = true;
                break;
            }
        }

        if (removed) {
            Toast.makeText(this, medName + " removed!", Toast.LENGTH_SHORT).show();
            NotificationHelper.showNotification(this, "Medication Removed", medName + " has been removed.");
        } else {
            Toast.makeText(this, "Medication not found", Toast.LENGTH_SHORT).show();
        }

        etMedicationNameToRemove.setText("");
    }
}
