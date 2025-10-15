package com.example.rere;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class RemoveMedicationActivity extends AppCompatActivity {

    private EditText etMedicationNameToRemove;
    private Button btnConfirmRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_medication);

        // Set up custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Remove Medication");
        }

        etMedicationNameToRemove = findViewById(R.id.etMedicationNameToRemove);
        btnConfirmRemove = findViewById(R.id.btnConfirmRemove);

        btnConfirmRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medName = etMedicationNameToRemove.getText().toString();
                if (medName.isEmpty()) {
                    Toast.makeText(RemoveMedicationActivity.this, "Please enter a medication name", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RemoveMedicationActivity.this, medName + " removed!", Toast.LENGTH_SHORT).show();
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
