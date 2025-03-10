package com.example.android_firebase_2.repositories;

import com.example.android_firebase_2.models.Illustrator;
import com.example.android_firebase_2.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    public UserRepository() {
        this.mAuth = FirebaseAuth.getInstance();
        this.databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    // Método para registrar un nuevo usuario en Firebase Authentication y almacenarlo en la base de datos
    public MutableLiveData<FirebaseUser> registerUser(String name, String email, String password, String phone, String address) {
        MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                if (firebaseUser != null) {
                    // Crear objeto de usuario y guardarlo en la base de datos de Firebase
                    User user = new User(name, email, phone, address);
                    databaseReference.child(firebaseUser.getUid()).setValue(user);
                }
                userLiveData.setValue(firebaseUser);
            } else {
                userLiveData.setValue(null);
            }
        });
        return userLiveData;
    }

    // Obtener la lista de IDs de ilustradores favoritos del usuario
    public LiveData<List<String>> getUserFavourites(String userId) {
        MutableLiveData<List<String>> favouritesLiveData = new MutableLiveData<>();
        databaseReference.child(userId).child("favoritos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> favouritesList = new ArrayList<>();
                for (DataSnapshot favSnapshot : snapshot.getChildren()) {
                    favouritesList.add(favSnapshot.getKey());
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

    // Obtener los detalles de los ilustradores favoritos
    public LiveData<List<Illustrator>> getFavoriteIllustrators(String userId) {
        MutableLiveData<List<Illustrator>> favoriteIllustratorsLiveData = new MutableLiveData<>();
        getUserFavourites(userId).observeForever(favouriteIds -> {
            if (favouriteIds == null || favouriteIds.isEmpty()) {
                favoriteIllustratorsLiveData.setValue(new ArrayList<>());
                return;
            }

            FirebaseDatabase.getInstance().getReference("ilustradores").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Illustrator> favoriteIllustrators = new ArrayList<>();
                    for (DataSnapshot illustratorSnapshot : snapshot.getChildren()) {
                        Illustrator illustrator = illustratorSnapshot.getValue(Illustrator.class);
                        if (illustrator != null && favouriteIds.contains(illustrator.getId())) {
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
        return favoriteIllustratorsLiveData;
    }

    // Agregar un ilustrador a favoritos
    public void addFavourite(String userId, String illustratorId) {
        databaseReference.child(userId).child("favoritos").child(illustratorId).setValue(true);
    }

    // Eliminar un ilustrador de favoritos
    public void removeFavourite(String userId, String illustratorId) {
        databaseReference.child(userId).child("favoritos").child(illustratorId).removeValue();
    }
}
