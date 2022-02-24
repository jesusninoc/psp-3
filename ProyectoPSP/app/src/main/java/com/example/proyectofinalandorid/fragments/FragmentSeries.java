package com.example.proyectofinalandorid.fragments;



import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinalandorid.R;
import com.example.proyectofinalandorid.adapatadores.AdpatadorListas;
import com.example.proyectofinalandorid.adapatadores.AdpatadorSeries;
import com.example.proyectofinalandorid.utils.Pelicula;
import com.example.proyectofinalandorid.utils.Serie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


 public class FragmentSeries extends Fragment {
    private View view;
    private RecyclerView recyclerViewSeries;
    private AdpatadorSeries adaptadorSeries;
    private AdpatadorListas.OnPeliculaListener listener;

     public void onAttach(Context context) {
         super.onAttach(context);

         try {
             listener = (AdpatadorListas.OnPeliculaListener) context;
         } catch (Exception e) {
             Log.v("Error", "La comunicacion no ha podido llevarse a cabo");
         }
     }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dos,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        instancias();
        asociarDatos();
        realizarPeticion();
    }

    private void realizarPeticion() {
        //String Url="https://api.themoviedb.org/3/list/2?api_key=d3a3e7ce8243cf169b5f1fb4ab4103dc&language=en-US";
        String Url="https://api.themoviedb.org/3/tv/popular?api_key=d3a3e7ce8243cf169b5f1fb4ab4103dc&language=en-US&page=1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                procesarPeticion(jsonObject);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.v("series","error peticion");

            }

        });
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }

    private void procesarPeticion(JSONObject response){
        try {
            JSONArray peliculas=response.getJSONArray("results");
            for (int i = 0; i < peliculas.length(); i++) {
                JSONObject pelicula=peliculas.getJSONObject(i);
                String nombre= pelicula.getString("name");
                String descipcion=pelicula.getString("overview");
                String imagen="https://image.tmdb.org/t/p/w500"+pelicula.getString("poster_path");
                //String imagen= "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg";
                String fechaLanazmineto=pelicula.getString("first_air_date");
                int votos= pelicula.getInt("vote_count");
                int popularidad= pelicula.getInt("popularity");
                Serie serie= new Serie(nombre,descipcion,imagen,fechaLanazmineto,votos,popularidad);
                adaptadorSeries.agregarPelicula(serie);





            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void asociarDatos() {
        recyclerViewSeries.setAdapter(adaptadorSeries);
        recyclerViewSeries.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
    }

    private void instancias() {
        recyclerViewSeries=view.findViewById(R.id.recycler_series);
        adaptadorSeries= new AdpatadorSeries(getContext());
    }
}



