package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.IllustratorRepository;


/**
 * Gestiona la lógica de negocio y expone LiveData
 */
public class RandomViewModel extends ViewModel {
    private MutableLiveData<Illustrator> illustratorLiveData;
    private IllustratorRepository repository;

    public RandomViewModel(String userId) {
        repository = new IllustratorRepository(userId);
        illustratorLiveData = new MutableLiveData<>();
        loadRandomIllustrator();
    }

    public void loadRandomIllustrator() {
        repository.getRandomIllustrator(illustratorLiveData);
    }

    public LiveData<Illustrator> getRandomIllustratorLiveData() {
        return illustratorLiveData;
    }
}



