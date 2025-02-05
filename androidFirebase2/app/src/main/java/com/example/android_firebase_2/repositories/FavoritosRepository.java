package com.example.android_firebase_2.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritosRepository {
    private DatabaseReference userFavoritesRef;

    public FavoritosRepository(String userId) {
        userFavoritesRef = FirebaseDatabase.getInstance()
                .getReference("usuarios/" + userId + "/favoritos");
    }

    public LiveData<List<String>> obtenerFavoritos() {
        MutableLiveData<List<String>> favoritosLiveData = new MutableLiveData<>();
        userFavoritesRef.addValueEventListener(new ValueEventListener() { // Escucha cambios en tiempo real
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<String> favoritos = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    favoritos.add(child.getKey());
                }
                favoritosLiveData.setValue(favoritos);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Manejo de errores
                favoritosLiveData.setValue(new ArrayList<>());
                System.err.println("Error al obtener favoritos: " + error.getMessage());
            }
        });
        return favoritosLiveData;
    }

    public void agregarFavorito(String ilustradorId) {
        userFavoritesRef.child(ilustradorId).setValue(true);
    }

    public void eliminarFavorito(String ilustradorId) {
        userFavoritesRef.child(ilustradorId).removeValue();
    }
}