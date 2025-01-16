package com.example.androidfirebase;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.registerButton).setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        // Obtener los datos de los campos
        String fullName = ((EditText) findViewById(R.id.fullNameEditText)).getText().toString();
        String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.passwordRepeatEditText)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phoneEditText)).getText().toString();
        String address = ((EditText) findViewById(R.id.addressEditText)).getText().toString();

        // Validaciones básicas
        if (fullName.isEmpty()) {
            ((EditText) findViewById(R.id.fullNameEditText)).setError("El nombre completo es obligatorio");
            return;
        }

        if (email.isEmpty()) {
            ((EditText) findViewById(R.id.emailEditText)).setError("El email es obligatorio");
            return;
        }

        if (password.isEmpty()) {
            ((EditText) findViewById(R.id.passwordEditText)).setError("La contraseña es obligatoria");
            return;
        }

        if (password.length() < 6) {
            ((EditText) findViewById(R.id.passwordEditText)).setError("La contraseña debe tener al menos 6 caracteres");
            return;
        }

        if (!password.equals(confirmPassword)) {
            ((EditText) findViewById(R.id.passwordRepeatEditText)).setError("Las contraseñas no coinciden");
            return;
        }

        if (phone.isEmpty()) {
            ((EditText) findViewById(R.id.phoneEditText)).setError("El teléfono es obligatorio");
            return;
        }

        if (address.isEmpty()) {
            ((EditText) findViewById(R.id.addressEditText)).setError("La dirección es obligatoria");
            return;
        }

        // Registrar usuario en Firebase Authentication
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Aquí podrías guardar los datos adicionales en Firebase Realtime Database
                        saveUserData(fullName, email, phone, address);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Error en el registro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserData(String fullName, String email, String phone, String address) {
        // Obtener el ID del usuario registrado
        String userId = mAuth.getCurrentUser().getUid();

        // Crear un nodo en Firebase Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        User user = new User(fullName, email, phone, address);

        databaseReference.child(userId).setValue(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Usuario registrado correctamente.", Toast.LENGTH_SHORT).show();
                        finish(); // Cierra la actividad actual
                    } else {
                        Toast.makeText(MainActivity.this, "Error al guardar los datos: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Clase auxiliar para estructurar los datos del usuario
    public static class User {
        public String fullName, email, phone, address;

        public User() {
            // Constructor vacío requerido por Firebase
        }

        public User(String fullName, String email, String phone, String address) {
            this.fullName = fullName;
            this.email = email;
            this.phone = phone;
            this.address = address;
        }
    }
}

