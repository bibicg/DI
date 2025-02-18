package com.example.android_firebase_2.repositories;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.android_firebase_2.models.Illustrator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

// antes no tenia esta clase, intentando seguir MVVM
public class DashboardRepository {
    /**
    private final DatabaseReference databaseReference;

    public DashboardRepository() {
        // los datos que va a coger de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("illustrators");
    }

    // este metodo obtiene los ilustradores y actualiza el LiveData en el ViewModel
    public void getIllustrators(MutableLiveData<List<Illustrator>> illustratorLiveData) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Illustrator> illustratorList = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Illustrator illustrator = data.getValue(Illustrator.class);
                    if (illustrator != null) {
                        illustratorList.add(illustrator);
                    }
                }
                illustratorLiveData.setValue(illustratorList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DashboardRepository", "Error al obtener los ilustradores", error.toException());
            }
        });
    }*/
}

