package com.example.android_firebase_2.viewmodels;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.IllustratorRepository;
import com.example.android_firebase_2.repositories.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

//!!!Antes manejaba firebase y favoritos
//!!!Ahora: FavoritosViewModel maneja los favoritos con UserRepository,
//y FavoritosActivity simplemente observa los datos.

//!!!Está aquí el tema del MODO OSCURO y el del CAMBIO DE CONTRASEÑA

/**
 * Se inicializa darkModeState con el valor almacenado en SharedPreferences.
 * - Se añade getDarkModeState() para exponer el estado del modo oscuro como LiveData.
 * - Se añade toggleDarkMode(boolean) para actualizar el modo oscuro y guardarlo en SharedPreferences.
 * - Se aplica el cambio de tema con AppCompatDelegate.setDefaultNightMode().
 */

/**Esto es con el MODO OSCURO y el CAMBIO DE CONTRASEÑA
public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private LiveData<List<Illustrator>> favoriteIllustratorsLiveData;
    //!!!para el MODO OSCURO:
    private MutableLiveData<Boolean> darkModeState = new MutableLiveData<>();
    private SharedPreferences sharedPreferences;

    /**Lo eliminamos al añadir lo del MODO OSCURO
    public UserViewModel() {
        userRepository = new UserRepository();
    }*/
/**
    //!!!!!!para el MODO OSCURO:
    public UserViewModel(Application application) {
        super();
        userRepository = new UserRepository(); // hay que inicializarlo ahora en este contructor
        sharedPreferences = application.getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        darkModeState.setValue(sharedPreferences.getBoolean("darkMode", false));
    }

    // Método para exponer los favoritos del usuario como LiveData
    public LiveData<List<String>> getUserFavourites(String userId) {
        return userRepository.getUserFavourites(userId);
    }

    // Método para agregar un ilustrador a favoritos usando el repositorio
    public void addFavourite(String userId, String illustratorId) {
        userRepository.addFavourite(userId, illustratorId);
    }

    // Método para eliminar un ilustrador de favoritos usando el repositorio
    public void removeFavourite(String userId, String illustratorId) {
        userRepository.removeFavourite(userId, illustratorId);
    }

    // Método para exponer los ilustradores favoritos del usuario
    public LiveData<List<Illustrator>> getFavoriteIllustratorsLiveData(String userId) {
        if (favoriteIllustratorsLiveData == null) {
            favoriteIllustratorsLiveData = userRepository.getFavoriteIllustrators(userId);
        }
        return favoriteIllustratorsLiveData;
    }

    //!!!Método para obtener el estado del modo oscuro como LiveData
    public LiveData<Boolean> getDarkModeState() {
        return darkModeState;
    }

    //!!!Método para cambiar el estado del modo oscuro
    public void toggleDarkMode(boolean enableDarkMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("darkMode", enableDarkMode);
        editor.apply();

        darkModeState.setValue(enableDarkMode);

        AppCompatDelegate.setDefaultNightMode(
                enableDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }

    //!!!Método para cambiar la contraseña del usuario
    public LiveData<Boolean> changePassword(String newPassword) {
        MutableLiveData<Boolean> passwordChangeResult = new MutableLiveData<>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            user.updatePassword(newPassword).addOnCompleteListener(task -> {
                passwordChangeResult.setValue(task.isSuccessful());
            });
        } else {
            passwordChangeResult.setValue(false);
        }
        return passwordChangeResult;
    }
}*/
//solo GESTIÓN DE FAVORITOS, quitamos MODO OSCURO y CAMBIO DE CONTRASEÑA
public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;
    private LiveData<List<Illustrator>> favoriteIllustratorsLiveData;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    // Métodos para gestionar favoritos
    public LiveData<List<String>> getUserFavourites(String userId) {
        return userRepository.getUserFavourites(userId);
    }

    public void addFavourite(String userId, String illustratorId) {
        userRepository.addFavourite(userId, illustratorId);
    }

    public void removeFavourite(String userId, String illustratorId) {
        userRepository.removeFavourite(userId, illustratorId);
    }

    public LiveData<List<Illustrator>> getFavoriteIllustratorsLiveData(String userId) {
        if (favoriteIllustratorsLiveData == null) {
            favoriteIllustratorsLiveData = userRepository.getFavoriteIllustrators(userId);
        }
        return favoriteIllustratorsLiveData;
    }
}




