package com.example.android_firebase_2.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

/**
// Obteniendo los favoritos de UserViewModel
public class FavoritosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritosAdapter adapter;
    private UserViewModel userViewModel;
    private Button volver;

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
        setContentView(R.layout.activity_favoritos);

        recyclerView = findViewById(R.id.recyclerViewFavs);
        recyclerView.setContentDescription("Muestra el listado de ilustradores. Pincha sobre uno y accederás a una página con más información sobre el mismo");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userViewModel.fetchFavoriteIllustrators(userId);
        userViewModel.getFavoriteIllustratorsLiveData().observe(this, illustrators -> {
            adapter = new FavoritosAdapter(illustrators);
            recyclerView.setAdapter(adapter);
        });

        volver = findViewById(R.id.volverButton);
        volver.setContentDescription("Botón para volver a la página de Dashboard");
        volver.setOnClickListener(v -> {
            Intent intent = new Intent(FavoritosActivity.this, DashboardActivity.class);
            startActivity(intent);
        });
    }
}

/**
// Obteniendo los favoritos de FavoritosViewModel

public class FavoritosActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private FavoritosViewModel favoritosViewModel;
    private RecyclerView recyclerView;
    private FavoritosAdapter adapter;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        recyclerView = findViewById(R.id.recyclerViewFavs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        favoritosViewModel = new ViewModelProvider(this).get(FavoritosViewModel.class); // Inicializa FavoritosViewModel

        // Obtener el ID del usuario
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                // Llamar al método fetchFavoriteIllustrators con ambos argumentos
                userViewModel.fetchFavoriteIllustrators(userId, favoritosViewModel).observe(this, illustrators -> {
                    adapter = new FavoritosAdapter(illustrators);
                    recyclerView.setAdapter(adapter);
                });
    }
}*/






