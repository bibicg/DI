package com.example.android_firebase_2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.FavoritosViewModel;
import com.google.firebase.auth.FirebaseAuth;

/**
 * FavoritosFragment está usando FavoritosViewModel.
 *
 * Ahora:
 * - Utilicé FavoritosViewModel en lugar de UserViewModel
 * - Se asegura de que la obtención de favoritos funciona correctamente con LiveData
 * - Mantiene el RV y el Adapter correctamente configurados
 */

/**
 * getParentFragmentManager() solo existe en AndroidX Fragment API 28+ y no siempre se reconoce en todos los contextos.
 * requireActivity().getSupportFragmentManager() es más estable y compatible con todas las versiones.
 */


public class FavoritosFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoritosAdapter adapter;
    private FavoritosViewModel favoritosViewModel;

    public FavoritosFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewFavs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Obtener el ID del usuario autenticado
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Configurar ViewModel para favoritos
        favoritosViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new FavoritosViewModel(userId);
            }
        }).get(FavoritosViewModel.class);

        // Observar la lista de ilustradores favoritos
        favoritosViewModel.obtenerFavoritos().observe(getViewLifecycleOwner(), illustrators -> {
            adapter = new FavoritosAdapter(illustrators, getParentFragmentManager());
            //adapter = new FavoritosAdapter(illustrators); //esto si no se pasar de fragment a fragment
            //adapter = new FavoritosAdapter(illustrators, requireActivity().getSupportFragmentManager());
            //adapter = new FavoritosAdapter(illustrators, getChildFragmentManager());
            recyclerView.setAdapter(adapter);
        });

        return view;
    }
}

