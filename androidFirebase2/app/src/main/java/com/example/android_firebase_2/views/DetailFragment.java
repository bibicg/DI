package com.example.android_firebase_2.views;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

/**
public class DetailFragment extends Fragment {
    private ImageView imageView;
    private TextView titleView;
    private TextView descriptionView;
    private FloatingActionButton favouriteButton;
    private UserViewModel userViewModel;
    private String userId;
    private String illustratorId;
    private boolean isFavourite = false;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Cargar el tema desde SharedPreferences
        SharedPreferences preferences = requireActivity().getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        boolean isDarkMode = preferences.getBoolean("darkMode", false);

        if (isDarkMode) {
            requireActivity().setTheme(R.style.Theme_Android_Firebase_2_Dark);
        } else {
            requireActivity().setTheme(R.style.Theme_Android_Firebase_2);
        }

        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        // Obtener referencias de UI
        imageView = view.findViewById(R.id.illustratorImageDetail);
        titleView = view.findViewById(R.id.illustratorTitleDetail);
        descriptionView = view.findViewById(R.id.illustratorDescriptionDetail);
        favouriteButton = view.findViewById(R.id.favoritoFB);

        // Obtener argumentos pasados desde el RecyclerView
        if (getArguments() != null) {
            String titulo = getArguments().getString("titulo");
            String imagen = getArguments().getString("imagen");
            String descripcion = getArguments().getString("descripcion");
            illustratorId = getArguments().getString("id");

            titleView.setText(titulo);
            descriptionView.setText(descripcion);
            Picasso.get().load(imagen).into(imageView);
        }

        // Configurar ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Obtener el UID del usuario autenticado
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
        }

        // Verificar si el ilustrador ya está en favoritos
        userViewModel.getUserFavourites(userId).observe(getViewLifecycleOwner(), favourites -> {
            isFavourite = favourites.contains(illustratorId);
            updateFavouriteButtonIcon();
        });

        // Acción del botón de favoritos
        favouriteButton.setOnClickListener(v -> {
            if (userId == null) {
                Toast.makeText(getContext(), "Usuario no autenticado.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (illustratorId == null) {
                Toast.makeText(getContext(), "Ilustrador no disponible.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isFavourite) {
                userViewModel.removeFavourite(userId, illustratorId);
                Toast.makeText(getContext(), "Favorito eliminado.", Toast.LENGTH_SHORT).show();
            } else {
                userViewModel.addFavourite(userId, illustratorId);
                Toast.makeText(getContext(), "Favorito añadido.", Toast.LENGTH_SHORT).show();
            }

            isFavourite = !isFavourite;
            updateFavouriteButtonIcon();
        });

        return view;
    }

    private void updateFavouriteButtonIcon() {
        if (isFavourite) {
            favouriteButton.setImageResource(R.drawable.corazon_favorito);
        } else {
            favouriteButton.setImageResource(R.drawable.corazon_no_favorito);
        }
    }
}*/
//migramos de activity a fragment:
public class DetailFragment extends Fragment {
    private ImageView imageView;
    private TextView titleView;
    private TextView descriptionView;
    private FloatingActionButton favouriteButton;
    private UserViewModel userViewModel;
    private String userId;
    private String illustratorId;
    private boolean isFavourite = false;

    public DetailFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        imageView = view.findViewById(R.id.illustratorImageDetail);
        titleView = view.findViewById(R.id.illustratorTitleDetail);
        descriptionView = view.findViewById(R.id.illustratorDescriptionDetail);
        favouriteButton = view.findViewById(R.id.favoritoFB);

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            userId = user.getUid();
        }

        if (getArguments() != null) {
            illustratorId = getArguments().getString("id");
            String titulo = getArguments().getString("titulo");
            String imagen = getArguments().getString("imagen");
            String descripcion = getArguments().getString("descripcion");

            titleView.setText(titulo);
            descriptionView.setText(descripcion);
            Picasso.get().load(imagen).into(imageView);

            // Verificar si el ilustrador ya está en favoritos
            userViewModel.getUserFavourites(userId).observe(getViewLifecycleOwner(), favourites -> {
                isFavourite = favourites.contains(illustratorId);
                updateFavouriteButtonIcon();
            });
        }

        // Acción del botón de favoritos
        favouriteButton.setOnClickListener(v -> {
            if (userId == null) {
                Toast.makeText(getContext(), "Usuario no autenticado.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (illustratorId == null) {
                Toast.makeText(getContext(), "Ilustrador no disponible.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isFavourite) {
                userViewModel.removeFavourite(userId, illustratorId);
                Toast.makeText(getContext(), "Favorito eliminado.", Toast.LENGTH_SHORT).show();
            } else {
                userViewModel.addFavourite(userId, illustratorId);
                Toast.makeText(getContext(), "Favorito añadido.", Toast.LENGTH_SHORT).show();
            }

            isFavourite = !isFavourite;
            updateFavouriteButtonIcon();
        });

        return view;
    }

    private void updateFavouriteButtonIcon() {
        if (isFavourite) {
            favouriteButton.setImageResource(R.drawable.corazon_favorito);
        } else {
            favouriteButton.setImageResource(R.drawable.corazon_no_favorito);
        }
    }
}
