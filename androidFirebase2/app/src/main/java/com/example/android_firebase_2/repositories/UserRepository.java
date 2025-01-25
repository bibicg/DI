package com.example.android_firebase_2.repositories;

import com.example.android_firebase_2.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.lifecycle.MutableLiveData;

/**
 * Implementar un repositorio (UserRepository) que gestione la autenticación y el almacenamiento
 * de datos en Firebase.
 */
public class UserRepository {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public MutableLiveData<FirebaseUser> registerUser(String name, String email, String password, String phone, String address) {
        MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = mAuth.getCurrentUser();
                if (user != null) {
                    User userProfile = new User(name, email, phone, address);
                    mDatabase.child("users").child(user.getUid()).setValue(userProfile);
                }
                userLiveData.setValue(user);
            } else {
                userLiveData.setValue(null);
            }
        });
        return userLiveData;
    }
}

