package com.example.proyectofinalandorid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofinalandorid.dialogos.DialogoAcceder;
import com.example.proyectofinalandorid.dialogos.DialogoRegistrarse;

public class Login extends AppCompatActivity {

    private Button btnAcceder,btnRegistrarse;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loging);
        instancias();
        acciones();
    }

    private void acciones() {
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoAcceder dialogoAcceder= new DialogoAcceder();
                dialogoAcceder.show(getSupportFragmentManager(),"acceder");

            }
        });


        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoRegistrarse registrarse= new DialogoRegistrarse();
                registrarse.show(getSupportFragmentManager(),"registrarse");


            }
        });
    }

    private void instancias() {
        btnAcceder=findViewById(R.id.btn_acceder);
        btnRegistrarse=findViewById(R.id.btn_registrarse);


    }
}
