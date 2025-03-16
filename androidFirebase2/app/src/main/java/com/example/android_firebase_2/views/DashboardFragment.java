package com.example.android_firebase_2.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.databinding.FragmentDashboardBinding;
import com.example.android_firebase_2.viewmodels.DashboardViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


/**
 * Ahora en DASHBOARD FRAGMENT no se cargan todos los ilustradores por defecto, sino solo aquellos
 * que no han sido marcados como favoritos por el usuario (es decir, la primera vez que el usuario entre
 * en la app, sí se cargarán todos los ilustradores pq aún no hay favoritos. También se cargarán todos los
 * ilustradores cuando se limpie favoritos).
 *
 * RESUMEN DEL MVVM EN RELACIÓN A LA PANTALLA DE DASHBOARD:
 * - DashboardFragment inicializa el DashboardViewModel y observa los cambios en los datos de ilustradores.
 * - DashboardViewModel utiliza IllustratorRepository para cargar los ilustradores y filtrar aquellos que no están en la lista de favoritos del usuario.
 * - IllustratorRepository interactúa con Firebase para obtener y proporcionar los datos de los ilustradores.
 *
 */

public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;
    private IllustratorAdapter illustratorAdapter;
    private FragmentDashboardBinding binding;

    public DashboardFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         * Si no usara DataBindg tendria que usar este enfoque para vincular la vista:
         *      View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
         *
         *     RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
         *     recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
         *
         *     IllustratorAdapter illustratorAdapter = new IllustratorAdapter(new ArrayList<>(), getParentFragmentManager());
         *     recyclerView.setAdapter(illustratorAdapter);
         *
         *     return view;
         */
        Log.d("DASHBOARD_FRAGMENT", "DashboardFragment se está creando...");

        //!!!Infla la vista y la vincula a Binding en dos pasos:
        //View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //FragmentDashboardBinding binding = FragmentDashboardBinding.bind(view);

        //Usamos DataBinding para inflar y vincular la vista que le corresponde en un solo paso:
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        /**
         * Podría usar esto ya que no tengo variables en el data del xml:
         * binding = FragmentDashboardBinding.inflate(inflater, container, false); // Solo ViewBinding
         */

        // Ahora debemos obtener el ID del usuario autenticado (como en Favoritos, ya que no es para todos el mismo dashboard):
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Configurar ViewModel de la misma manera que FavoritosFragment (para poder pasar el id del usuario)
        // (podría tener un constructor vacío en el wiewModel, pero da error por la falta de inicialización)
        // usa Factory pero no como clase aparte, sino dentro del propio código de esta clase
        dashboardViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new DashboardViewModel(userId);
            }
        }).get(DashboardViewModel.class);

        /**
         * CREACIÓN DEL ADAPTADOR PARA EL RECYCLER VIEW:
         *  - Crea una nueva instancia de IllustratorAdapter.
         *  - Le pasa una lista vacía (new ArrayList<>()) como datos iniciales.
         *  - Le pasa getParentFragmentManager(), que es el administrador de fragmentos del fragmento padre.
         *
         *  - IllustratorAdapter abre otro Fragment cuando se hace clic en un ilustrador.
         *  - getParentFragmentManager() se usa para manejar esa navegación desde este fragmento a otro.
         *  - Si el adaptador no necesitara abrir otros fragmentos, podríamos omitir este parámetro.
         */
        illustratorAdapter = new IllustratorAdapter(new ArrayList<>(), getParentFragmentManager());
        //Este sería si pasaramos de fragment a activity:
        //illustratorAdapter = new IllustratorAdapter(new ArrayList<>());

        /**
         * CONFIGURACIÓN DEL LAYOUT MANAGER PARA EL RECYCLER VIEW:
         * - Define el tipo de diseño (layout) del RecyclerView:
         *   new LinearLayoutManager(getContext()) significa que los elementos se mostrarán en una lista vertical (scrollable).
         *   // new GridLayoutManager(getContext(), 2); → Para mostrar en rejilla de 2 columnas
         */
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /**
         * ASIGNACIÓN DEL ADAPTADOR AL RECYCLER VIEW:
         *  - Conecta el RecyclerView con IllustratorAdapter, permitiendo que se muestren los datos.
         *  - Sin esta línea, el RecyclerView no sabría qué datos mostrar.
         *  - Asegura que, cuando illustratorAdapter reciba datos nuevos, el RecyclerView pueda actualizarse.
         */
        binding.recyclerView.setAdapter(illustratorAdapter);

        Log.d("DASHBOARD_FRAGMENT", "RecyclerView inicializado.");

        /**
         // Ahora usamos DashboardViewModel en lugar de IllustratorViewModel
         dashboardViewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
         Log.d("DASHBOARD_FRAGMENT", "ViewModel inicializado.");

         dashboardViewModel.getIllustratorLiveData().observe(getViewLifecycleOwner(), illustrators -> {
         Log.d("DASHBOARD_FRAGMENT", "Datos recibidos del ViewModel: " + illustrators.size() + " ilustradores.");
         illustratorAdapter.setIllustrators(illustrators);
         });*/

        // Observamos cambios en la lista de ilustradores
        dashboardViewModel.getIllustratorLiveData().observe(getViewLifecycleOwner(), illustrators -> { //Recibe datos
            illustratorAdapter.setIllustrators(illustrators); //actualiza el adaptador, que está conectado al RV, el cual los mostrará
        });

        return binding.getRoot(); // devuelve la vista cuando usas binding

        //return view; // cuando usas findViewById()
    }
}

