package com.example.rere;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RemoveMedicationActivity extends AppCompatActivity {

    private EditText editTextRemoveName;
    private Button buttonConfirmRemoval, buttonBackHome;
    private LinearLayout medicationListContainer;
    private final List<String> medicationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_medication);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        editTextRemoveName = findViewById(R.id.editTextRemoveName);
        buttonConfirmRemoval = findViewById(R.id.buttonConfirmRemoval);
        buttonBackHome = findViewById(R.id.buttonBackHome);
        medicationListContainer = findViewById(R.id.medicationListContainer);

        buttonConfirmRemoval.setOnClickListener(v -> removeByName());
        buttonBackHome.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        medicationList.clear();
        medicationList.addAll(MedicationStorage.getMedications(this));
        refreshMedicationList();
    }

    private void removeByName() {
        String name = editTextRemoveName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter a medication name", Toast.LENGTH_SHORT).show();
            return;
        }

        String lower = name.toLowerCase();
        String target = null;

        for (String m : medicationList) {
            if (m.toLowerCase().startsWith(lower)) {
                target = m;
                break;
            }
        }

        if (target == null) {
            Toast.makeText(this, "Medication not found", Toast.LENGTH_SHORT).show();
            return;
        }

        medicationList.remove(target);
        MedicationStorage.saveMedications(this, medicationList);
        refreshMedicationList();

        editTextRemoveName.setText("");
        Toast.makeText(this, "Medication removed", Toast.LENGTH_SHORT).show();
    }

    private void refreshMedicationList() {
        medicationListContainer.removeAllViews();

        if (medicationList.isEmpty()) {
            TextView empty = new TextView(this);
            empty.setText("No medications saved.");
            empty.setTextColor(0xFF88A79A);
            empty.setPadding(8, 8, 8, 8);
            medicationListContainer.addView(empty);
            return;
        }

        for (String med : medicationList) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(8, 8, 8, 8);

            TextView tv = new TextView(this);
            LinearLayout.LayoutParams tvParams =
                    new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            tv.setLayoutParams(tvParams);
            tv.setText(med);
            tv.setTextColor(0xFF0F3D2E);

            Button btnRemove = new Button(this);
            LinearLayout.LayoutParams btnParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
            btnRemove.setLayoutParams(btnParams);
            btnRemove.setText("Remove");
            btnRemove.setAllCaps(false);
            btnRemove.setTextColor(0xFFFFFFFF);
            btnRemove.setBackgroundColor(Color.parseColor("#FF6B6B"));

            String entry = med;
            btnRemove.setOnClickListener(v -> {
                medicationList.remove(entry);
                MedicationStorage.saveMedications(this, medicationList);
                refreshMedicationList();
                Toast.makeText(this, "Medication removed", Toast.LENGTH_SHORT).show();
            });

            row.addView(tv);
            row.addView(btnRemove);
            medicationListContainer.addView(row);
        }
    }
}
