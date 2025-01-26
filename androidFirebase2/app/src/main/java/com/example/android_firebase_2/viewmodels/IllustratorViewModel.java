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
public class IllustratorViewModel extends ViewModel {
    //contiene la lista de mis ilustradores. Observa y actualiza la lista:
    private final MutableLiveData<List<Illustrator>> illustratorLiveData = new MutableLiveData<>();
    //instancia del repositorio de ilustradores:
    private final IllustratorRepository illustratorRepository;
    //controla el estado de cierre de sesión:
    private MutableLiveData<Boolean> logoutLiveData = new MutableLiveData<>();

    public IllustratorViewModel() { //inicializa el repositorio y llamada a la carga de los mismos:
        illustratorRepository = new IllustratorRepository();
        loadIllustrators();
    }

    //Devuelve el LiveData que contiene la lista de ilustradores:
    public LiveData<List<Illustrator>> getIllustratorLiveData() { //
        return illustratorLiveData;
    }

    //Devuelve el LiveData que contiene el cierre de sesión:
    public LiveData<Boolean> getLogoutLiveData() {
        return logoutLiveData;
    }

    //Llama a getIllustrators() del repositorio para cargar los ilustradores en illustratorLiveData:
    private void loadIllustrators() {
        illustratorRepository.getIllustrators(illustratorLiveData);
    }

    //Cierre de sesión:
    public void logout() {
        FirebaseAuth.getInstance().signOut();
        logoutLiveData.setValue(true);
    }
}