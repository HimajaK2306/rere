package com.example.rere.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MedicationViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public MedicationViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MedicationViewModel.class)) {
            return (T) new MedicationViewModel(application);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
