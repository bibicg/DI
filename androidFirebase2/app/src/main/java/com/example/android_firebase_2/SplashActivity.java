package com.example.android_firebase_2;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_firebase_2.views.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lanza la actividad principal
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
