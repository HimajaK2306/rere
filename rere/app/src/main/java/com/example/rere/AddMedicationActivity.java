package com.example.rere;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddMedicationActivity extends AppCompatActivity {

    private EditText etMedicationName, etDosage, etFrequency;
    private Button btnSaveMedication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        // Set up custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Add Medication");
        }

        // Initialize views
        etMedicationName = findViewById(R.id.etMedicationName);
        etDosage = findViewById(R.id.etDosage);
        etFrequency = findViewById(R.id.etFrequency);
        btnSaveMedication = findViewById(R.id.btnSaveMedication);

        btnSaveMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medName = etMedicationName.getText().toString();
                if (medName.isEmpty()) {
                    Toast.makeText(AddMedicationActivity.this, "Medication name cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddMedicationActivity.this, medName + " saved!", Toast.LENGTH_SHORT).show();
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
