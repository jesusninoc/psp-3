package com.example.proyectofinalandorid.dialogos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.proyectofinalandorid.MainActivity;
import com.example.proyectofinalandorid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DialogoAcceder extends DialogFragment {

    private Context context;
    private View view;
    private Button btnLogin;
    private EditText editUser,editPass;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private TextView nombreHeader;
    private TextView correoHeader;
    private NavigationView navigationView;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertdialog =new AlertDialog.Builder(getActivity());
        view= LayoutInflater.from(context).inflate(R.layout.dialogo_accerder,null,false);
        alertdialog.setView(view);
        instancias();
        acciones();



        return alertdialog.create();
    }

    private void acciones() {
btnLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        firebaseAuth.signInWithEmailAndPassword(editUser.getText().toString(),editPass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getContext(), "Login Correcto", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(getContext(), "Login Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
});
    }

    private void instancias() {
        btnLogin=view.findViewById(R.id.boton_login);
        editUser=view.findViewById(R.id.edit_usuario);
        editPass=view.findViewById(R.id.edit_pass);
        firebaseAuth=FirebaseAuth.getInstance();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;

    }
}
