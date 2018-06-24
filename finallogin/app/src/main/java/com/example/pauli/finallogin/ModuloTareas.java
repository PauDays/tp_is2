package com.example.pauli.finallogin;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModuloTareas extends AppCompatActivity implements View.OnClickListener{

    ListView listView;
    EditText editTextNombre;
    EditText editTextDuracion;
    EditText editTextIdUsuario;
    EditText editTextFecha;
    Button buttonInsertar, buttonEditar, buttonEliminar;
    ArrayList<Tareas> arrTareas, pruebas;
    CustomAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        getTareas();
        listView = (ListView) findViewById(R.id.list_tareas);
        arrTareas = new ArrayList<Tareas>();
        String resultado = executeGET();
        Gson gson = new Gson();
        System.out.print(resultado);
        JsonArray task = gson.fromJson(resultado, JsonArray.class);
        for (int i = 0; i <task.size (); i ++) {
            JsonObject expectJson = task.get(i).getAsJsonObject ();
            Tareas temTarea = new Tareas();
            arrTareas.add(new Tareas(expectJson.get("idSprint").getAsInt(),expectJson.get("duracion").getAsInt(),
                    expectJson.get("nombreSprint").getAsString(), expectJson.get("fecha").getAsString(),
                     expectJson.get("idUsuario").getAsInt()));
            System.out.println(expectJson.get("nombreSprint").toString());
         }

        myAdapter = new CustomAdapter(this, R.layout.activity_item_tareas, arrTareas);
        listView.setAdapter(myAdapter);
        listView.setHorizontalScrollBarEnabled(true);


    }

    public void getTareas(){
        listView = (ListView) findViewById(R.id.list_tareas);
        editTextNombre = (EditText) findViewById(R.id.edit_nombre_tarea);
        editTextDuracion = (EditText) findViewById(R.id.edit_duracion);
        editTextIdUsuario = (EditText) findViewById(R.id.edit_duracion);
        editTextFecha = (EditText) findViewById(R.id.edit_duracion);
        buttonInsertar = (Button) findViewById(R.id.btn_insertar);
        buttonEditar = (Button) findViewById(R.id.btn_editar);
        buttonEliminar = (Button) findViewById(R.id.btn_eliminar);
//        button1.setOnClickListener(this);
       // (Button)loginDialog.findViewById(R.id.Submit)
        //button2.setOnClickListener(this);
       // button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    public String executeGET() {
        int timeout=15000;
        String url;
        HttpURLConnection connection = null;

        try {
            url = "http://192.168.0.11:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.sprints/getTareas?";

            URL loginUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) loginUrl.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            if (con.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + con.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (con.getInputStream())));

            String output = br.readLine();
            System.out.println("Output from Server .... \n");
            while (br.readLine() != null) {
                output=br.readLine();
            }
            System.out.println(output);
            //System.out.print
            return output.toString();

        } catch (Exception e) {
            Toast.makeText(this.getBaseContext(),"Error de conexión $e.message",Toast.LENGTH_SHORT).show();
            //Toast.makeText(this , "Error de conexión $e.message", 10).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;

    }

}
