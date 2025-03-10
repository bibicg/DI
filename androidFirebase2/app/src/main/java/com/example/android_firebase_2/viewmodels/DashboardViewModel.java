package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.IllustratorRepository;

import java.util.List;

// Nuevo DashboardViewModel para manejar la lógica de DashboardActivity
//!!!Se encarga de la carga de ilustradores (antes lo hacia directamente la activity)
public class DashboardViewModel extends ViewModel {
    private final IllustratorRepository illustratorRepository;
    private final MutableLiveData<List<Illustrator>> illustratorLiveData;

    public DashboardViewModel() {
        illustratorRepository = new IllustratorRepository();
        illustratorLiveData = new MutableLiveData<>();
        loadIllustrators();
    }

    public LiveData<List<Illustrator>> getIllustratorLiveData() {
        return illustratorLiveData;
    }

    private void loadIllustrators() {
        illustratorRepository.getIllustrators(illustratorLiveData);
    }
}
