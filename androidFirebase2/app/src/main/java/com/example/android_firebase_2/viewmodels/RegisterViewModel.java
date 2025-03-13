package com.example.android_firebase_2.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends ViewModel {
    // Repositorio encargado de manejar la autenticación y almacenamiento de usuarios en Firebase
    private UserRepository userRepository;

    // LiveData que contiene el usuario autenticado después del registro
    private MutableLiveData<FirebaseUser> userLiveData;

    // Constructor que inicializa el repositorio y la instancia de LiveData
    public RegisterViewModel() {
        userRepository = new UserRepository();
        userLiveData = new MutableLiveData<>();
    }

    // Método que devuelve el LiveData con la información del usuario registrado
    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    /**
    // Método que registra un nuevo usuario en Firebase Authentication y lo almacena en la base de datos
    public void register(String name, String email, String password, String phone, String address) {
        // Se asigna el resultado del registro al LiveData para ser observado por la vista
        userLiveData = userRepository.registerUser(name, email, password, phone, address);
    }*/

    //hay qu añadir context ya que recueramos el id del usuaruo de las shared preferences:
    //public void register(Context context, String name, String email, String password, String phone, String address) {
    //userLiveData = userRepository.registerUser(name, email, password, phone, address, context);
    //}
    public void register(Context context, String name, String email, String password, String phone, String address) {
        Log.d("REGISTER_VIEWMODEL", "Registro iniciado para: " + email);

        userRepository.registerUser(name, email, password, phone, address, context)
                //tuve que añadir el observeForever para que liveData se actualizara conrrectamente
                //ahora se asegura de recibir la actualización de UserRepository y propagarla a RegisterActivity
                .observeForever(user -> {
                    if (user != null) {
                        Log.d("REGISTER_VIEWMODEL", "Usuario recibido en ViewModel: " + user.getUid());
                    } else {
                        Log.e("REGISTER_VIEWMODEL_ERROR", "Error en el registro en ViewModel");
                    }
                    userLiveData.postValue(user);
                });
    }
}


