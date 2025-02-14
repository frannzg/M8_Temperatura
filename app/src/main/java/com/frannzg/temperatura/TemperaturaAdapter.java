package com.frannzg.temperatura;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TemperaturaAdapter extends RecyclerView.Adapter<TemperaturaAdapter.ViewHolder> {
    private List<Temperatura> temperatures;
    private Context context;


    public TemperaturaAdapter(List<Temperatura> temperatures, Context context){
        this.temperatures = temperatures;
        this.context = context;
    }

    @NonNull
    @Override
    public TemperaturaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_temperatura, parent, true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemperaturaAdapter.ViewHolder holder, int position) {

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
            tvCel= itemView.findViewById(R.id.tv_cel);
            tvData = itemView.findViewById(R.id.tv_data);
            tvHumitat = itemView.findViewById(R.id.tv_humitat);
            tvTemperatura = itemView.findViewById(R.id.tv_temperatura);
            btPlus = itemView.findViewById(R.id.bt_plus);
            btMinus = itemView.findViewById(R.id.bt_minus);
        }
    }
}
