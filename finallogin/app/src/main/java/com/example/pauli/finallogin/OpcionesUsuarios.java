package com.example.pauli.finallogin;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OpcionesUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_usuarios);
        Button btusuarios, bttareas;
        btusuarios = (Button) findViewById(R.id.btusuarios);
        bttareas = (Button) findViewById(R.id.bttareas);

        Intent nuevoIntent = getIntent();
        final int idUsuario = nuevoIntent.getIntExtra("IDUSER", 0);
        final String rolUsuario = nuevoIntent.getStringExtra("NOMROL");
        final String personaJson=nuevoIntent.getStringExtra("Persona");


        btusuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V)  {
                try {

                    if (rolUsuario.equals("scrummaster")) {
                        startActivity(new Intent(OpcionesUsuarios.this, windowSeeUser.class));
                    } else if (rolUsuario.equals("usuequipo")) {

                        Intent intentusuequipo = new Intent(OpcionesUsuarios.this, windowOnlyModify.class);
                        intentusuequipo.putExtra("idUsuario", idUsuario);
                        intentusuequipo.putExtra("personaJson", personaJson);
                        startActivity(intentusuequipo);

                    }
                }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        });


        bttareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {

                if (rolUsuario.equals("scrummaster")) {
                    startActivity(new Intent(OpcionesUsuarios.this, ModuloTareas.class));
                } else if (rolUsuario.equals("usuequipo")) {
                    Intent intentusuequipo = new Intent(OpcionesUsuarios.this, ModuloTareasByUser.class);
                    intentusuequipo.putExtra("idUsuario", idUsuario);
                    startActivity(intentusuequipo);
                }
            }

        });
    }
}