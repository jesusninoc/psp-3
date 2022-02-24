package com.example.proyectofinalandorid.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyectofinalandorid.Login;
import com.example.proyectofinalandorid.MainActivity;
import com.example.proyectofinalandorid.R;
import com.example.proyectofinalandorid.utils.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogoRegistrarse extends DialogoAcceder {
 private Context context;
 private Button btn_registrarse;
 private EditText editNombre,editApellido,editContraseña,editCorreo;
 private View view;
 private FirebaseAuth firebaseAuth;
 private FirebaseDatabase database;
 private TextView nombreHeader,correoHeader;
 private NavigationView navigationView;
 private TextView textView;
 private OnUsuarioListner listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(getActivity());
        view= LayoutInflater.from(context).inflate(R.layout.dialogo_registro,null,false);
        dialogo.setView(view);
        instancias();
        acciones();


        return dialogo.create();

    }
    public void acciones(){
        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo=editCorreo.getText().toString();
                String contraseña=editContraseña.getText().toString();
                String nombre = editNombre.getText().toString();
                String apellido=editApellido.getText().toString();
                Usuario usuario=new Usuario(nombre,apellido);
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();

                DatabaseReference reference = database.getReference("Usuarios/" + currentUser.getUid());
                reference.setValue(usuario);

                firebaseAuth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); user.sendEmailVerification();

                           /* nombreHeader = navigationView.getHeaderView(0).findViewById(R.id.letra_header_nav);
                            nombreHeader.setText(usuario.getNombre());
                            correoHeader=navigationView.getHeaderView(1).findViewById(R.id.correo_header_nav);
                            correoHeader.setText(usuario.getCorreo());*/

                            Toast.makeText(getContext(), "Usuario Registrado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            //listener.onUsurioRegister(usuario);
                            getActivity().finish();


                        }else {
                            //Toast.makeText(getContext(), "Error al registrar usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
    private void instancias(){
        btn_registrarse=view.findViewById(R.id.boton_registrarse);
        editNombre=view.findViewById(R.id.edit_nombre_registro);
        editApellido=view.findViewById(R.id.edit_apellido_registro);
        editCorreo=view.findViewById(R.id.edit_correo_registro);
        editContraseña=view.findViewById(R.id.edit_pass_registro);
        firebaseAuth= FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance("https://iniciofb-9599d-default-rtdb.europe-west1.firebasedatabase.app/");
        //listener=(OnUsuarioListner) context;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    public interface OnUsuarioListner{
        void onUsurioRegister(Usuario usuario);

    }
}
