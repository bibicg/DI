package com.example.android_firebase_2.views;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.android_firebase_2.R;
import com.example.android_firebase_2.databinding.FragmentDashboardBinding;
import com.example.android_firebase_2.viewmodels.DashboardViewModel;
import java.util.ArrayList;


/**
 * !!!Actualizacion de DashboardFragment para que use DashboardViewModel en lugar de IllustratorViewModel.
 *
 *  Ahora los datos de los ilustradores se cargan desde DashboardViewModel.
 *  Mantiene el RecyclerView y el Adapter funcionando correctamente.
 *  Elimina la dependencia de IllustratorViewModel, asegurando que DashboardFragment solo obtenga datos de su ViewModel.
 */

/**
 * Se usa DataBinding pero de una manera más indirecta que en RandomFragment. Estás obteniendo una instancia del binding usando
 * FragmentDashboardBinding.bind(view), lo cual asocia el layout XML (fragment_dashboard.xml) con el fragmento. Esto permite acceder
 * a las vistas declaradas en el XML a través de binding.
 *
 * Luego, lo usas para:
 * - Configurar el RecyclerView
 * - Observar cambios en el ViewModel
 *
 * Si quisieras usar Data Binding completamente, podrías cambiar la forma en que inflas la vista:
 * FragmentDashboardBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
 *
 * Y luego establecer el ViewModel en el binding:
 * binding.setViewModel(dashboardViewModel);
 * binding.setLifecycleOwner(getViewLifecycleOwner());
 */

public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;
    private IllustratorAdapter illustratorAdapter;

    public DashboardFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("DASHBOARD_FRAGMENT", "DashboardFragment se está creando...");

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        FragmentDashboardBinding binding = FragmentDashboardBinding.bind(view);

        //Es necesairo que el constructor del adapter tenga en paramentros el Fragment
        //pq estamos pasando de un fragment a otro fragment
        illustratorAdapter = new IllustratorAdapter(new ArrayList<>(), getParentFragmentManager());
        //Este sería si pasaramos de fragment a activity:
        //illustratorAdapter = new IllustratorAdapter(new ArrayList<>());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(illustratorAdapter);

        Log.d("DASHBOARD_FRAGMENT", "RecyclerView inicializado.");

        // Ahora usamos DashboardViewModel en lugar de IllustratorViewModel
        dashboardViewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
        Log.d("DASHBOARD_FRAGMENT", "ViewModel inicializado.");

        dashboardViewModel.getIllustratorLiveData().observe(getViewLifecycleOwner(), illustrators -> {
            Log.d("DASHBOARD_FRAGMENT", "Datos recibidos del ViewModel: " + illustrators.size() + " ilustradores.");
            illustratorAdapter.setIllustrators(illustrators);
        });

        return view;
    }
}

/**
 * DashboardFragment usando completamente DataBinding
 */
/**
public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;
    private IllustratorAdapter illustratorAdapter;
    private FragmentDashboardBinding binding;

    public DashboardFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("DASHBOARD_FRAGMENT", "DashboardFragment se está creando...");

        // Usamos DataBinding para inflar la vista
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);

        // Inicializar ViewModel
        dashboardViewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        // Inicializar el Adapter
        illustratorAdapter = new IllustratorAdapter(new ArrayList<>(), getParentFragmentManager());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(illustratorAdapter);

        Log.d("DASHBOARD_FRAGMENT", "RecyclerView inicializado.");
        Log.d("DASHBOARD_FRAGMENT", "ViewModel inicializado.");

        // Observar cambios en el ViewModel y actualizar el Adapter
        dashboardViewModel.getIllustratorLiveData().observe(getViewLifecycleOwner(), illustrators -> {
            Log.d("DASHBOARD_FRAGMENT", "Datos recibidos del ViewModel: " + illustrators.size() + " ilustradores.");
            illustratorAdapter.setIllustrators(illustrators);
        });

        return binding.getRoot();
    }
}*/

/**
 * Si además de usar por completo dataBinging, quiero usar también el viewModel
 * para no tener que hacer una observacion manual
 */
/**
public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;
    private IllustratorAdapter illustratorAdapter;
    private FragmentDashboardBinding binding;

    public DashboardFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("DASHBOARD_FRAGMENT", "DashboardFragment se está creando...");

        // Usamos DataBinding para inflar la vista
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);

        // Inicializar ViewModel y enlazarlo con el binding
        dashboardViewModel = new ViewModelProvider(requireActivity()).get(DashboardViewModel.class);
        binding.setViewModel(dashboardViewModel); //esto así no funciona, debo vinvular el viewmodel en el cml:
        /**
         * <data>
         *         <variable
         *             name="viewModel"
         *             type="com.example.android_firebase_2.viewmodels.DashboardViewModel"/>
         *     </data>
         */
/**        binding.setLifecycleOwner(getViewLifecycleOwner());

        // Inicializar el Adapter
        illustratorAdapter = new IllustratorAdapter(new ArrayList<>(), getParentFragmentManager());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(illustratorAdapter);

        Log.d("DASHBOARD_FRAGMENT", "RecyclerView inicializado.");
        Log.d("DASHBOARD_FRAGMENT", "ViewModel inicializado y enlazado al Binding.");

        // Observar cambios en el ViewModel y actualizar el Adapter
        dashboardViewModel.getIllustratorLiveData().observe(getViewLifecycleOwner(), illustrators -> {
            Log.d("DASHBOARD_FRAGMENT", "Datos recibidos del ViewModel: " + illustrators.size() + " ilustradores.");
            illustratorAdapter.setIllustrators(illustrators);
        });

        return binding.getRoot();
    }
}*/