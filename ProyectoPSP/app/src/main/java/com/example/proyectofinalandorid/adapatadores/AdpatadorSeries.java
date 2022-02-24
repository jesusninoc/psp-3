package com.example.proyectofinalandorid.adapatadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectofinalandorid.R;
import com.example.proyectofinalandorid.utils.Pelicula;
import com.example.proyectofinalandorid.utils.Serie;

import java.util.ArrayList;

public class AdpatadorSeries extends RecyclerView.Adapter<AdpatadorSeries.MyHolder>{
    private ArrayList<Serie> listaSeries;
    private Context context;
    private  OnSerieListener listener;

    public AdpatadorSeries(Context context) {
        this.listaSeries = new ArrayList<>();
        this.context = context;
        this.listener = (OnSerieListener) context;
    }



    public void agregarPelicula(Serie serie){
        this.listaSeries.add(serie);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_lista2,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Serie serieActual=listaSeries.get(position);
        holder.nombre.setText(serieActual.getNombre());
        holder.descipcion.setText(serieActual.getDescripcion());
        Glide.with(context).load(serieActual.getImagen()).into(holder.imagenItem);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSerieSelected(serieActual);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaSeries.size();
    }


    public interface OnSerieListener{
        void onSerieSelected(Serie serie);
    }
    class MyHolder extends RecyclerView.ViewHolder{

        TextView nombre,descipcion;
        ImageView imagenItem;
        LinearLayout linearLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombre_item2);
            descipcion=itemView.findViewById(R.id.descicion_item2);
            imagenItem=itemView.findViewById(R.id.imagen_item2);
            linearLayout=itemView.findViewById(R.id.fila_item2);
        }
    }
}