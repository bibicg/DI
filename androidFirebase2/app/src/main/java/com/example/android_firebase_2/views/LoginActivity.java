package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android_firebase_2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Actividad de inicio de sesión para la aplicación
public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText etEmail, etPassword;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializamos Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Inicialización de campos de texto y botones
        etEmail = findViewById(R.id.emailRegEditText);
        etEmail.setContentDescription("Escribe el mail con el que te quieres loguear");
        etPassword = findViewById(R.id.passwordEditText);
        etPassword.setContentDescription("Escribe una contraseña");

        loginBtn = findViewById(R.id.loginButton);
        loginBtn.setContentDescription("Botón para loguearte");

        // Listener para iniciar sesión cuando se presiona el botón
        loginBtn.setOnClickListener(v -> loginUser());

        registerBtn = findViewById(R.id.registerGoButton);
        registerBtn.setContentDescription("Botón para registrarte");

        // Listener para ir a la pantalla de registro
        registerBtn.setOnClickListener(v -> {
            // Ir a la pantalla de registro
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    // Método para manejar el inicio de sesión
    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validamos que los campos no estén vacíos
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            Log.e("LOGIN_ERROR", "Campos vacíos en el login.");
            return;
        }

        Log.d("LOGIN", "Intentando iniciar sesión con: " + email);

        // Autenticación con Firebase
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("LOGIN", "Inicio de sesión exitoso.");

                        // Verificamos si el usuario autenticado no es null
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Log.d("LOGIN", "Usuario autenticado: " + user.getEmail());
                        } else {
                            Log.e("LOGIN_ERROR", "Usuario autenticado es null después del login.");
                        }

                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Evita volver a la pantalla de login

                    } else {
                        Log.e("LOGIN_ERROR", "Error en autenticación: " + task.getException().getMessage());
                        Toast.makeText(LoginActivity.this, "Error en autenticación: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
