package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.DashboardRepository;

import java.util.List;

// Sigo intentando ajustarme maás a MVVM: ahora uso DashboardRepository
// en lugar de manejar lógica en IllustratorViewModel como antes

public class DashboardViewModel extends ViewModel {
    /**
    private final MutableLiveData<List<Illustrator>> illustratorLiveData = new MutableLiveData<>();
    private final DashboardRepository dashboardRepository;

    public DashboardViewModel() {
        dashboardRepository = new DashboardRepository();
        fetchIllustrators(); // Llamamos al método para cargar los datos
    }

    public LiveData<List<Illustrator>> getIllustratorLiveData() {
        return illustratorLiveData;
    }

    public void fetchIllustrators() {
        dashboardRepository.getIllustrators(illustratorLiveData);
    }*/
}

