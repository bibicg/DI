package com.example.android_firebase_2.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.databinding.ItemIllustratorBinding;
import com.example.android_firebase_2.models.Illustrator;
import com.squareup.picasso.Picasso;
import java.util.List;


public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosViewHolder> {
    private List<Illustrator> favoritosList;
    private FragmentManager fragmentManager;

    public FavoritosAdapter(List<Illustrator> favoritosList, FragmentManager fragmentManager) {
        this.favoritosList = favoritosList;
        this.fragmentManager = fragmentManager;
    }

    public void setFavoritosList(List<Illustrator> favoritosList) {
        this.favoritosList = favoritosList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIllustratorBinding binding = ItemIllustratorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoritosViewHolder(binding, fragmentManager);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritosViewHolder holder, int position) {
        Illustrator illustrator = favoritosList.get(position);
        holder.bind(illustrator);
    }

    @Override
    public int getItemCount() {
        return favoritosList.size();
    }

    class FavoritosViewHolder extends RecyclerView.ViewHolder {
        private final ItemIllustratorBinding binding;
        private final FragmentManager fragmentManager;

        //Lo adaptamos al fragment:
        public FavoritosViewHolder(ItemIllustratorBinding binding, FragmentManager fragmentManager) {
            super(binding.getRoot());
            this.binding = binding;
            this.fragmentManager = fragmentManager;

            binding.getRoot().setOnClickListener(v -> {
                Illustrator illustrator = binding.getIllustrator();
                if (illustrator != null) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", illustrator.getId());
                    bundle.putString("titulo", illustrator.getTitulo());
                    bundle.putString("imagen", illustrator.getImagen());
                    bundle.putString("descripcion", illustrator.getDescripcion());
                    detailFragment.setArguments(bundle);

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, detailFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

        public void bind(Illustrator illustrator) {
            binding.setIllustrator(illustrator);
            Picasso.get().load(illustrator.getImagen()).into(binding.illustratorImage);
            binding.executePendingBindings();
        }
    }
}
