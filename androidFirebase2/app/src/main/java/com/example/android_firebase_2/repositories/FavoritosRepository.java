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

/**
 * NO SE USA, ESTÁ TODA LA LÓGICA EN USER REPOSITORY
 */

/**
public class FavoritosRepository {
    private DatabaseReference userFavoritesRef;

    public FavoritosRepository(String userId) {
        //!!!Referencia a los favoritos del usuario en Firebase
        userFavoritesRef = FirebaseDatabase.getInstance()
                .getReference("usuarios/" + userId + "/favoritos");
    }

    //!!!Método para obtener la lista de favoritos del usuario
    public LiveData<List<String>> obtenerFavoritos() {
        MutableLiveData<List<String>> favoritosLiveData = new MutableLiveData<>();
        userFavoritesRef.addValueEventListener(new ValueEventListener() { // Escucha cambios en tiempo real
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<String> favoritos = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    favoritos.add(child.getKey()); //!!!Almacenar el ID del ilustrador favorito
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

    //!!!Método para agregar un ilustrador a favoritos
    public void agregarFavorito(String ilustradorId) {
        userFavoritesRef.child(ilustradorId).setValue(true);
    }

    //!!!Método para eliminar un ilustrador de favoritos
    public void eliminarFavorito(String ilustradorId) {
        userFavoritesRef.child(ilustradorId).removeValue();
    }
}*/