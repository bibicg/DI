package com.example.android_firebase_2.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.databinding.FragmentDashboardBinding;
import com.example.android_firebase_2.viewmodels.IllustratorViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import androidx.navigation.Navigation;

/**
public class DashboardFragment extends Fragment {
    private IllustratorViewModel illustratorViewModel;
    private IllustratorAdapter illustratorAdapter;

    // Constructor vacío
    public DashboardFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el layout del fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Cargar el tema desde SharedPreferences
        SharedPreferences preferences = requireActivity().getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        boolean isDarkMode = preferences.getBoolean("darkMode", false);

        // Establecer el tema antes de cargar el layout
        if (isDarkMode) {
            requireActivity().setTheme(R.style.Theme_Android_Firebase_2_Dark);
        } else {
            requireActivity().setTheme(R.style.Theme_Android_Firebase_2);
        }

        // Enlace con el layout adecuado
        FragmentDashboardBinding binding = FragmentDashboardBinding.bind(view);

        // Botón para cambiar el tema
        FloatingActionButton themeButton = view.findViewById(R.id.themeButton);
        themeButton.setContentDescription("Botón para cambiar el modo de visualización: modo claro o modo oscuro");
        themeButton.setOnClickListener(v -> {
            boolean isDarkModeEnabled = preferences.getBoolean("darkMode", false);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("darkMode", !isDarkModeEnabled);
            editor.apply();

            // Cambiar el tema
            requireActivity().setTheme(!isDarkModeEnabled ? R.style.Theme_Android_Firebase_2_Dark : R.style.Theme_Android_Firebase_2);
            requireActivity().recreate();
        });

        // Inicializar el adaptador para ilustradores
        illustratorAdapter = new IllustratorAdapter(new ArrayList<>());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(illustratorAdapter);

        // Configurar el ViewModel
        illustratorViewModel = new ViewModelProvider(requireActivity()).get(IllustratorViewModel.class);
        illustratorViewModel.getIllustratorLiveData().observe(getViewLifecycleOwner(), illustrators -> {
            // Actualizar el adaptador cuando cambien los datos
            illustratorAdapter.setIllustrators(illustrators);
        });

        // botón de logout
        Button logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setContentDescription("Botón para desloguearte de la aplicación");
        logoutButton.setOnClickListener(v -> {
            // Llama a la función de logout en el ViewModel
            illustratorViewModel.logout();
        });

        // botón de ir a favoritos NO FUNCIONA
        /**
         Button favButton = view.findViewById(R.id.favButton);
         favButton.setContentDescription("Botón para ir a favoritos");
         favButton.setOnClickListener(v -> {
         // Navegación a FavoritosFragment (asegúrate de tener la navegación configurada)
         Navigation.findNavController(view).navigate(R.id.action_dashboardFragment_to_favoritosFragment);
         });*/

/**
        // LiveData de logout
        illustratorViewModel.getLogoutLiveData().observe(getViewLifecycleOwner(), isLoggedOut -> {
            if (isLoggedOut) {
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Evita volver al Dashboard
                startActivity(intent);
                requireActivity().finish(); // Cierra el DashboardActivity
            }
        });

        return view;
    }
}*/
// migramos de activity a fragment:
public class DashboardFragment extends Fragment {
    private IllustratorViewModel illustratorViewModel;
    private IllustratorAdapter illustratorAdapter;

    public DashboardFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        FragmentDashboardBinding binding = FragmentDashboardBinding.bind(view);

        illustratorAdapter = new IllustratorAdapter(new ArrayList<>(), getParentFragmentManager());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(illustratorAdapter);

        illustratorViewModel = new ViewModelProvider(requireActivity()).get(IllustratorViewModel.class);
        illustratorViewModel.getIllustratorLiveData().observe(getViewLifecycleOwner(), illustrators -> {
            illustratorAdapter.setIllustrators(illustrators);
        });

        return view;
    }
}

