package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.IllustratorRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Ahora DASHBOARDVIEWMODEL debe obtener datos tanto del repositorio de ilustradores como de los favoritos,
 * ya que debe pasarle a la vista una "mezcla" entre ambos
 *
 * DashboardViewModel se encarga de manejar la lógica de negocio y preparar los datos para el DashboardFragment.
 * Actúa como un puente entre la interfaz de usuario (Fragment) y el modelo de datos (Repositorio).
 * Sus responsabilidades principales son:
 *
 * - Inicialización del Repositorio y ViewModel de Favoritos.
 * - Provisión de datos.
 * - Carga de ilustradores.
 */


public class DashboardViewModel extends ViewModel {
    private final IllustratorRepository illustratorRepository;
    private final FavoritosViewModel favoritosViewModel;
    private final MutableLiveData<List<Illustrator>> illustratorLiveData = new MutableLiveData<>();


    public DashboardViewModel(String userId) {
        illustratorRepository = new IllustratorRepository();
        favoritosViewModel = new FavoritosViewModel(userId);
        loadIllustrators(userId);
    }

    public LiveData<List<Illustrator>> getIllustratorLiveData() {
        return illustratorLiveData;
    }

    /**
     * Este método se encarga de cargar ilustradores. En una primera vuelta, carga todos, después va
     * comparando mediante el id cuales están en favoritos y cuales no, filtrando aquellos que no son favoritos,
     * que serán los que le pase a la vista (DashboardFragment) para que los muestre
     * @param userId
     */
    private void loadIllustrators(String userId) {
        illustratorRepository.getIllustrators().observeForever(allIllustrators -> {
            favoritosViewModel.obtenerFavoritos().observeForever(favoritos -> {
                List<Illustrator> filteredIllustrators = new ArrayList<>();

                // Convertimos la lista de objetos Illustrator a una lista de id
                List<String> favoritosIds = new ArrayList<>();
                for (Illustrator favorito : favoritos) {
                    favoritosIds.add(favorito.getId());
                }

                // Filtramos los ilustradores que no están en favoritos
                for (Illustrator illustrator : allIllustrators) {
                    if (!favoritosIds.contains(illustrator.getId())) {
                        filteredIllustrators.add(illustrator);
                    }
                }

                illustratorLiveData.setValue(filteredIllustrators);
            });
        });
    }


}
