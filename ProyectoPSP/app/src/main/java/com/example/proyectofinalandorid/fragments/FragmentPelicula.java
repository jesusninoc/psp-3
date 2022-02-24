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
import com.example.proyectofinalandorid.utils.Pelicula;
import com.example.proyectofinalandorid.utils.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentPelicula extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private AdpatadorListas adaptador;
    private AdpatadorListas.OnPeliculaListener listener;

    @Override
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
         view = inflater.inflate(R.layout.fragment_uno,container,false);
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
        String Url="https://api.themoviedb.org/3/movie/now_playing?api_key=4ef66e12cddbb8fe9d4fd03ac9632f6e&language=en-US&page=1%22";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(1, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                procesarPeticion(jsonObject);
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.v("peliculas","error peticion");

            }

        });
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }

    private void procesarPeticion(JSONObject response){
        try {
            JSONArray peliculas=response.getJSONArray("results");
            for (int i = 0; i < peliculas.length(); i++) {
                JSONObject pelicula=peliculas.getJSONObject(i);
                String nombre= pelicula.getString("original_title");
                String descipcion=pelicula.getString("overview");
                String imagen="https://image.tmdb.org/t/p/w500"+pelicula.getString("poster_path");
                //String imagen= "https://image.tmdb.org/t/p/w500/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg";
                String fechaLanazmineto=pelicula.getString("release_date");
                int votos= pelicula.getInt("vote_average");
                Pelicula pelicula1= new Pelicula(nombre,descipcion,imagen,fechaLanazmineto,votos);
                adaptador.agregarPelicula(pelicula1);
                Log.v("peliculas",peliculas.getString(0));
                Log.v("peliculas",pelicula.getString("original_title"));
                Log.v("peliculas",pelicula1.toString());
                System.out.println(peliculas);
                System.out.println(pelicula);
                System.out.println(pelicula1);




            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void asociarDatos() {
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
    }

    private void instancias() {
        recyclerView=view.findViewById(R.id.recycler);
        adaptador= new AdpatadorListas(getContext());
    }
    public static FragmentPelicula newInstance(Usuario usuario) {

        Bundle args = new Bundle();
        args.putSerializable("usuario",usuario);
        FragmentPelicula fragment = new FragmentPelicula();
        fragment.setArguments(args);
        return fragment;
    }
}

