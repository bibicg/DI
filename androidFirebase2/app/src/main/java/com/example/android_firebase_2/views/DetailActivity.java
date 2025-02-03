package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleView;
    private TextView descriptionView;
    private FloatingActionButton favouriteButton;
    private UserViewModel userViewModel;
    private String userId; // ID del usuario actual
    private String illustratorId; // ID del ilustrador actual
    private boolean isFavourite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.illustratorImageDetail);
        imageView.setContentDescription("Imagen de una obra representativa de la ilustradora/ilustrador.");
        titleView = findViewById(R.id.illustratorTitleDetail);
        titleView.setContentDescription("Nombre artístico de la ilustradora/ilustrador.");
        descriptionView = findViewById(R.id.illustratorDescriptionDetail);
        descriptionView.setContentDescription("Información de interés sobre la obra y/o carrera de la ilustradora/ilustrador.");
        favouriteButton = findViewById(R.id.favoritoFB);
        favouriteButton.setContentDescription("Marcar favorito / eliminar de favorito");

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String imagen = intent.getStringExtra("imagen");
        String descripcion = intent.getStringExtra("descripcion");
        illustratorId = intent.getStringExtra("id"); // ID del ilustrador

        titleView.setText(titulo);
        descriptionView.setText(descripcion);
        Picasso.get().load(imagen).into(imageView);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Obtener el UID del usuario autenticado
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            userId = currentUser.getUid();
        }

        // Verificar si el ilustrador ya está en favoritos
        userViewModel.getUserFavourites(userId).observe(this, favourites -> {
            isFavourite = favourites.contains(illustratorId);
            updateFavouriteButtonIcon();
        });


        favouriteButton.setOnClickListener(v -> {
            if (userId == null) { // el usuaruo no está autenticado
                Toast.makeText(this, "Usuario no autenticado.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (illustratorId == null) { // el ilustrador no está
                Toast.makeText(this, "Ilustrador no disponible.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Comrpuebo si ese ilustrador ya es favorito:
            if (isFavourite) {
                userViewModel.removeFavourite(userId, illustratorId);
                Toast.makeText(this, "Favorito eliminado.", Toast.LENGTH_SHORT).show();
            } else {
                userViewModel.addFavourite(userId, illustratorId);
                Toast.makeText(this, "Favorito añadido.", Toast.LENGTH_SHORT).show();
            }

            // Se cambia el estado al favorito y actualiza el icono del botón:
            isFavourite = !isFavourite;
            updateFavouriteButtonIcon();
        });

        Button volverBtn = findViewById(R.id.volverButton);
        volverBtn.setOnClickListener(v -> finish()); // vuelve a DashboardActivity
    }

    private void updateFavouriteButtonIcon() {
        if (isFavourite) {
            favouriteButton.setImageResource(R.drawable.corazon_favorito); // Icono de favorito añadido
        } else {
            favouriteButton.setImageResource(R.drawable.corazon_no_favorito); // Icono de favorito no añadido
        }
    }
}
