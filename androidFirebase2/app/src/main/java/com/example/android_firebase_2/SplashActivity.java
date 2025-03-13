package com.example.android_firebase_2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_firebase_2.views.LoginActivity;
import com.example.android_firebase_2.views.MainActivity;

/** version sin shared preferences con el id del usuario:
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
/**}
*/


/**
 * Implemento las Shared Preferences para el inicio de sesión directo en main activity
 * cuando el usuario ya se ha loueado previamente en el app
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //!!!Con las sharedPreferences hay que comprobar si hay un usuario autenticado:
        SharedPreferences sharedPref = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        String userId = sharedPref.getString("userId", null);

        if (userId != null) {
            // Si  ya hay un suario autenticado previamente, se redirige a MainActivity
            // sin pasar por login:
            startActivity(new Intent(this, MainActivity.class));
        } else {
            // pero si no hay un usuario autenticado, se redirige a Login:
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}