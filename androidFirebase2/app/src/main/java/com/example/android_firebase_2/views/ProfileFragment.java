package com.example.android_firebase_2.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//con MODO OSCURO y CAMBIO DE CONTRASEÑA
public class ProfileFragment extends Fragment {
    private EditText newPasswordEditText;
    private Switch darkModeSwitch;
    private FirebaseAuth auth;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inicializar FirebaseAuth
        auth = FirebaseAuth.getInstance();

        // Referencias a los elementos del layout
        newPasswordEditText = view.findViewById(R.id.newPasswordEditText);
        darkModeSwitch = view.findViewById(R.id.darkModeSwitch);
        Button changePasswordButton = view.findViewById(R.id.changePasswordButton);

        // Inicializar SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("AppConfig", Context.MODE_PRIVATE);

        // Cargar preferencia de modo oscuro
        boolean isDarkModeEnabled = sharedPreferences.getBoolean("darkMode", false);
        darkModeSwitch.setChecked(isDarkModeEnabled);
        applyDarkMode(isDarkModeEnabled);

        // Listeners
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> toggleDarkMode(isChecked));
        changePasswordButton.setOnClickListener(v -> changePassword());

        return view;
    }

    private void changePassword() {
        String newPassword = newPasswordEditText.getText().toString().trim();

        if (newPassword.isEmpty() || newPassword.length() < 6) {
            Toast.makeText(getContext(), "La nueva contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            user.updatePassword(newPassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al cambiar la contraseña: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "Usuario no autenticado", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleDarkMode(boolean enableDarkMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("darkMode", enableDarkMode);
        editor.apply();

        applyDarkMode(enableDarkMode);
    }

    private void applyDarkMode(boolean enableDarkMode) {
        AppCompatDelegate.setDefaultNightMode(
                enableDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
        //requireActivity().recreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Establece el título para este fragmento
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Título del Fragmento");
    }

}


/** esto es sin MODO OSCURO Y SIN CAMBIO DE CONTRASEÑA
public class ProfileFragment extends Fragment {

    private EditText currentPasswordEditText, newPasswordEditText;
    private Switch darkModeSwitch;
    private UserViewModel userViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inicializar ViewModel
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        // Referencias a los elementos del layout
        currentPasswordEditText = view.findViewById(R.id.currentPasswordEditText);
        newPasswordEditText = view.findViewById(R.id.newPasswordEditText);
        darkModeSwitch = view.findViewById(R.id.darkModeSwitch);
        Button changePasswordButton = view.findViewById(R.id.changePasswordButton);

        // Cargar preferencia de modo oscuro desde ViewModel
        userViewModel.getDarkModeState().observe(getViewLifecycleOwner(), isDarkMode -> {
            darkModeSwitch.setChecked(isDarkMode);
        });

        // Listeners para cambios de configuración
        darkModeSwitch.setOnCheckedChangeListener((compoundButton, checked) -> userViewModel.toggleDarkMode(checked));
        changePasswordButton.setOnClickListener(v -> changePassword());

        return view;
    }

    private void changePassword() {
        String newPass = newPasswordEditText.getText().toString().trim();
        userViewModel.changePassword(newPass).observe(getViewLifecycleOwner(), success -> {
            if (success) {
                Toast.makeText(getContext(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show();
            }
        });
    }
}*/

