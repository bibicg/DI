package com.example.android_firebase_2.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.example.android_firebase_2.R;
import com.example.android_firebase_2.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

/**
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_dashboard: openFragment(new DashboardFragment()); break;
                case R.id.nav_favoritos: openFragment(new FavoritosFragment()); break;
                case R.id.nav_profile: openFragment(new ProfileFragment()); break;
                case R.id.nav_logout: logoutUser(); break;
            }
            binding.drawerLayout.closeDrawers();
            return true;
        });

        if (savedInstanceState == null) {
            openFragment(new DashboardFragment());
        }
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}*/



public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // con switch case no reconoce los items del drawer_menu
        Map<Integer, Runnable> navigationMap = new HashMap<>();
        navigationMap.put(R.id.nav_dashboard, () -> openFragment(new DashboardFragment()));
        navigationMap.put(R.id.nav_favoritos, () -> openFragment(new FavoritosFragment()));
        navigationMap.put(R.id.nav_profile, () -> openFragment(new ProfileFragment()));
        navigationMap.put(R.id.nav_logout, this::logoutUser);

        binding.navigationView.setNavigationItemSelectedListener(item -> {
            Runnable action = navigationMap.get(item.getItemId());
            if (action != null) {
                action.run();
            }
            binding.drawerLayout.closeDrawers();
            return true;
        });


        if (savedInstanceState == null) {
            openFragment(new DashboardFragment());
        }
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
