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

/**
 * conecta los datos del modelo con las vistas del RecyclerView.
 */

public class IllustratorAdapter extends RecyclerView.Adapter<IllustratorAdapter.IllustratorViewHolder> {
    private List<Illustrator> illustrators;
    //añadimos esto al usar fragments (navegacion entre fragments: de dashboard a detail)
    private FragmentManager fragmentManager;

    // Constructor del adaptador que recibe una lista de ilustradores
    /**Este sería de desde el fragment navegaramos a una activity
    public IllustratorAdapter(List<Illustrator> illustrators) {
        this.illustrators = illustrators;
    }*/

    //!!!si que necesitamos este constructor con parametro fragment, ya que pasamos de un fragment a otro
    //de dashboarda detail, y ese paso se hace con FRAGMENT TRANSACTION
    public IllustratorAdapter(List<Illustrator> illustrators, FragmentManager fragmentManager) {
        this.illustrators = illustrators;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public IllustratorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Usamos DataBinding para inflar el layout del item
        //!!!Inflamos el layout del item utilizando DataBinding
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemIllustratorBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_illustrator, parent, false);
        return new IllustratorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IllustratorViewHolder holder, int position) {
        //!!!Vinculamos el ilustrador con la vista del ViewHolder
        Illustrator illustrator = illustrators.get(position);
        holder.bind(illustrator);
    }

    @Override
    public int getItemCount() {
        //!!!Retorna el número de elementos en la lista
        return illustrators != null ? illustrators.size() : 0;
    }

    //!!!Método para actualizar la lista de ilustradores y notificar cambios en la vista
    public void setIllustrators(List<Illustrator> illustrators) {
        this.illustrators = illustrators;
        notifyDataSetChanged();  // Actualiza el adaptador con los nuevos datos
    }

    public void updateData(List<Illustrator> illustrators) {
    }

    //!!!ViewHolder que contiene y maneja la vista de cada ilustrador
    /**Cuando se abría una ACTIVITY
    static class IllustratorViewHolder extends RecyclerView.ViewHolder {
        private final ItemIllustratorBinding binding;

        public IllustratorViewHolder(ItemIllustratorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            // Aquí movemos el OnClickListener al ViewHolder
            //!!!// Agregar listener para abrir los detalles del ilustrador al hacer clic
            //!!!Listener para manejar el clic sobre un ilustrador y abrir la pantalla de detalles
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

    //Ahora se abre un fragment:
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

        //!!!Método para asociar el ilustrador con la vista
        public void bind(Illustrator illustrator) {
            // Enlazamos los datos del ilustrador con el layout del item
            binding.setIllustrator(illustrator);
            //!!!Cargar la imagen del ilustrador con Picasso
            Picasso.get().load(illustrator.getImagen()).into(binding.illustratorImage);
            binding.executePendingBindings();
        }
    }

    // Para randomFragmen, para poder manejar un solo ilustador, no una lista:
    public void setSingleIllustrator(Illustrator illustrator) {
        this.illustrators.clear();
        this.illustrators.add(illustrator);
        notifyDataSetChanged();
    }


}
