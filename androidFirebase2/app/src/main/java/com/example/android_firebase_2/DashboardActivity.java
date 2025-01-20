package com.example.android_firebase_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView tvTitulo, tvDescripcion;
    private ImageView ivImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("ilustradores/ilustrador1");

        tvTitulo = findViewById(R.id.tituloTextView);
        tvDescripcion = findViewById(R.id.descripcionTextView);
        ivImagen = findViewById(R.id.imagenTextView);

        loadIlustrador();

        findViewById(R.id.logoutButton).setOnClickListener(v -> logout());
    }

    private void loadIlustrador() {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String titulo = dataSnapshot.child("titulo").getValue(String.class);
                    String descripcion = dataSnapshot.child("descripcion").getValue(String.class);
                    String imagenUrl = dataSnapshot.child("imagen").getValue(String.class);

                    tvTitulo.setText(titulo);
                    tvDescripcion.setText(descripcion);
                    Picasso.get().load(imagenUrl).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(ivImagen);
                } else {
                    Toast.makeText(DashboardActivity.this, "No se encontraron datos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DashboardActivity.this, "Error al cargar los datos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void logout() {
        mAuth.signOut();
        Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
