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

//!!!Adapter para mostrar la lista de ilustradores favoritos en un RecyclerView
public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosViewHolder> {
    private List<Illustrator> favoritos;
    private FragmentManager fragmentManager;

    /**Este cuando no son framents
    public FavoritosAdapter(List<Illustrator> favoritos) {
        this.favoritos = favoritos;
    }*/

    //Adaptado para fragments:
    public FavoritosAdapter(List<Illustrator> favoritos, FragmentManager fragmentManager) {
        this.favoritos = favoritos;
        this.fragmentManager = fragmentManager;
    }

    /** Cuando no son fragment
    @NonNull
    @Override
    public FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usamos DataBinding para inflar el layout del item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemIllustratorBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_illustrator, parent, false);
        return new FavoritosViewHolder(binding);
    }*/
    @NonNull
    @Override
    public FavoritosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIllustratorBinding binding = ItemIllustratorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FavoritosViewHolder(binding, fragmentManager);
    }


    @Override
    public void onBindViewHolder(@NonNull FavoritosViewHolder holder, int position) {
        Illustrator illustrator = favoritos.get(position);
        holder.bind(illustrator);
    }

    @Override
    public int getItemCount() {
        return favoritos != null ? favoritos.size() : 0;
    }

    public void setFavoritos(List<Illustrator> favoritos) {
        this.favoritos = favoritos;
        notifyDataSetChanged();  // Actualiza el adaptador con los nuevos datos
    }

    /**Este es entre activities, pero ahora Detail es FRAGMENT, no activity (esto es de la semana 3)
    static class FavoritosViewHolder extends RecyclerView.ViewHolder {
        private final ItemIllustratorBinding binding;

        public FavoritosViewHolder(ItemIllustratorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            // Aquí movemos el OnClickListener al ViewHolder
            binding.getRoot().setOnClickListener(v -> {
                Illustrator illustrator = binding.getIllustrator();
                if (illustrator != null) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("id", illustrator.getId());  // Pasa el ID del ilustrador
                    intent.putExtra("titulo", illustrator.getTitulo());
                    intent.putExtra("imagen", illustrator.getImagen());
                    intent.putExtra("descripcion", illustrator.getDescripcion());
                    context.startActivity(intent);
                }
            });
        }

        public void bind(Illustrator illustrator) {
            // Enlazamos el ilustrador con el layout del item
            binding.setIllustrator(illustrator);
            Picasso.get().load(illustrator.getImagen()).into(binding.illustratorImage);
            binding.executePendingBindings();
        }
    }*/

    //Adaptado para DETAIL FRAGMENT:
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




