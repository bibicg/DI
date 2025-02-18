package com.example.android_firebase_2.views;

import android.content.Context;
import android.content.Intent;
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
import android.os.Bundle;

import java.util.List;

/**
 * conecta los datos del modelo con las vistas del RecyclerView.
 */

public class IllustratorAdapter extends RecyclerView.Adapter<IllustratorAdapter.IllustratorViewHolder> {
    private List<Illustrator> illustrators;
    //añadimos esto al usar fragments
    private FragmentManager fragmentManager;

    public IllustratorAdapter(List<Illustrator> illustrators) {
        this.illustrators = illustrators;
    }

    public IllustratorAdapter(List<Illustrator> illustrators, FragmentManager fragmentManager) {
        this.illustrators = illustrators;
        this.fragmentManager = fragmentManager;
    }


    /**
    @NonNull
    @Override
    public IllustratorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usamos DataBinding para inflar el layout del item
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemIllustratorBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_illustrator, parent, false);
        return new IllustratorViewHolder(binding);
    }*/

    @NonNull
    @Override
    public IllustratorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemIllustratorBinding binding = ItemIllustratorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IllustratorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IllustratorViewHolder holder, int position) {
        Illustrator illustrator = illustrators.get(position);
        holder.bind(illustrator);
    }

    @Override
    public int getItemCount() {
        return illustrators != null ? illustrators.size() : 0;
    }

    public void setIllustrators(List<Illustrator> illustrators) {
        this.illustrators = illustrators;
        notifyDataSetChanged();  // Actualiza el adaptador con los nuevos datos
    }

    public void updateData(List<Illustrator> illustrators) {
    }

    /** cuando se abria una ACTIVIDAD
    static class IllustratorViewHolder extends RecyclerView.ViewHolder {
        private final ItemIllustratorBinding binding;

        public IllustratorViewHolder(ItemIllustratorBinding binding) {
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
        }*/

    // Ahora se abre un FRAGMENT en lugar de una actividad:
    class IllustratorViewHolder extends RecyclerView.ViewHolder {
        private final ItemIllustratorBinding binding;

        public IllustratorViewHolder(ItemIllustratorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

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
            // Enlazamos el ilustrador con el layout del item
            binding.setIllustrator(illustrator);
            Picasso.get().load(illustrator.getImagen()).into(binding.illustratorImage);
            binding.executePendingBindings();
        }
    }

}

