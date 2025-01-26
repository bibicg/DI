package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.android_firebase_2.R;
import com.example.android_firebase_2.databinding.ActivityDetailBinding;
import com.example.android_firebase_2.models.Illustrator;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleView;
    private TextView descriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.illustratorImageDetail);
        titleView = findViewById(R.id.illustratorTitleDetail);
        descriptionView = findViewById(R.id.illustratorDescriptionDetail);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String imagen = intent.getStringExtra("imagen");
        String descripcion = intent.getStringExtra("descripcion");

        titleView.setText(titulo);
        descriptionView.setText(descripcion);
        Picasso.get().load(imagen).into(imageView);

        // Configura el botón de volver
        Button volverBtn = findViewById(R.id.volverButton);
        volverBtn.setOnClickListener(v -> {
            finish(); // vuelve a DashboardActivity
        });
    }
}




