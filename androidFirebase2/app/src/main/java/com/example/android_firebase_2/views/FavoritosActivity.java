package com.example.android_firebase_2.views;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class FavoritosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritosAdapter adapter;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        recyclerView = findViewById(R.id.recyclerViewFavs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userViewModel.fetchFavoriteIllustrators(userId);
        userViewModel.getFavoriteIllustratorsLiveData().observe(this, illustrators -> {
            adapter = new FavoritosAdapter(illustrators);
            recyclerView.setAdapter(adapter);
        });
    }
}







