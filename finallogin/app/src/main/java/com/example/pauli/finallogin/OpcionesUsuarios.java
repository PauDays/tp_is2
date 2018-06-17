package com.example.pauli.finallogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OpcionesUsuarios extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_usuarios);
        Button btusuarios, bttareas;
        //Intent intentRespuesta = getIntent();
        //Toast.makeText(this,"Heck yes", Toast.LENGTH_SHORT).show();

                btusuarios = (Button) findViewById(R.id.btusuarios);
                bttareas = (Button) findViewById(R.id.bttareas);
                btusuarios.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        startActivity(new Intent(OpcionesUsuarios.this, ModuloUsuarios.class));
                    }
                });
                bttareas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View V) {
                        startActivity(new Intent(OpcionesUsuarios.this, ModuloTareas.class));
                    }
                });
            }
        }
