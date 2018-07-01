package com.example.pauli.finallogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ModuloTareas extends AppCompatActivity implements View.OnClickListener{
    int id_e = -1;
    ListView listView;
    EditText editTextNombre;
    EditText editTextIdUsuarioCreador;
    EditText editTextIdUsuarioEditor;
    EditText editTextIdSprint;
    EditText editTextEstado;
    EditText editTextFecha;
    EditText editTextFechaFin;
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
            System.out.println("*******************************"+expectJson+expectJson.get("idUs"));
            Tareas temTarea = new Tareas();
            temTarea.setIdUS(Integer.parseInt(expectJson.get("idUs").toString()));
            temTarea.setNombreUS(expectJson.get("nombreUs").toString());
            temTarea.setIdUsuarioCreador(expectJson.get("idUserCreador").getAsInt());
            temTarea.setIdUsuarioEditor(expectJson.get("idUserEditor").getAsInt());
            temTarea.setEstado(expectJson.get("estado").getAsString());
            temTarea.setFecha(expectJson.get("fecha").getAsString());
            temTarea.setFechaFin(expectJson.get("fechaFin").getAsString());
            temTarea.setIdSprint(expectJson.get("idSprint").getAsInt());
            arrTareas.add(temTarea);
            System.out.println(expectJson.get("nombreUs").toString());
        }

        myAdapter = new CustomAdapter(this, R.layout.activity_item_tareas, arrTareas);
        listView.setAdapter(myAdapter);
        listView.setHorizontalScrollBarEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_e = position;
                editTextNombre.setText(arrTareas.get(position).getNombreUS());
                editTextIdUsuarioCreador.setText(arrTareas.get(position).getIdUsuarioCreador().toString());
                editTextIdUsuarioEditor.setText(arrTareas.get(position).getIdUsuarioEditor().toString());
                editTextEstado.setText(arrTareas.get(position).getEstado().toString());
                editTextFecha.setText(arrTareas.get(position).getFecha());
                editTextFechaFin.setText(arrTareas.get(position).getFechaFin());
                editTextIdSprint.setText(arrTareas.get(position).getIdSprint().toString());

            }
        });

        /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                id_e=position;
                Toast.makeText(ModuloTareas.this, "Eliminado", Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/


    }

    public void getTareas(){
        listView = (ListView) findViewById(R.id.list_tareas);
        editTextNombre = (EditText) findViewById(R.id.edit_nombre_tarea);
        editTextIdUsuarioCreador = (EditText) findViewById(R.id.edit_usuario_creador);
        editTextIdUsuarioEditor = (EditText) findViewById(R.id.edit_usuario_editor);
        editTextEstado = (EditText) findViewById(R.id.edit_estado);
        editTextFecha = (EditText) findViewById(R.id.edit_fecha);
        editTextFechaFin = (EditText) findViewById(R.id.edit_fecha_fin);
        editTextIdSprint = (EditText) findViewById(R.id.edit_id_sprint);
        buttonInsertar = (Button) findViewById(R.id.btn_insertar);
        buttonEditar = (Button) findViewById(R.id.btn_editar);
        buttonEliminar = (Button) findViewById(R.id.btn_eliminar);
        buttonInsertar.setOnClickListener(this);
        buttonEditar.setOnClickListener(this);
        buttonEliminar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_insertar:
                System.out.println("Entra onClic");
                //Toast.makeText(this, "Clic en insertar", Toast.LENGTH_SHORT).show();
                Toast.makeText(ModuloTareas.this, "Iniciando registro", Toast.LENGTH_SHORT).show();
                try {
                    executePOST();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_editar:
                Tareas tem = new Tareas();
                tem.setNombreUS(editTextNombre.getText().toString());
                tem.setIdUsuarioEditor(Integer.parseInt(editTextIdUsuarioCreador.getText().toString()));
                tem.setIdUsuarioCreador(Integer.parseInt(editTextIdUsuarioEditor.getText().toString()));
                //System.out.println("****************Fecha"+editTextFecha.getText());
                tem.setEstado(editTextEstado.getText().toString());
                tem.setFecha(editTextFecha.getText().toString());
                tem.setFechaFin(editTextFechaFin.getText().toString());
                tem.setIdSprint(Integer.parseInt(editTextIdSprint.getText().toString()));

                System.out.println("****************Id: "+ id_e );
                arrTareas.set(id_e, tem);
                try {
                    executePUT(tem, id_e);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                id_e = -1;

                myAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_eliminar:
                Tareas tem2 = new Tareas();
                tem2.setNombreUS(arrTareas.get(id_e).getNombreUS());
                tem2.setIdUsuarioCreador(arrTareas.get(id_e).getIdUsuarioCreador());
                tem2.setIdUsuarioEditor(arrTareas.get(id_e).getIdUsuarioEditor());
                tem2.setIdSprint(arrTareas.get(id_e).getIdSprint());
                tem2.setFecha(arrTareas.get(id_e).getFecha());
                tem2.setFechaFin(arrTareas.get(id_e).getFechaFin());
                tem2.setEstado(arrTareas.get(id_e).getEstado());
                arrTareas.remove(id_e);


                try {
                    executePUT(tem2, arrTareas.get(id_e).getIdSprint());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                id_e = -1;
                myAdapter.notifyDataSetChanged();
                Toast.makeText(ModuloTareas.this, "Eliminado", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public String executeGET() {
        int timeout=15000;
        String url;
        HttpURLConnection connection = null;

        try {
            url = "http://192.168.0.13:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/getTareas? ";

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
            System.out.println("--------------------------*****************************doIn");
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;

    }

    private void executePOST() throws IOException {
        Tareas tem = new Tareas();
        tem.setNombreUS(editTextNombre.getText().toString().trim());
        tem.setIdUsuarioCreador(Integer.parseInt(editTextIdUsuarioCreador.getText().toString().trim()));
        tem.setIdUsuarioEditor(Integer.parseInt(editTextIdUsuarioEditor.getText().toString().trim()));
        tem.setIdSprint(Integer.parseInt(editTextIdSprint.getText().toString().trim()));
        tem.setFecha(editTextFecha.getText().toString().trim());
        tem.setFechaFin(editTextFechaFin.getText().toString().trim());
        tem.setEstado(editTextEstado.getText().toString().trim());
        arrTareas.add(tem);
        String spockAsJson = new Gson().toJson(tem);


        URL url = new URL("http://192.168.0.13:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/addTarea");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //constants

        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        //conn.setRequestProperty("Content-Type", "application/json");
        System.out.println("***********************************************"+spockAsJson);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //os.write(json.getBytes("UTF-8"));

        OutputStream os = conn.getOutputStream();
        os.write(spockAsJson.getBytes("UTF-8"));
        os.flush();
        System.out.println("***********************************************"+conn.getResponseMessage());


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println("**********************************************************"+output);
        }

        conn.disconnect();


    }

    private void executePUT(Tareas tem, int id) throws IOException {

        String spockAsJson = new Gson().toJson(tem);


        //constants
        URL url = new URL("http://192.168.0.13:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.sprints/editarTarea/"+id+"?");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        //conn.setRequestProperty("Content-Type", "application/json");
        System.out.println("***********************************************"+spockAsJson);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //os.write(json.getBytes("UTF-8"));

        OutputStream os = conn.getOutputStream();
        os.write(spockAsJson.getBytes("UTF-8"));
        os.flush();
        System.out.println("***********************************************"+conn.getResponseMessage());


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println("**********************************************************"+output);
        }

        conn.disconnect();


    }

    private void executeDELETE(Tareas tem, int id) throws IOException {

        String spockAsJson = new Gson().toJson(tem);


        //constants
        URL url = new URL("http://192.168.0.13:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.sprints/editarTarea/"+id+"?");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        //conn.setRequestProperty("Content-Type", "application/json");
        System.out.println("***********************************************"+spockAsJson);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //os.write(json.getBytes("UTF-8"));

        OutputStream os = conn.getOutputStream();
        os.write(spockAsJson.getBytes("UTF-8"));
        os.flush();
        System.out.println("***********************************************"+conn.getResponseMessage());


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println("**********************************************************"+output);
        }

        conn.disconnect();


    }
}
