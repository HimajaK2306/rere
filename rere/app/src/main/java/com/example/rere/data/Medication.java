package com.example.rere.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medications")
public class Medication {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public String dosage;       // e.g. "500 mg"
    public String frequency;    // e.g. "2 times per day"

    public boolean active;      // true = still taking

    public Medication(@NonNull String name,
                      String dosage,
                      String frequency,
                      boolean active) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.active = active;
    }
}
