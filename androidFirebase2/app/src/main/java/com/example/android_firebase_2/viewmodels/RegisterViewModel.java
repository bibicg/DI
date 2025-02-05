package com.example.android_firebase_2.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.android_firebase_2.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends ViewModel {
    private UserRepository userRepository;
    private MutableLiveData<FirebaseUser> userLiveData;

    public RegisterViewModel() {
        userRepository = new UserRepository();
        userLiveData = new MutableLiveData<>();
    }

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public void register(String name, String email, String password, String phone, String address) {
        userLiveData = userRepository.registerUser(name, email, password, phone, address);
    }
}

