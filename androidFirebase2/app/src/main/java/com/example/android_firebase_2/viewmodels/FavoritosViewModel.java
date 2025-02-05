package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.IllustratorRepository;
import java.util.List;
/**
public class FavoritosViewModel extends ViewModel {

    private MutableLiveData<List<Illustrator>> favoritosLiveData;
    private IllustratorRepository illustratorRepository;

    public FavoritosViewModel() {
        illustratorRepository = IllustratorRepository.getInstance();
        favoritosLiveData = new MutableLiveData<>();
        loadFavoritos(); // Cargar la lista de favoritos al iniciar
    }

    // Método para obtener los ilustradores favoritos como LiveData
    public LiveData<List<Illustrator>> getFavoritos() {
        return favoritosLiveData;
    }

    // Método para cargar los ilustradores favoritos desde el repositorio
    private void loadFavoritos() {
        // Supongamos que tienes un método en el repositorio que obtiene la lista de favoritos
        List<Illustrator> favoritos = illustratorRepository.getFavoritos();
        favoritosLiveData.setValue(favoritos);
    }

    // Método para agregar un ilustrador a favoritos
    public void addFavorito(Illustrator illustrator) {
        illustratorRepository.addFavorito(illustrator);
        loadFavoritos(); // Actualizar la lista de favoritos después de agregar
    }

    // Método para eliminar un ilustrador de favoritos
    public void removeFavorito(Illustrator illustrator) {
        illustratorRepository.removeFavorito(illustrator);
        loadFavoritos(); // Actualizar la lista de favoritos después de eliminar
    }
}*/
