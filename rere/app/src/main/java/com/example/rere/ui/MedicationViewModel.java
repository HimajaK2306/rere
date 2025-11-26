package com.example.rere.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.rere.data.Medication;
import com.example.rere.data.MedicationRepository;

import java.util.List;

public class MedicationViewModel extends AndroidViewModel {

    private final MedicationRepository repository;
    private final LiveData<List<Medication>> activeMedications;
    private final LiveData<Integer> activeMedicationCount;

    public MedicationViewModel(@NonNull Application application) {
        super(application);
        repository = new MedicationRepository(application);
        activeMedications = repository.getActiveMedications();
        activeMedicationCount = repository.getActiveMedicationCount();
    }

    public LiveData<List<Medication>> getActiveMedications() {
        return activeMedications;
    }

    public LiveData<Integer> getActiveMedicationCount() {
        return activeMedicationCount;
    }

    public void insert(Medication medication) {
        repository.insert(medication);
    }

    public void delete(Medication medication) {
        repository.delete(medication);
    }
}
