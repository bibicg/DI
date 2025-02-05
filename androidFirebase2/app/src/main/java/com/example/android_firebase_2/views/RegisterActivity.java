package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.RegisterViewModel;


public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel registerViewModel;
    private EditText etFullName, etEmail, etPassword, etPasswordRepeat, etPhone, etAddress;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        etFullName = findViewById(R.id.fullNameEditText);
        etFullName.setContentDescription("Escribe tu nombre");
        etEmail = findViewById(R.id.emailRegEditText);
        etEmail.setContentDescription("Escribe tu email");
        etPassword = findViewById(R.id.passwordRegEditText);
        etPassword.setContentDescription("Escribe una contraseña");
        etPasswordRepeat = findViewById(R.id.passwordRepeatEditText);
        etPasswordRepeat.setContentDescription("Repite la contraseña");
        etPhone = findViewById(R.id.phoneEditText);
        etPhone.setContentDescription("Escribe tu númoro de teléfono");
        etAddress = findViewById(R.id.addressEditText);
        etAddress.setContentDescription("Escribe tu dirección");

        registerBtn = findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(v -> registerUser());

        registerViewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Toast.makeText(RegisterActivity.this, "Usuario registrado correctamente.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this, "Error en el registro.", Toast.LENGTH_SHORT).show();
            }
        });
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

        registerViewModel.register(name, email, password, phone, address);
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
}
