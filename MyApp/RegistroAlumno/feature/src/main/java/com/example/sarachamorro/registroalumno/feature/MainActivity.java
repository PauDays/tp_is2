package com.example.sarachamorro.registroalumno.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    private String[] opciones = { "Registrar Usuario", "Ver Usuarios"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 =(ListView)findViewById(R.id.menuList);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, opciones);
        lv1.setAdapter(adapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcion = lv1.getItemAtPosition(position).toString();
                if(opcion.equals("Registrar Alumno")){
                    Intent intent = new Intent(MainActivity.this,RegistroUsuariosActivity.class);
                    startActivity(intent);
                }
                if(opcion.equals("Ver Alumnos")){
                    Intent intent = new Intent(MainActivity.this,VerUsuariosActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
