package com.example.android_firebase_2.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

// migramos de activity a fragment:
public class FavoritosFragment extends Fragment {
    private RecyclerView recyclerView;
    private FavoritosAdapter adapter;
    private UserViewModel userViewModel;
    private Button volver;

    public FavoritosFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewFavs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userViewModel.fetchFavoriteIllustrators(userId);
        userViewModel.getFavoriteIllustratorsLiveData().observe(getViewLifecycleOwner(), illustrators -> {
            adapter = new FavoritosAdapter(illustrators, getParentFragmentManager());
            recyclerView.setAdapter(adapter);
        });

        volver = view.findViewById(R.id.volverButton);
        volver.setOnClickListener(v -> requireActivity().onBackPressed());

        return view;
    }
}
