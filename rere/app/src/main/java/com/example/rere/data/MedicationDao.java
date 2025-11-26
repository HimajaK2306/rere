package com.example.rere.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicationDao {

    @Insert
    long insert(Medication medication);

    @Delete
    void delete(Medication medication);

    @Query("SELECT * FROM medications WHERE active = 1 ORDER BY id DESC")
    LiveData<List<Medication>> getActiveMedications();

    @Query("SELECT COUNT(*) FROM medications WHERE active = 1")
    LiveData<Integer> getActiveMedicationCount();

    @Query("SELECT * FROM medications WHERE active = 1 ORDER BY id DESC")
    List<Medication> getActiveMedicationsOnce();
}
