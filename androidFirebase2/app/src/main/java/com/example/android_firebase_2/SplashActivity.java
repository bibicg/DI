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

    /**
     *  @Override
     *     protected void onCreate(Bundle savedInstanceState) {
     *         super.onCreate(savedInstanceState());
     *
     *         FirebaseAuth auth = FirebaseAuth.getInstance();
     *
     *         // Verificar si el usuario está autenticado
     *         if (auth.getCurrentUser() != null) {
     *             // Usuario logueado → Ir a MainActivity
     *             startActivity(new Intent(this, MainActivity.class));
     *         } else {
     *             // Usuario no logueado → Ir a LoginActivity
     *             startActivity(new Intent(this, LoginActivity.class));
     *         }
     *
     *         finish(); // Cierra SplashActivity para que no vuelva atrás
     *     }
     */
}
