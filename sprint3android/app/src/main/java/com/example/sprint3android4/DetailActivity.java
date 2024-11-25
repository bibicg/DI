package com.example.sprint3android4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enlazo con la actividad, que es el archivo en xml
        setContentView(R.layout.activity_detail);

        // 16. linea necesaria porque hemos borrado el text view del xml
        // Aquí, inicialmente también añadimos un Fragment1 a la interfaz
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new Fragment1()).commit();


    }


}

