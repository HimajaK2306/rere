package com.example.rere.data;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MedicationRepository {

    private final MedicationDao medicationDao;
    private final LiveData<List<Medication>> activeMedications;
    private final LiveData<Integer> activeMedicationCount;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public MedicationRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        medicationDao = db.medicationDao();
        activeMedications = medicationDao.getActiveMedications();
        activeMedicationCount = medicationDao.getActiveMedicationCount();
    }

    public LiveData<List<Medication>> getActiveMedications() {
        return activeMedications;
    }

    public LiveData<Integer> getActiveMedicationCount() {
        return activeMedicationCount;
    }

    public void insert(Medication medication) {
        executor.execute(() -> medicationDao.insert(medication));
    }

    public void delete(Medication medication) {
        executor.execute(() -> medicationDao.delete(medication));
    }
}
