package com.example.android_firebase_2.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.squareup.picasso.Picasso;
import com.example.android_firebase_2.R;

/**
 * Para cargar las imágenes en la páginade random
 */

public class BindingAdapters {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.logo) // Imagen temporal mientras carga
                    .error(R.drawable.logo_texto) // Imagen si falla la carga
                    .into(view);
        } else {
            view.setImageResource(R.drawable.logo_texto);
        }
    }
}
