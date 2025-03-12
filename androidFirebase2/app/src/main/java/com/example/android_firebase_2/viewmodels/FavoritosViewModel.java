package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

//!!!Debe usar UserRepository en lugar de FavoritosRepository

/**
 * He actualizado FavoritosViewModel para que:
 *
 * Use UserRepository en lugar de FavoritosRepository.
 * Exporte directamente LiveData<List<Illustrator>> en lugar de solo los IDs de favoritos.
 * Mantenga los métodos de agregar y eliminar favoritos, ahora correctamente vinculados al UserRepository.
 * Con este cambio, FavoritosViewModel ahora es más útil porque expone la lista completa de ilustradores favoritos,
 * facilitando su uso en la UI sin necesidad de hacer más consultas en la vista.
 */

//Ahora los VIEWMODEL no contactan directamente con Firebase (eso se hace desde los Repository).
//Ahora los VIEWMODEL solo exponen LiveData a las vistas.

public class FavoritosViewModel extends ViewModel {
    // Repositorio que maneja los favoritos del usuario en Firebase
    private final UserRepository userRepository;
    // LiveData que contiene la lista de ilustradores favoritos del usuario
    private LiveData<List<Illustrator>> favoritosLiveData;

    // Constructor que inicializa el repositorio y obtiene los favoritos del usuario
    public FavoritosViewModel(String userId) {
        userRepository = new UserRepository();
        // Ahora obtenemos los ilustradores favoritos en lugar de solo sus IDs
        favoritosLiveData = userRepository.getFavoriteIllustrators(userId);
    }

    // Método para agregar un ilustrador a la lista de favoritos del usuario
    public void agregarFavorito(String userId, String ilustradorId) {
        userRepository.addFavorito(userId, ilustradorId);
    }

    // Método para eliminar un ilustrador de la lista de favoritos del usuario
    public void eliminarFavorito(String userId, String ilustradorId) {
        userRepository.eliminarFavorito(userId, ilustradorId);
    }

    // Método que devuelve la lista de ilustradores favoritos como LiveData para que la vista pueda observar los cambios
    public LiveData<List<Illustrator>> obtenerFavoritos() {
        return favoritosLiveData;
    }


    // Método para eliminar todos los favoritos
    public void limpiarFavoritos(String userId) {
        userRepository.limpiarFavoritos(userId);

        // Forzar actualización de favoritos en UI
        MutableLiveData<List<Illustrator>> mutableLiveData = (MutableLiveData<List<Illustrator>>) favoritosLiveData;
        mutableLiveData.postValue(new ArrayList<>()); // Actualizar UI
    }





}
