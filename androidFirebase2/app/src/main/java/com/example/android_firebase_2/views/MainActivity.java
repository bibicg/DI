package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
// import android.widget.Toolbar; CUIDADO, ESTA IMPORTACIÓN DA ERROR"""""""""""
import androidx.appcompat.widget.Toolbar;  // ESTA ES LA BUENA
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.example.android_firebase_2.R;
import com.google.firebase.auth.FirebaseUser;

import androidx.core.view.GravityCompat;

/**
 * Con la toolbar, pero no funciona bien
 */
/**
public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //asi se capturan errroes fatales antes de que se cierre la app
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            Log.e("FATAL_ERROR", "Se ha producido un error fatal: ", throwable);
        });


        super.onCreate(savedInstanceState);
        Log.d("MAIN_ACTIVITY", "MainActivity se está iniciando...");
        setContentView(R.layout.activity_main);

        // Verificar usuario autenticado
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            Log.d("MAIN_ACTIVITY", "Usuario autenticado en MainActivity: " + user.getEmail());
        } else {
            Log.e("MAIN_ACTIVITY_ERROR", "No se encontró un usuario autenticado en MainActivity. Redirigiendo a Login...");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return; // Evita seguir ejecutando código si no hay usuario autenticado
        }

        // Configuración del menú lateral
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (drawerLayout != null && navigationView != null) {
            Log.d("MAIN_ACTIVITY", "Navigation Drawer inicializado correctamente.");
        } else {
            Log.e("MAIN_ACTIVITY_ERROR", "Error al inicializar Navigation Drawer.");
        }

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //con este n funciona

        // Configurar el listener del Navigation Drawer
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (itemId == R.id.nav_favoritos) {
                selectedFragment = new FavoritosFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            } else if (itemId == R.id.nav_random) {
                selectedFragment = new RandomFragment();
            } else if (itemId == R.id.nav_logout) {
                logoutUser();
                return true;
            }

            if (selectedFragment != null) {
                Log.d("MAIN_ACTIVITY", "Cambiando a Fragment: " + selectedFragment.getClass().getSimpleName());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, selectedFragment)
                        .commit();
            } else {
                Log.e("MAIN_ACTIVITY_ERROR", "Error: Fragment seleccionado es NULL.");
            }

            drawerLayout.closeDrawers(); // Cerrar el menú después de seleccionar
            return true;
        });

        // Cargar DashboardFragment por defecto
        if (savedInstanceState == null) {
            Log.d("MAIN_ACTIVITY", "Cargando DashboardFragment por defecto...");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new DashboardFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    private void logoutUser() {
        Log.d("MAIN_ACTIVITY", "Cerrando sesión...");
        FirebaseAuth.getInstance().signOut();
        drawerLayout.closeDrawers();
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
}*/

/**
 * Sin la toolbar del mainActivity, sino usando la del tema (está en styles.xml)
 */

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    //private Toolbar toolbar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Capturar errores fatales antes de que se cierre la app
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            Log.e("FATAL_ERROR", "Se ha producido un error fatal: ", throwable);
        });

        super.onCreate(savedInstanceState);
        Log.d("MAIN_ACTIVITY", "MainActivity se está iniciando...");
        setContentView(R.layout.activity_main);

        // Verificar usuario autenticado
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        if (user == null) {
            Log.e("MAIN_ACTIVITY_ERROR", "No se encontró un usuario autenticado en MainActivity. Redirigiendo a Login...");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        Log.d("MAIN_ACTIVITY", "Usuario autenticado en MainActivity: " + user.getEmail());

        // Configuración del menú lateral
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        //toolbar = findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar); // Esta es la ÚNICA llamada a setSupportActionBar(toolbar)

        /**
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // Sincroniza el estado del Drawer
        */
        // Elimina esta línea: getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configurar el listener del Navigation Drawer
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (itemId == R.id.nav_favoritos) {
                selectedFragment = new FavoritosFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            } else if (itemId == R.id.nav_random) {
                selectedFragment = new RandomFragment();
            } else if (itemId == R.id.nav_logout) {
                logoutUser();
                return true;
            }

            if (selectedFragment != null) {
                Log.d("MAIN_ACTIVITY", "Cambiando a Fragment: " + selectedFragment.getClass().getSimpleName());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, selectedFragment)
                        .commit();
            } else {
                Log.e("MAIN_ACTIVITY_ERROR", "Error: Fragment seleccionado es NULL.");
            }

            drawerLayout.closeDrawers(); // Cerrar el menú después de seleccionar
            return true;
        });

        // Cargar DashboardFragment por defecto
        if (savedInstanceState == null) {
            Log.d("MAIN_ACTIVITY", "Cargando DashboardFragment por defecto...");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new DashboardFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }
    }

    private void logoutUser() {
        Log.d("MAIN_ACTIVITY", "Cerrando sesión...");
        FirebaseAuth.getInstance().signOut();
        drawerLayout.closeDrawers();
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

