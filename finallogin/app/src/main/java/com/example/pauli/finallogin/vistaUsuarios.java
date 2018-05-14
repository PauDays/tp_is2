package com.example.pauli.finallogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class vistaUsuarios extends AppCompatActivity {

    private ListView lv1;
    private String[] opciones = { "Registrar Usuarios", "Ver Usuarios"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_usuarios);
        lv1 =(ListView)findViewById(R.id.menuList);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, opciones);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcion = lv1.getItemAtPosition(position).toString();
                if(opcion.equals("Registrar Usuarios")){
                    Intent intent = new Intent(vistaUsuarios.this,RegistroUsuarios.class);
                    startActivity(intent);
                }
                if(opcion.equals("Ver Usuarios")){
                    Intent intent = new Intent(vistaUsuarios.this,VerUsuarios.class);
                    startActivity(intent);
                }
            }
        });


    }
}
