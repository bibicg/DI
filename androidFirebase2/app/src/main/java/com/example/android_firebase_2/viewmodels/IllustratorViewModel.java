package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.IllustratorRepository;
import com.google.firebase.auth.FirebaseAuth;
import java.util.List;

/**
 * Actúa como intermediario entre el repositorio y la vista.
 * Expone los datos observables de los productos mediante LiveData.
 */
//FAVORITOS
public class IllustratorViewModel extends ViewModel {
    private final MutableLiveData<List<Illustrator>> illustratorLiveData = new MutableLiveData<>();
    private final IllustratorRepository illustratorRepository;
    private MutableLiveData<Boolean> logoutLiveData = new MutableLiveData<>();

    public IllustratorViewModel() {
        illustratorRepository = new IllustratorRepository();
        loadProducts();
    }

    public LiveData<List<Illustrator>> getIllustratorLiveData() {
        return illustratorLiveData;
    }

    public LiveData<Boolean> getLogoutLiveData() {
        return logoutLiveData;
    }

    private void loadProducts() {
        illustratorRepository.getIllustrators(illustratorLiveData);
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        logoutLiveData.setValue(true);
    }
}


