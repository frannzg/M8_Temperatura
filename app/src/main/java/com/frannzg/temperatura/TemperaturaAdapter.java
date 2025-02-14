package com.frannzg.temperatura;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class TemperaturaAdapter extends RecyclerView.Adapter<TemperaturaAdapter.ViewHolder> {
    private List<Temperatura> temperatures;
    private Context context;
    private DatabaseReference myRef;

    public TemperaturaAdapter(List<Temperatura> temperatures, DatabaseReference myRef, Context context) {
        this.temperatures = temperatures;
        this.myRef = myRef;
        this.context = context;
    }

    @NonNull
    @Override
    public TemperaturaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_temperatura, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemperaturaAdapter.ViewHolder holder, int position) {
        Temperatura temperatura = temperatures.get(position);
        holder.tvCel.setText(temperatura.getCel());
        holder.tvData.setText(temperatura.getData());
        holder.tvHumitat.setText(String.valueOf(temperatura.getHumitat()));
        holder.tvTemperatura.setText(String.valueOf(temperatura.getTemperatura()));

        holder.btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double temp = temperatura.getTemperatura();
                temp -= 1;
                myRef.child(temperatura.getKey()).child("temperatura").setValue(temp);
                temperatura.setTemperatura(temp); // Actualizar localmente
                holder.tvTemperatura.setText(String.valueOf(temp)); // Actualizar la vista
            }
        });

        holder.btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double temp = temperatura.getTemperatura();
                temp += 1;
                myRef.child(temperatura.getKey()).child("temperatura").setValue(temp);
                temperatura.setTemperatura(temp); // Actualizar localmente
                holder.tvTemperatura.setText(String.valueOf(temp)); // Actualizar la vista
            }
        });
    }

    @Override
    public int getItemCount() {
        return temperatures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCel;
        public TextView tvData;
        public TextView tvHumitat;
        public TextView tvTemperatura;
        public Button btPlus;
        public Button btMinus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCel = itemView.findViewById(R.id.tv_cel_item_temperatura);
            tvData = itemView.findViewById(R.id.tv_data_item_temperatura);
            tvHumitat = itemView.findViewById(R.id.tv_humitat_item_temperatura);
            tvTemperatura = itemView.findViewById(R.id.tv_temperatura_item_temperatura);
            btPlus = itemView.findViewById(R.id.bt_plus);
            btMinus = itemView.findViewById(R.id.bt_minus);
        }
    }
}
