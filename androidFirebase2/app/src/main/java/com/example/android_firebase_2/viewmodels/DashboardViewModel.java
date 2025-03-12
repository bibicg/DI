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
 * ya que debe pasarle a la vista una "mezcla" entre ambos. Pero esta mezcla se hará en IllustratorRepository
 * para cumplir de forma correcta el patrón MVVM.
 *
 * DashboardViewModel actúa como un puente entre la interfaz de usuario (Fragment) y el modelo de datos (Repositorio).
 * Sus responsabilidades principales son:
 *
 * - Declara un LiveData para almacenar la lista de ilustradores que NO están en favoritos.
 * - Inicializa IllustratorRepository en el constructor con el userId (para poder filtrar los ilustradores de cada usuario, ya que ha dejado de
 * ser una pantalla común para todos).
 * - Carga la lista de ilustradores que no son favoritos llamando a loadIllustratorsNoFav(), que delega la lógica al repositorio.
 */


public class DashboardViewModel extends ViewModel {
    //Se encarga de obtener los datos desde Firebase:
    private final IllustratorRepository illustratorRepository;
    //Es el LiveData que notificará a la interfaz cuando los datos cambien:
    private final MutableLiveData<List<Illustrator>> illustratorLiveData = new MutableLiveData<>();

    /**
     * Recibe el userId como parámetro y lo usa para inicializar el repositorio.
     * Llama a loadIllustratorsNoFav() inmediatamente después de inicializar el repositorio, asegurando
     * que los datos se carguen al crear el viewModel.
     * @param userId
     */
    public DashboardViewModel(String userId) {
        illustratorRepository = new IllustratorRepository(userId); //ahora el illustrator repository pasa el id del usuario
        loadIllustratorsNoFav();
    }

    /**
     * Expone illustratorLiveData a la vista (Fragment o Activity) para que pueda observar los cambios de datos.
     * @return
     */
    public LiveData<List<Illustrator>> getIllustratorLiveData() {

        return illustratorLiveData;
    }

    /**
     * No contiene lógica de negocio, sino que delega la obtención y filtrado de los datos a IllustratorRepository.
     */
    private void loadIllustratorsNoFav() {
        illustratorRepository.getIllustratorsNoFav(illustratorLiveData);
    }
}
