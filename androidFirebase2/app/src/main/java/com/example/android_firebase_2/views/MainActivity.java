package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
// import android.widget.Toolbar; CUIDADO, ESTA IMPORTACIÓN DA ERROR"""""""""""
import androidx.appcompat.widget.Toolbar;  // ESTA ES LA BUENA
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import com.example.android_firebase_2.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import androidx.core.view.GravityCompat;



public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        // Configurar el Toolbar como ActionBar, pero me da error
        //setSupportActionBar(toolbar);


        /**
        // Configurar el botón de menú (hamburguesa)
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();*/

        // la ActionBar muestra el botón de navegación: PERO NO FUNCIONA!!!!!
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Listener de los elementos del menú lateral
        // para saber cuak se está pulsando
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (itemId == R.id.nav_favoritos) {
                selectedFragment = new FavoritosFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            } else if (itemId == R.id.nav_logout) {
                logoutUser();
                return true;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, selectedFragment)
                        .commit();
            }

            drawerLayout.closeDrawers(); // Cerrar el menú después de seleccolnar
            return true;
        });

        // Cargamos el DashboardFragment por defecto para que se vea algo
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new DashboardFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_dashboard); // Marcar "Dashboard" como seleccionado
        }
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        drawerLayout.closeDrawers(); // Cerrar el menú antes de salir
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
