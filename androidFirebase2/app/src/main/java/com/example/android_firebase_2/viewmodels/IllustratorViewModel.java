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
    // LiveData que contiene la lista de ilustradores obtenida desde Firebase
    private final MutableLiveData<List<Illustrator>> illustratorLiveData = new MutableLiveData<>();
    private final IllustratorRepository illustratorRepository;

    // LiveData para gestionar el estado de cierre de sesión
    private MutableLiveData<Boolean> logoutLiveData = new MutableLiveData<>();

    public IllustratorViewModel() {
        // Inicialización del repositorio de ilustradores
        illustratorRepository = new IllustratorRepository();
        // Cargar los datos de los ilustradores al inicializar el ViewModel
        loadProducts();
    }

    // Método para exponer los datos de los ilustradores como LiveData
    public LiveData<List<Illustrator>> getIllustratorLiveData() {
        return illustratorLiveData;
    }

    // Método para exponer el estado de cierre de sesión como LiveData
    public LiveData<Boolean> getLogoutLiveData() {
        return logoutLiveData;
    }

    // Método para cargar la lista de ilustradores desde el repositorio
    private void loadProducts() {
        // Se le pasa el LiveData a la capa de repositorio para obtener datos de Firebase
        illustratorRepository.getIllustrators(illustratorLiveData);
    }

    // Método para cerrar sesión del usuario en Firebase Authentication
    public void logout() {
        FirebaseAuth.getInstance().signOut(); // Cierra la sesión actual
        logoutLiveData.setValue(true); // Notifica a la vista que el usuario ha cerrado sesión
    }
}



