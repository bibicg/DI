package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.android_firebase_2.R;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText etEmail, etPassword;
    private Button loginBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.emailRegEditText);
        etEmail.setContentDescription("Escribe el mail con el que te quieres loguear");
        etPassword = findViewById(R.id.passwordEditText);
        etPassword.setContentDescription("Escribe una contraseña");

        loginBtn = findViewById(R.id.loginButton);
        loginBtn.setContentDescription("Botón para loguaerte");
        loginBtn.setOnClickListener(v -> loginUser());

        registerBtn = findViewById(R.id.registerGoButton);
        loginBtn.setContentDescription("Botón para registrarte");
        registerBtn.setOnClickListener(v -> {
            // Ir a la pantalla de registro
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show();
                        // Ir al dashboard o pantalla principal
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish(); // Opcional: cerrar LoginActivity para que no pueda volver atrás
                    } else {
                        Toast.makeText(LoginActivity.this, "Error en autenticación: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
