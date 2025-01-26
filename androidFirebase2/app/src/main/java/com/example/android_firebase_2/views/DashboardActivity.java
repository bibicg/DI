package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

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
        super.onCreate(savedInstanceState);
        // Enlace con el layout adecuado:
        ActivityDashboardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);

        // Inicializa el adaptador para ilustradores:
        illustratorAdapter = new IllustratorAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(illustratorAdapter);

        // Configura el ViewModel:
        illustratorViewModel = new ViewModelProvider(this).get(IllustratorViewModel.class);
        illustratorViewModel.getIllustratorLiveData().observe(this, illustrators -> {
            // Actualizar el adaptador cuando cambien los datos:
            illustratorAdapter.setIllustrators(illustrators);
        });

        // Configura el botón de logout:
        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(v -> {
            // Llamar al logout en el ViewModel:
            illustratorViewModel.logout();
        });

        // Observa el LiveData de logout:
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


