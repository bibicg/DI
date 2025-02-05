package com.example.android_firebase_2.viewmodels;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.repositories.IllustratorRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

// CON LOS FAVORIOS DESDE AQUI,
public class UserViewModel extends ViewModel {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private IllustratorRepository illustratorRepository = new IllustratorRepository();
    private MutableLiveData<List<Illustrator>> favoriteIllustratorsLiveData = new MutableLiveData<>();

    // Obtener los favoritos del usuario
    public LiveData<List<String>> getUserFavourites(String userId) {
        MutableLiveData<List<String>> favouritesLiveData = new MutableLiveData<>();
        databaseReference.child("users").child(userId).child("favoritos")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> favouritesList = new ArrayList<>();
                        for (DataSnapshot favSnapshot : snapshot.getChildren()) {
                            favouritesList.add(favSnapshot.getKey()); // Añadir el ID del ilustrador
                        }
                        favouritesLiveData.setValue(favouritesList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Manejar error
                    }
                });
        return favouritesLiveData;
    }

    // Añadir ilustrador a favoritos
    public void addFavourite(String userId, String illustratorId) {
        databaseReference.child("users").child(userId).child("favoritos").child(illustratorId).setValue(true)
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e("Firebase", "Error al añadir favorito", task.getException());
                    }
                });
    }

    // Eliminar ilustrador de favoritos
    public void removeFavourite(String userId, String illustratorId) {
        databaseReference.child("users").child(userId).child("favoritos").child(illustratorId).removeValue()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e("Firebase", "Error al eliminar favorito", task.getException());
                    }
                });
    }

    // Obtener los ilustradores favoritos del usuario
    public void fetchFavoriteIllustrators(String userId) {
        getUserFavourites(userId).observeForever(favouriteIds -> {
            databaseReference.child("ilustradores").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Illustrator> favoriteIllustrators = new ArrayList<>();
                    for (DataSnapshot illustratorSnapshot : snapshot.getChildren()) {
                        Illustrator illustrator = illustratorSnapshot.getValue(Illustrator.class);
                        if (favouriteIds.contains(illustrator.getId())) {
                            favoriteIllustrators.add(illustrator);
                        }
                    }
                    favoriteIllustratorsLiveData.setValue(favoriteIllustrators);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Manejar error
                }
            });
        });
    }

    public LiveData<List<Illustrator>> getFavoriteIllustratorsLiveData() {
        return favoriteIllustratorsLiveData;
    }
}

/**
// Sin la parte de favoritos
public class UserViewModel extends ViewModel {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private IllustratorRepository illustratorRepository = new IllustratorRepository();

    // Obtener los ilustradores favoritos del usuario
    public LiveData<List<Illustrator>> fetchFavoriteIllustrators(String userId, FavoritosViewModel favoritosViewModel) {
        MutableLiveData<List<Illustrator>> favoriteIllustratorsLiveData = new MutableLiveData<>();

        favoritosViewModel.obtenerFavoritos().observeForever(favouriteIds -> {
            if (favouriteIds == null) {
                favoriteIllustratorsLiveData.setValue(new ArrayList<>());
                return;
            }
            if (favouriteIds.isEmpty()){
                favoriteIllustratorsLiveData.setValue(new ArrayList<>()); //Si no hay favoritos, devuelve lista vacia
                return;
            }

            databaseReference.child("ilustradores").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Illustrator> favoriteIllustrators = new ArrayList<>();
                    for (DataSnapshot illustratorSnapshot : snapshot.getChildren()) {
                        Illustrator illustrator = illustratorSnapshot.getValue(Illustrator.class);
                        if (illustrator != null && favouriteIds.contains(illustrator.getId())) { // Verifica que illustrator no sea nulo
                            favoriteIllustrators.add(illustrator);
                        }
                    }
                    favoriteIllustratorsLiveData.setValue(favoriteIllustrators);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Manejar error
                    favoriteIllustratorsLiveData.setValue(new ArrayList<>()); // Importante manejar el error
                    System.err.println("Error al obtener ilustradores: " + error.getMessage());

                }
            });
        });
        return favoriteIllustratorsLiveData;
    }

}*/

