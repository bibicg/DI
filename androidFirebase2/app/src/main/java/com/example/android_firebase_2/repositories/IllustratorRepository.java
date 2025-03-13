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
    //Referencia a Firebase donde están almacenados todos los ilustradores:
    private final DatabaseReference illustratorRef;
    //Referencia a los ilustradores favoritos de un usuario específico. No es final porque puede ser null si no hay usuario:
    private DatabaseReference userFavoritesRef;
    //illustratorLiveData: Almacena y notifica cambios en la lista de ilustradores:
    private final MutableLiveData<List<Illustrator>> illustratorLiveData = new MutableLiveData<>();

    /** Se elimina este constructor ya que ahora siempre tenemos que conocer los favoritos del usuario, por tanto su id
     public IllustratorRepository() {
        //!!!Referencia a la colección de ilustradores en Firebase
        illustratorRef = FirebaseDatabase.getInstance().getReference("ilustradores");

        loadIllustrators();
     }*/

    //Como eliminamos el constructor sin parámetros, es necesario comprobar que el userId no sea null
    public IllustratorRepository(String userId) {
        illustratorRef = FirebaseDatabase.getInstance().getReference("ilustradores");

        //userFavoritesRef no es final para que permita hacer esta asignacion condicional:
        //Si userId es válido, crea la referencia a los favoritos del usuario.
        //Si userId es null o vacío, userFavoritesRef queda null y se registra un error.
        /**
         * MEJORAS POSIBLES: Validar userId antes de crear la instancia del repositorio en el ViewModel
         */
        if (userId != null && !userId.isEmpty()) { // Asegurar que userId no sea null o vacío
            userFavoritesRef = FirebaseDatabase.getInstance().getReference("users/" + userId + "/favoritos");
        } else {
            userFavoritesRef = null;
            Log.e("IllustratorRepository", "Error: userId es nulo o vacío.");
        }
    }

    /**
     * Expone illustratorLiveData y carga los ilustradores desde Firebase
     * Solo llamar loadIllustrators() si illustratorLiveData está vacío, porque sino
     * cada vez que se llama a getIllustrators(), se ejecuta loadIllustrators() nuevamente,
     * lo que puede generar múltiples llamadas innecesarias a Firebase.
     * @return
     */
    public LiveData<List<Illustrator>> getIllustrators() {
        if (illustratorLiveData.getValue() == null) {
            loadIllustrators();
        }
        return illustratorLiveData;
    }


    //!!!Método para cargar ilustradores desde Firebase y actualizar el LiveData
    private void loadIllustrators() {
        //Se usa addValueEventListener(), lo que significa que se actualizará cada vez que Firebase cambie
        //Si los datos no cambian con frecuencia, mejor usar addListenerForSingleValueEvent()
        //que carga los datos una sola vez en lugar de escuchar cambios constantes.
        illustratorRef.addValueEventListener(new ValueEventListener() {
            //Recorre los hijos de snapshot para convertirlos en objetos Illustrator y los almacena en illustratorLiveData
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


    /**
     * METODO DUPLICADO, HACE LO MISMO UE LOAD ILLUSTRATORS. podemos eliminarlo.
     * El otro es mejor porque usa LiveData, lo que facilita su uso en el viewModel
     * @param illustratorLiveData
     */
    /**
    //!!!Método para obtener la lista de ilustradores desde Firebase.
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
    }*/

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

                Random random = new Random();
                int randomIndex = random.nextInt(illustrators.size());
                Illustrator illustratorRandom = illustrators.get(randomIndex);

                illustratorLiveData.setValue(illustratorRandom);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("IllustratorRepository", "Error al obtener ilustrador aleatorio: " + error.getMessage());
            }
        });
    }

    /**
     * MÉTODO PARA OBTENER ILUSTRADORES EXCLUYENDO LOS FAVORITOS
     * (Tenía este metodo en el viewModel, pero debe estar aqui y, desde viewModel, llamar a este método)
     *
     * 1- Verifica si userFavoritesRef es null (si lo es no podrá devolver la lista de illust.). Se registra un error y se detiene la ejecución.
     * 2- Obtiene la lista de ilustradores favoritos del usuario desde Firebase (1 sola vez):
     *      · Hace una consulta a userFavoritesRef y almacena los IDs de los ilustradores favoritos en una lista favoritosIds
     * 3- Luego, consulta illustratorRef para obtener todos los ilustradores
     * 4- Filtra los ilustradores para excluir los favoritos: solo añade a la lista los ilustradores cuyo id no esté en favoritosIds.
     * 5- Actualiza illustratorLiveData con la lista filtrada: finalmente, los ilustradores no favoritos se publican en el LiveData.
     * @param illustratorLiveData
     */

    public void getIllustratorsNoFav(MutableLiveData<List<Illustrator>> illustratorLiveData) {
        if (userFavoritesRef == null) { //1
            Log.e("IllustratorRepository", "userFavoritesRef es nulo. No se pueden obtener favoritos.");
            return;
        }

        //userFavoritesRef.addListenerForSingleValueEvent(new ValueEventListener() { //2
        userFavoritesRef.addValueEventListener(new ValueEventListener() { //2: observable permanente, mas adecuado pq quiero que se actualice al dashboard
            @Override
            public void onDataChange(DataSnapshot favoritosSnapshot) {
                List<String> favoritosIds = new ArrayList<>();
                for (DataSnapshot child : favoritosSnapshot.getChildren()) {
                    favoritosIds.add(child.getKey()); //los favoritos se almacenan en la lista "favoritosIds"
                }

                //illustratorRef.addListenerForSingleValueEvent(new ValueEventListener() { //3
                illustratorRef.addValueEventListener(new ValueEventListener() { //3: observable permanente, mas adecuado pq quiero que se actualice al dashboard
                    @Override
                    public void onDataChange(DataSnapshot illustratorsSnapshot) {
                        List<Illustrator> illustrators = new ArrayList<>();
                        for (DataSnapshot child : illustratorsSnapshot.getChildren()) {
                            Illustrator illustrator = child.getValue(Illustrator.class);

                            if (illustrator != null && !favoritosIds.contains(illustrator.getId())) { //4
                                illustrators.add(illustrator);
                            }
                        }
                        illustratorLiveData.setValue(illustrators); //5
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.e("IllustratorRepository", "Error al obtener ilustradores: " + error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.e("IllustratorRepository", "Error al obtener favoritos: " + error.getMessage());
            }
        });
    }

}




