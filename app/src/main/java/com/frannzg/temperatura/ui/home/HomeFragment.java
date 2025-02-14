package com.frannzg.temperatura.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.frannzg.temperatura.Temperatura;
import com.frannzg.temperatura.databinding.FragmentHomeBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        binding.btSave.setOnClickListener(this);



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == binding.btSave.getId()){

            Temperatura temperatura = new Temperatura();
            temperatura.setCel(binding.etCel.getText().toString());
            temperatura.setData(binding.etData.getText().toString());
            temperatura.setHumitat(Integer.parseInt(binding.etHumitat.getText().toString()));
            temperatura.setTemperatura(Double.parseDouble(binding.etTemperatura.getText().toString()));



            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("temperatures");

            //myRef.setValue(temperatura);

            //myRef.child('temperatures').push().setValue(temperatura);

            for(int i = 1; i < 20; i++){
               myRef.push().setValue(temperatura);


            }
        }
}
}