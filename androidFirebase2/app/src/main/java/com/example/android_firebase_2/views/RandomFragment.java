package com.example.android_firebase_2.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_firebase_2.R;
import com.example.android_firebase_2.databinding.FragmentDashboardBinding;
import com.example.android_firebase_2.databinding.FragmentDetailBinding;
import com.example.android_firebase_2.databinding.FragmentRandomBinding;
import com.example.android_firebase_2.viewmodels.DashboardViewModel;
import com.example.android_firebase_2.viewmodels.RandomViewModel;

import java.util.ArrayList;


/**
 * FUNCIONA, pero no tiene accesibilidad
 */
/**
public class RandomFragment extends Fragment {
    /**
     * Declaro el ViewModel y el Binding que usaré para enlazar la vista con los datos del ViewModel
     */
/**    private RandomViewModel randomViewModel;
    private FragmentRandomBinding binding; // Binding para fragment_random.xml

    public RandomFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout de la vista usando Data Binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_random, container, false);
        return binding.getRoot();
    }

    /**
     * Se configuar la vista en onViewCreated
     */
/**    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("RANDOM_FRAGMENT", "RandomFragment se está creando...");

        /**
        // Configurar el botón de retroceso en la barra de herramientas
        binding.topAppBar.setNavigationOnClickListener(v -> {
            requireActivity().getOnBackPressedDispatcher().onBackPressed(); // Regresar al fragmento anterior
        });*/

        // Se inicializa el ViewModel y se enlaza con binding
 /**       randomViewModel = new ViewModelProvider(this).get(RandomViewModel.class);
        binding.setViewModel(randomViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        Log.d("RANDOM_FRAGMENT", "ViewModel inicializado.");

        /**
         * Observar el ilustrador aleatorio y actualizar la UI
         * (se observa el LiveData del RandomViewModel y se actualiza la UI con el ilustrador aleatorio):
         */
/**        randomViewModel.getRandomIllustratorLiveData().observe(getViewLifecycleOwner(), illustrator -> {
            if (illustrator != null) {
                Log.d("RANDOM_FRAGMENT", "Ilustrador aleatorio recibido: " + illustrator.getTitulo());
                binding.setIllustrator(illustrator); // Data Binding actualizará la vista automáticamente
            } else {
                Log.e("RANDOM_FRAGMENT", "No se pudo obtener un ilustrador aleatorio.");
                Toast.makeText(requireContext(), "No se encontró un ilustrador", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón para cargar un nuevo ilustrador aleatorio
        binding.buttonRandom.setOnClickListener(v -> {
            Log.d("RANDOM_FRAGMENT", "Botón presionado, cargando nuevo ilustrador...");
            randomViewModel.loadRandomIllustrator();
        });

        // Cargar el primer ilustrador al iniciar el Fragment
        randomViewModel.loadRandomIllustrator();
    }
}*/

/**
 * Añadida ACCESIBILIDAD con setContentDescription
 */

public class RandomFragment extends Fragment {
    private RandomViewModel randomViewModel;
    private FragmentRandomBinding binding;

    public RandomFragment() { }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_random, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("RANDOM_FRAGMENT", "RandomFragment se está creando...");

        // Se inicializa el ViewModel y se enlaza con binding
        randomViewModel = new ViewModelProvider(this).get(RandomViewModel.class);
        binding.setViewModel(randomViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        Log.d("RANDOM_FRAGMENT", "ViewModel inicializado.");

        // Observar el ilustrador aleatorio y actualizar la UI
        randomViewModel.getRandomIllustratorLiveData().observe(getViewLifecycleOwner(), illustrator -> {
            if (illustrator != null) {
                Log.d("RANDOM_FRAGMENT", "Ilustrador aleatorio recibido: " + illustrator.getTitulo());
                binding.setIllustrator(illustrator);

                // Agregar descripción accesible para la imagen y el título
                // son los items del fragment_random.xml:
                binding.illustratorImageDetail.setContentDescription("Ilustración de " + illustrator.getTitulo());
                binding.illustratorTitleDetail.setContentDescription("Título: " + illustrator.getTitulo());

            } else {
                Log.e("RANDOM_FRAGMENT", "No se pudo obtener un ilustrador aleatorio.");
                Toast.makeText(requireContext(), "No se encontró un ilustrador", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón para cargar un nuevo ilustrador aleatorio con accesibilidad
        binding.buttonRandom.setOnClickListener(v -> {
            Log.d("RANDOM_FRAGMENT", "Botón presionado, cargando nuevo ilustrador...");
            randomViewModel.loadRandomIllustrator();
        });
        binding.buttonRandom.setContentDescription("Botón para obtener un nuevo ilustrador aleatorio");

        // Cargar el primer ilustrador al iniciar el Fragment
        randomViewModel.loadRandomIllustrator();
    }
}
