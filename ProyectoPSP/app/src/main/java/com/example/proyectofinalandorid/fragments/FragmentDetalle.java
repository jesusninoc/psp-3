package com.example.proyectofinalandorid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.proyectofinalandorid.R;
import com.example.proyectofinalandorid.utils.Pelicula;


public class FragmentDetalle extends Fragment {

    private View view;
    private ImageView imagen;
    private TextView nombre,fecha,descipcion,valoracion;
    private Pelicula peliculaRecuperda;


    public static FragmentDetalle newInstance(Pelicula pelicula){
        Bundle args =new Bundle();
        args.putSerializable("pelicula",pelicula);
        FragmentDetalle fragment = new FragmentDetalle();
        fragment.setArguments(args);
        return fragment;


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(this.getArguments() !=null){
            peliculaRecuperda =(Pelicula) this.getArguments().getSerializable("pelicula");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detalle,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        instancias();
        iniciarVista();
    }

    private void iniciarVista() {
        nombre.setText("Nombre:"+peliculaRecuperda.getNombre());
        descipcion.setText(peliculaRecuperda.getDescripcion());
        fecha.setText("Fecha de lanzamineto: "+peliculaRecuperda.getFechaDeLanzamineto());
        Glide.with(getContext()).load(peliculaRecuperda.getImagen()).into(imagen);
        String valoracionS= String.valueOf(peliculaRecuperda.getVotos());
        valoracion.setText("Valoracion: "+valoracionS);

    }

    private void instancias() {
        imagen=view.findViewById(R.id.imagen_detalle);
        nombre=view.findViewById(R.id.nombre_detalle);
        descipcion=view.findViewById(R.id.descipcion_detalle);
        fecha=view.findViewById(R.id.fecha_detalle);
        valoracion=view.findViewById(R.id.valoracion);

    }
}