package com.frannzg.temperatura.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.frannzg.temperatura.Temperatura;
import com.frannzg.temperatura.TemperaturaAdapter;
import com.frannzg.temperatura.databinding.FragmentDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private List<Temperatura> temperatures;
    private TemperaturaAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        temperatures = new ArrayList<Temperatura>();


        //Primer agafem la llista de temperatura del firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // Es conecta a la BD
        DatabaseReference myRef = database.getReference("temperatures");

        adapter = new TemperaturaAdapter(temperatures, myRef, getContext());
        binding.rvTemperatura.setAdapter(adapter);
        binding.rvTemperatura.setLayoutManager(new LinearLayoutManager(getContext()));


        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Temperatura temperatura = dataSnapshot.getValue(Temperatura.class);
                    temperatura.setKey(dataSnapshot.getKey());
                    temperatures.add(temperatura);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}