package com.example.rere;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.List;

public class AddMedicationActivity extends AppCompatActivity {

    private EditText editTextName, editTextDosage, editTextFrequency;
    private LinearLayout medicationListContainer;
    private Button buttonSave, buttonBack;

    private final List<String> medicationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        editTextName = findViewById(R.id.editTextName);
        editTextDosage = findViewById(R.id.editTextDosage);
        editTextFrequency = findViewById(R.id.editTextFrequency);
        buttonSave = findViewById(R.id.buttonSave);
        buttonBack = findViewById(R.id.buttonBack);
        medicationListContainer = findViewById(R.id.medicationListContainer);

        // Stubbed Data (simulate database data)
        medicationList.addAll(getStubbedMedications());
        refreshMedicationList();

        // Save Button Logic
        buttonSave.setOnClickListener(view -> saveMedication());

        // Back Button Logic
        buttonBack.setOnClickListener(view -> {
            Intent intent = new Intent(AddMedicationActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void saveMedication() {
        String name = editTextName.getText().toString().trim();
        String dosage = editTextDosage.getText().toString().trim();
        String frequency = editTextFrequency.getText().toString().trim();

        if (name.isEmpty() || dosage.isEmpty() || frequency.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        medicationList.add(name + " - " + dosage + " - " + frequency);
        Toast.makeText(this, "Medication Saved!", Toast.LENGTH_SHORT).show();
        refreshMedicationList();

        // Clear fields
        editTextName.setText("");
        editTextDosage.setText("");
        editTextFrequency.setText("");
    }

    private void refreshMedicationList() {
        medicationListContainer.removeAllViews();
        for (String item : medicationList) {
            MaterialCardView card = new MaterialCardView(this);
            card.setCardElevation(4);
            card.setRadius(16);
            card.setCardBackgroundColor(getColor(android.R.color.white));
            card.setUseCompatPadding(true);
            card.setContentPadding(24, 24, 24, 24);

            TextView text = new TextView(this);
            text.setText(item);
            text.setTextSize(16);
            text.setTextColor(getColor(android.R.color.black));

            card.addView(text);
            medicationListContainer.addView(card);
        }
    }

    // Stubbed Medications
    private List<String> getStubbedMedications() {
        List<String> meds = new ArrayList<>();
        meds.add("Aspirin - 100mg - Once a day");
        meds.add("Ibuprofen - 200mg - Twice a day");
        return meds;
    }
}