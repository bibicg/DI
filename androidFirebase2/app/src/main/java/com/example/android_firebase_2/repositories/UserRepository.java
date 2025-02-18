package com.example.android_firebase_2.repositories;

import com.example.android_firebase_2.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.lifecycle.MutableLiveData;

// SIN LOS FAVORITOS (LO HAGO EN EL fAVORITOS REPOSITORY):
public class UserRepository {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    public MutableLiveData<FirebaseUser> registerUser(String name, String email, String password, String phone, String address) {
        MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    User userProfile = new User(name, email, phone, address);

                    // Guardar datos en Firebase Realtime Database
                    mDatabase.child(user.getUid()).setValue(userProfile)
                            .addOnSuccessListener(aVoid -> userLiveData.postValue(user))
                            .addOnFailureListener(e -> userLiveData.postValue(null));
                } else {
                    userLiveData.postValue(null);
                }
            } else {
                userLiveData.postValue(null);
            }
        });

        return userLiveData;
    }
    /** funciona el registro pero hay que salir de la app y volver a entrar para logearse
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    public MutableLiveData<FirebaseUser> registerUser(String name, String email, String password, String phone, String address) {
        MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    User userProfile = new User(name, email, phone, address);
                    mDatabase.child(user.getUid()).setValue(userProfile);
                }
                userLiveData.setValue(user);
            } else {
                userLiveData.setValue(null);
            }
        });
        return userLiveData;
    }*/
}


/**
// DESPUÉS DE LOS FAVORITOS:

public class UserRepository {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usuarios");
    }

    public MutableLiveData<FirebaseUser> registerUser(String name, String email, String password, String phone, String address) {
        MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    User userProfile = new User(name, email, phone, address);
                    mDatabase.child(user.getUid()).setValue(userProfile);
                }
                userLiveData.setValue(user);
            } else {
                userLiveData.setValue(null);
            }
        });
        return userLiveData;
    }

    // Método para añadir un favorito
    public void addFavourite(String userId, String illustratorId) {
        DatabaseReference userFavoritesRef = mDatabase.child(userId).child("favoritos");
        userFavoritesRef.child(illustratorId).setValue(true);
    }

    // Método para eliminar un favorito
    public void removeFavourite(String userId, String illustratorId) {
        DatabaseReference userFavoritesRef = mDatabase.child(userId).child("favoritos");
        userFavoritesRef.child(illustratorId).removeValue();
    }

    // Método para obtener los favoritos
    public LiveData<List<String>> getUserFavourites(String userId) {
        MutableLiveData<List<String>> favouritesLiveData = new MutableLiveData<>();
        DatabaseReference userFavoritesRef = mDatabase.child(userId).child("favoritos");

        userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<String> favourites = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    favourites.add(child.getKey());
                }
                favouritesLiveData.setValue(favourites);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Manejo de errores
            }
        });

        return favouritesLiveData;
    }
}*/


