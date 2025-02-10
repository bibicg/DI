package com.example.android_firebase_2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.android_firebase_2.R;

public class DashboardFragment extends Fragment {
    // Constructor vacío
    public DashboardFragment() { }

    // Se infla el layout del fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla el layout del fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        // Aquí configuras RecyclerView, adapters, etc.
        return view;
    }
}
