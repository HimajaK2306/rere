package com.example.rere;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TherapyActivity extends AppCompatActivity {

    private EditText etTherapyType;
    private Button btnSaveTherapy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapy);

        // Set up custom toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Therapy Session");
        }

        etTherapyType = findViewById(R.id.etTherapyType);
        btnSaveTherapy = findViewById(R.id.btnSaveTherapy);

        btnSaveTherapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String therapyType = etTherapyType.getText().toString();
                if (therapyType.isEmpty()) {
                    Toast.makeText(TherapyActivity.this, "Please enter a therapy type", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TherapyActivity.this, "Therapy session saved!", Toast.LENGTH_SHORT).show();
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
