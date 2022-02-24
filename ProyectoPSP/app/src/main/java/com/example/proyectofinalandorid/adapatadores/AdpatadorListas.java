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

import java.util.ArrayList;

public class AdpatadorListas extends RecyclerView.Adapter<AdpatadorListas.MyHolder>  {
         private ArrayList<Pelicula> listaItems;
         private Context context;
         private  OnPeliculaListener listener;

    public AdpatadorListas( Context context) {
        this.listaItems = new ArrayList<>();
        this.context = context;
        this.listener=(OnPeliculaListener) context;

    }




    public void agregarPelicula(Pelicula pelicula){
        this.listaItems.add(pelicula);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.item_lista,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Pelicula peliculaActual=listaItems.get(position);
        holder.nombre.setText(peliculaActual.getNombre());
        holder.descipcion.setText(peliculaActual.getDescripcion());
        Glide.with(context).load(peliculaActual.getImagen()).into(holder.imagenItem);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPeliculaSelected(peliculaActual);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }


    public interface OnPeliculaListener{

        void onPeliculaSelected(Pelicula pelicula);
    }
    class MyHolder extends RecyclerView.ViewHolder{

        TextView nombre,descipcion;
        ImageView imagenItem;
        LinearLayout linearLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombre_item);
            descipcion=itemView.findViewById(R.id.descicion_item);
            imagenItem=itemView.findViewById(R.id.imagen_item);
            linearLayout=itemView.findViewById(R.id.fila_item);
        }
    }
}
