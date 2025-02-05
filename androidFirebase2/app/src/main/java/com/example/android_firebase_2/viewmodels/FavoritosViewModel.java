package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.repositories.FavoritosRepository;
import java.util.List;


public class FavoritosViewModel extends ViewModel {
    private FavoritosRepository favoritosRepository;
    private LiveData<List<String>> favoritosLiveData;

    public FavoritosViewModel(String userId) {
        favoritosRepository = new FavoritosRepository(userId);
        favoritosLiveData = favoritosRepository.obtenerFavoritos(); // Obtiene el LiveData directamente
    }

    public void agregarFavorito(String ilustradorId) {
        favoritosRepository.agregarFavorito(ilustradorId);
    }

    public void eliminarFavorito(String ilustradorId) {
        favoritosRepository.eliminarFavorito(ilustradorId);
    }

    public LiveData<List<String>> obtenerFavoritos() {
        return favoritosLiveData; // Devuelve el LiveData directamente
    }
}