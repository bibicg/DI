package com.example.android_firebase_2.repositories;

import android.util.Log;
import com.example.android_firebase_2.models.Illustrator;
import com.google.firebase.database.*;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Se encarga de interactuar con Firebase Realtime Database obteniendo los datos de los ilustradores.
 * Los expone a través de LiveData.
 *
 * En concreto:
 * - Inicialización de la referencia de Firebase
 * - Carga de ilustradores (Se carga la lista de ilustradores desde Firebase y se actualiza el LiveData correspondiente)
 * - Provisión de datos: Proporciona un LiveData<List<Illustrator>> que puede ser observado para recibir actualizaciones de la lista de ilustradores.
 * - Obtención de un ilustrador aleatorio (Permite obtener un ilustrador aleatorio de la lista cargada desde Firebase y actualizar un LiveData con dicho ilustrador).
 */

public class IllustratorRepository {
    private final DatabaseReference illustratorRef;
    private final MutableLiveData<List<Illustrator>> illustratorLiveData = new MutableLiveData<>();

    public IllustratorRepository() {
        //!!!Referencia a la colección de ilustradores en Firebase
        illustratorRef = FirebaseDatabase.getInstance().getReference("ilustradores");
        loadIllustrators();
    }


    //!!!Método para cargar ilustradores desde Firebase y actualizar el LiveData
    private void loadIllustrators() {
        illustratorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Illustrator> illustrators = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Illustrator illustrator = child.getValue(Illustrator.class);
                    if (illustrator != null) {
                        illustrators.add(illustrator);
                    }
                }
                Log.d("IllustratorRepository", "Ilustradores obtenidos: " + illustrators.size());
                illustratorLiveData.postValue(illustrators);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("IllustratorRepository", "Error al obtener ilustradores: " + error.getMessage());
            }
        });
    }

    public LiveData<List<Illustrator>> getIllustrators() {
        return illustratorLiveData;
    }


    //!!!Método para obtener la lista de ilustradores desde Firebase
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

    //!!!Método para obtener un ilustrador random desde Firebase
    public void getRandomIllustrator(MutableLiveData<Illustrator> illustratorLiveData) {
        illustratorRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<Illustrator> illustrators = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    Illustrator illustrator = child.getValue(Illustrator.class);
                    if (illustrator != null) {
                        illustrators.add(illustrator);
                    }
                }

                if (illustrators.isEmpty()) {
                    Log.e("IllustratorRepository", "La lista de ilustradores está vacía.");
                    illustratorLiveData.postValue(null);
                    return;
                }

                // Generar un índice aleatorio
                Random random = new Random();
                int randomIndex = random.nextInt(illustrators.size());
                Illustrator illustratorRandom = illustrators.get(randomIndex);

                // Actualizar el LiveData con el ilustrador aleatorio
                illustratorLiveData.setValue(illustratorRandom);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("IllustratorRepository", "Error al obtener ilustrador aleatorio: " + error.getMessage());
            }
        });
    }



}
