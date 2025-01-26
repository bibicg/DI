package com.example.android_firebase_2.repositories;

import android.util.Log;
import com.example.android_firebase_2.models.Illustrator;
import com.google.firebase.database.*;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

/**
 * Se encarga de interactuar con Firebase Realtime Database obteniendo los datos de los ilustradores.
 * Los expone a través de LiveData.
 */
public class IllustratorRepository {
    private final DatabaseReference illustratorRef;

    public IllustratorRepository() {
        illustratorRef = FirebaseDatabase.getInstance().getReference("ilustradores");
    }

    public void getIllustrators(MutableLiveData<List<Illustrator>> illustratorLiveData) {
        illustratorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Illustrator> illustrators = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Illustrator illustrator = child.getValue(Illustrator.class);
                    illustrators.add(illustrator);
                    Log.d("IllustratorRepository", "Illustrator: " + illustrator.getTitulo());  // Agregar un log para ver si los datos llegan
                }
                illustratorLiveData.setValue(illustrators);
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Manejo de errores
            }
        });
    }
}







