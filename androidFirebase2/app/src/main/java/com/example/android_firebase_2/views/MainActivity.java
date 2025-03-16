package com.example.android_firebase_2.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.android_firebase_2.viewmodels.FavoritosViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
// import android.widget.Toolbar; CUIDADO, ESTA IMPORTACIÓN DA ERROR"""""""""""
import androidx.appcompat.widget.Toolbar;  // ESTA ES LA BUENA
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.example.android_firebase_2.R;
import com.google.firebase.auth.FirebaseUser;

import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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
                Log.d("MAIN_ACTIVITY", "Cambiando a RandomFragment...");
                selectedFragment = new RandomFragment();
            } else if (itemId == R.id.nav_clean) {
                limpiarFavoritos();
            } else if (itemId == R.id.nav_logout) {
                logoutUser();
                return true;
            }

            if (selectedFragment != null) {
                Log.d("MAIN_ACTIVITY", "Fragmento seleccionado: " + selectedFragment.getClass().getSimpleName());
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

    /** sin shared preferences
    private void logoutUser() {
        Log.d("MAIN_ACTIVITY", "Cerrando sesión...");
        FirebaseAuth.getInstance().signOut();
        drawerLayout.closeDrawers();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }*/

    //implementando las shared preferences en el logout:
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();

        SharedPreferences sharedPref = getSharedPreferences("AppConfig", Context.MODE_PRIVATE);
        sharedPref.edit().remove("userId").apply(); // Eliminamos userId

        Log.d("LOGOUT", "Usuario eliminado de SharedPreferences");

        startActivity(new Intent(this, LoginActivity.class));
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

    /**
     * DESDE EL NAVIGATION DRAWER, ACCEDEMOS A LIMPIAR-FAVORITOS. ¿Cómo funciona este método?:
     * 1 - Obtiene el userId del usuario autenticado.
     * 2 - Crea una instancia de FavoritosViewModel con el userId usando ViewModelProvider.
     * 3 - Llama al método limpiarFavoritos(userId) en FavoritosViewModel para eliminar los favoritos del usuario en Firebase.
     * 4 - Muestra un mensaje (Toast) confirmando que los favoritos fueron eliminados.
     * 5 - Recarga DashboardFragment para reflejar los cambios.
     */

    private void limpiarFavoritos() {
        Log.d("LIMPIAR_FAVORITOS", "Botón de Limpiar Favoritos presionado.");
        //1 - Este userId se usa para acceder a la base de datos y borrar los favoritos de ese usuario:
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("LIMPIAR_FAVORITOS", "Usuario autenticado con ID: " + userId);

        //2 - Crea una instancia de FavoritosViewModel con ViewModelProvider:
        //(la factorty es necesaria pq favoritosViewModel necesita el userId en sus parámetros)
        FavoritosViewModel favoritosViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new FavoritosViewModel(userId);
            }
        }).get(FavoritosViewModel.class);

        Toast.makeText(this, "Eliminando favoritos...", Toast.LENGTH_SHORT).show();

        //Se llama al metodo LimpiarFavoritos del FavoritosViewModel, que es quien elimina
        //la referencia de los favoritos del usuario en Firebase, desvinculando los ilustradores:
        favoritosViewModel.limpiarFavoritos(userId);
        Log.d("LIMPIAR_FAVORITOS", "Se ha llamado a limpiarFavoritos en ViewModel.");

        Toast.makeText(this, "Se han eliminado todos los favoritos", Toast.LENGTH_SHORT).show();

        //Se reemplaza DashboardFragment por una nueva instancia del mismo fragmento
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new DashboardFragment())
                .commit();
        }

        //usando Shared Preferences para recuperar el id del usuario
    private void limpiarFavoritosSp() {
        Log.d("LIMPIAR_FAVORITOS", "Botón de Limpiar Favoritos presionado.");

        // 1. Obtener userId desde SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        String userId = prefs.getString("userId", null);

        if (userId == null) {
            Log.e("LIMPIAR_FAVORITOS", "No se encontró el userId en SharedPreferences");
            Toast.makeText(this, "Error: No se encontró la información del usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("LIMPIAR_FAVORITOS", "UserId obtenido desde SharedPreferences: " + userId);

        // 2. Crear instancia de ViewModel
        FavoritosViewModel favoritosViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new FavoritosViewModel(userId);
            }
        }).get(FavoritosViewModel.class);

        Toast.makeText(this, "Eliminando favoritos...", Toast.LENGTH_SHORT).show();

        // 3. Llamar a limpiarFavoritos
        favoritosViewModel.limpiarFavoritos(userId);
        Log.d("LIMPIAR_FAVORITOS", "Se ha llamado a limpiarFavoritos en ViewModel.");

        Toast.makeText(this, "Se han eliminado todos los favoritos", Toast.LENGTH_SHORT).show();

        // 4. Recargar DashboardFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new DashboardFragment())
                .commit();
    }


}




