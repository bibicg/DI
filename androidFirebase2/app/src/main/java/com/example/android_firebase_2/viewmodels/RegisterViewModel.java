package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

/**
 * Implementar un RegisterViewModel que valide los datos del formulario
 * y gestione el registro de usuarios
 */
public class RegisterViewModel extends ViewModel {

    private UserRepository userRepository;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<String> validationMessage;

    public RegisterViewModel() {
        userRepository = new UserRepository();
        userLiveData = new MutableLiveData<>();
        validationMessage = new MutableLiveData<>();
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<String> getValidationMessage() {
        return validationMessage;
    }

    public void register(String name, String email, String password, String confirmPassword, String phone, String address) {
        if (!validateInputs(name, email, password, confirmPassword, phone, address)) {
            return; // la validación falla: no intentamos registrar al usuario
        }

        userRepository.registerUser(name, email, password, phone, address)
                .observeForever(firebaseUser -> {
                    userLiveData.setValue(firebaseUser); // actualiza el LiveData del usuario registrado
                });
    }

    private boolean validateInputs(String name, String email, String password, String confirmPassword, String phone, String address) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
                || phone.isEmpty() || address.isEmpty()) {
            validationMessage.setValue("Debes completar todos los campos.");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            validationMessage.setValue("Las contraseñas deben coincidir.");
            return false;
        }
        return true;
    }
}




