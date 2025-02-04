package com.example.android_firebase_2.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.android_firebase_2.databinding.ActivityDashboardBinding;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.IllustratorViewModel;

import java.util.ArrayList;

/**
 * En esta sección se configura el RecyclerView para mostrar los productos cargados desde Firebase
 * Realtime Database. El RecyclerView está vinculado al adaptador ProductAdapter y observa los
 * datos expuestos por el ProductViewModel.
 */


public class DashboardActivity extends AppCompatActivity {
    private IllustratorViewModel illustratorViewModel;
    private IllustratorAdapter illustratorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Cargar el tema desde SharedPreferences
        SharedPreferences preferences = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        boolean isDarkMode = preferences.getBoolean("darkMode", false);

        // Establecer el tema antes de cargar el layout
        if (isDarkMode) {
            setTheme(R.style.Theme_Android_Firebase_2_Dark);
        } else {
            setTheme(R.style.Theme_Android_Firebase_2);
        }

        super.onCreate(savedInstanceState);
        // Enlace con el layout adecuado
        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        // Botón para cambiar el tema
        FloatingActionButton themeButton = findViewById(R.id.themeButton);
        themeButton.setOnClickListener(view -> {
            boolean isDarkModeEnabled = preferences.getBoolean("darkMode", false);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("darkMode", !isDarkModeEnabled);
            editor.apply();

            // Cambiar el tema
            setTheme(!isDarkModeEnabled ? R.style.Theme_Android_Firebase_2_Dark : R.style.Theme_Android_Firebase_2);
            recreate();
        });


        // Inicializar el adaptador para ilustradores
        illustratorAdapter = new IllustratorAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(illustratorAdapter);

        // Configurar el ViewModel
        illustratorViewModel = new ViewModelProvider(this).get(IllustratorViewModel.class);
        illustratorViewModel.getIllustratorLiveData().observe(this, illustrators -> {
            // Actualizar el adaptador cuando cambien los datos
            illustratorAdapter.setIllustrators(illustrators);
        });

        // botón de logout
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            // Llama a la función de logout en el ViewModel
            illustratorViewModel.logout();
        });

        // botón de ir a favoritos
        Button favButton = findViewById(R.id.favButton);
        favButton.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, FavoritosActivity.class);
            startActivity(intent);
        });

        // LiveData de logout
        illustratorViewModel.getLogoutLiveData().observe(this, isLoggedOut -> {
            if (isLoggedOut) {
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Evita volver al Dashboard
                startActivity(intent);
                finish(); // Cierra el DashboardActivity
            }
        });
    }
}

