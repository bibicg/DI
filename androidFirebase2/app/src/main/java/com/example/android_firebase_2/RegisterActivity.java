package com.example.android_firebase_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
        private FirebaseAuth mAuth;
        private DatabaseReference mDatabase;
        private EditText etFullName, etEmail, etPassword, etPasswordRepeat, etPhone, etAddress;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference();

            etFullName = findViewById(R.id.fullNameEditText);
            etEmail = findViewById(R.id.emailRegEditText);
            etPassword = findViewById(R.id.passwordRegEditText);
            etPasswordRepeat = findViewById(R.id.passwordRepeatEditText);
            etPhone = findViewById(R.id.phoneEditText);
            etAddress = findViewById(R.id.addressEditText);

            findViewById(R.id.registerButton).setOnClickListener(v -> registerUser());
        }

        private void registerUser() {
            if (!validateInputs()) {
                return;
            }

            String name = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            // Registrar datos adicionales en Realtime Database
                            FirebaseUser user = mAuth.getCurrentUser();
                            UserProfile userProfile = new UserProfile(name, email, phone, address);
                            mDatabase.child("users").child(user.getUid()).setValue(userProfile)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "Usuario registrado correctamente.", Toast.LENGTH_SHORT).show();
                                            // Ir a la pantalla de Login:
                                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "Error al guardar los datos adicionales: " + task1.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error en el registro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private boolean validateInputs() {
            String name = etFullName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etPasswordRepeat.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
                    || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }

        public class UserProfile {
            public String name;
            public String email;
            public String phone;
            public String address;

            public UserProfile() { }

            public UserProfile(String name, String email, String phone, String address) {
                this.name = name;
                this.email = email;
                this.phone = phone;
                this.address = address;
            }
        }
    }
