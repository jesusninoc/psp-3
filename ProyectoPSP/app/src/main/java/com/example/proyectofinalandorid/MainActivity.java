package com.example.proyectofinalandorid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinalandorid.adapatadores.AdaptadorPager;
import com.example.proyectofinalandorid.adapatadores.AdpatadorListas;
import com.example.proyectofinalandorid.adapatadores.AdpatadorSeries;
import com.example.proyectofinalandorid.dialogos.DialogoRegistrarse;
import com.example.proyectofinalandorid.fragments.FragmentDetalle;
import com.example.proyectofinalandorid.fragments.FragmentPelicula;
import com.example.proyectofinalandorid.fragments.FragmentSeries;
import com.example.proyectofinalandorid.utils.Pelicula;
import com.example.proyectofinalandorid.utils.Serie;
import com.example.proyectofinalandorid.utils.Usuario;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdpatadorListas.OnPeliculaListener, AdpatadorSeries.OnSerieListener, DialogoRegistrarse.OnUsuarioListner {
    private static Usuario usuario;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Usuario usuarioRecuperado;

    private Toolbar toolbar;
    private Button boton;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView textViewCorreo,textViewNombre;
    private Spinner spinner;
    private FirebaseAuth firebaseAuth;
    private ViewPager viewPager;
    private AdaptadorPager adaptadorPager;
    private TabLayout tabs;
    private ArrayList listaFG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        iniciarPager();
        //configurarPager();
        //configurarHeader();
        acciones();
        tabs.setBackgroundColor(getResources().getColor(R.color.white));
        //viewPager.setCurrentItem(0);





    }

    private void configurarHeader() {
        textViewNombre.setText(usuarioRecuperado.getNombre());
        textViewCorreo.setText(usuarioRecuperado.getCorreo());
    }

    private void iniciarPager() {
        listaFG = new ArrayList();
        listaFG.add(new FragmentPelicula());
        listaFG.add(new FragmentSeries());

        adaptadorPager = new AdaptadorPager(getSupportFragmentManager(),0,listaFG);
        viewPager.setAdapter(adaptadorPager);

    }

    private void configurarPager() {
        viewPager.setAdapter(adaptadorPager);
    }

    private void acciones() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
                    case R.id.menu_nav_f1:

                        fragmentTransaction.replace(R.id.sitio_fragment,new FragmentPelicula());
                        fragmentTransaction.commit();




                        break;
                    case R.id.menu_nav_f2:
                        fragmentTransaction.replace(R.id.sitio_fragment,new FragmentSeries());
                        fragmentTransaction.commit();
                        break;

                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Fragment fragment = adaptadorPager.getItem(position);
                View view = fragment.getView();

                LinearLayout linearLayout = view.findViewById(R.id.linear_fragment);

                int color = ((ColorDrawable)linearLayout.getBackground()).getColor();
                tabs.setBackgroundColor(color);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    private void configurarToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("The Movie DB MobileApp");
        tabs.setupWithViewPager(viewPager);
    }

    private void instancias() {
        adaptadorPager = new AdaptadorPager(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.toolbar);
        textViewNombre = findViewById(R.id.nombre_header_nav);
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        textViewCorreo = navigationView.getHeaderView(0).findViewById(R.id.correo_header_nav);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sitio_fragment,new FragmentPelicula());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        tabs = findViewById(R.id.tab);


        configurarToolbar();
       ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                0,
                0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pref, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_pref_1:
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);

                break;
            case R.id.menu_pref_2:
                System.exit(0);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (drawerLayout.isOpen()){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            if (getSupportFragmentManager().getBackStackEntryCount()==0){
                finish();
            } else {
                super.onBackPressed();
            }

        }
    }

    @Override
    public void onPeliculaSelected(Pelicula pelicula) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sitio_fragment, FragmentDetalle.newInstance(pelicula));
        fragmentTransaction.addToBackStack("fdetalle");
        fragmentTransaction.commit();
    }


    @Override
    public void onSerieSelected(Serie serie) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sitio_fragment, FragmentDetalle.newInstance(serie));

        fragmentTransaction.addToBackStack("fdetalle");
        fragmentTransaction.commit();

    }

    @Override
    public void onUsurioRegister(Usuario usuario) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.sitio_fragment, FragmentPelicula.newInstance(usuario));
        fragmentTransaction.addToBackStack("fdetalle");
        fragmentTransaction.commit();
    }


}