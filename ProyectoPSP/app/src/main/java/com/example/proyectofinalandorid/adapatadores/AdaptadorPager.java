package com.example.proyectofinalandorid.adapatadores;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.proyectofinalandorid.fragments.FragmentPelicula;
import com.example.proyectofinalandorid.fragments.FragmentSeries;

import java.util.ArrayList;

public class AdaptadorPager extends FragmentPagerAdapter {

    ArrayList<Fragment> listaFragments;
    ArrayList<String> listaNombres;
    private String[] nombres = new String[]{"Peliculas","series"};

    public AdaptadorPager(@NonNull FragmentManager fm) {
        super(fm);

        listaFragments = new ArrayList<>();
        listaFragments.add(new FragmentPelicula());
        listaFragments.add(new FragmentSeries());
        listaNombres = new ArrayList<>();
    }


    public AdaptadorPager(@NonNull FragmentManager fm, int behavior, ArrayList<Fragment> listaFragments) {
        super(fm, behavior);
        this.listaFragments =listaFragments;

    }



    public void eliminarFragment(int posicion){
        listaFragments.remove(posicion);
        notifyDataSetChanged();
    }

    public void addFragment(Fragment fragment){
        listaFragments.add(fragment);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listaFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return nombres[position];
    }
}
