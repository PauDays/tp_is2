package com.example.pauli.finallogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModuloUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_usuarios);
        Button btCrearUsu, btEditarUsu, btEliminarUsu, btVerUsu;

        //btCrearUsu = (Button) findViewById(R.id.btCrearUsuario);
        btEditarUsu=(Button)findViewById(R.id.btEditarUsuario);
       // btEliminarUsu=(Button)findViewById(R.id.btEliminarUsuario);
        btVerUsu=(Button)findViewById(R.id.btVerUsuario);

       /* btCrearUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(new Intent(ModuloUsuarios.this, VentanaCrearUsu.class));
            }
        });*/

        btEditarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(new Intent(ModuloUsuarios.this, windowSeeUser.class));
            }
        });

       /* btEliminarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(new Intent(ModuloUsuarios.this, VentanaEliminarUsu.class));
            }
        });*/

        btVerUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                startActivity(new Intent(ModuloUsuarios.this, VentanaVerUsuario.class));
            }
        });

    }
}
