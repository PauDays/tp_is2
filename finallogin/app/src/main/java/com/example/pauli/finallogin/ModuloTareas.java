package com.example.pauli.finallogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


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
    String mensaje = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tareas);
        getTareas();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date hoy = Calendar.getInstance().getTime();

        Date fechaFinal= null;

        android.content.Intent in = getIntent();
        int us = in.getIntExtra("ID_USER", 0);
        String usRol = in.getStringExtra("NOM_ROL");
        System.out.println("+++++++++++++++++++++++++++++++++++++"+us);
        listView = (ListView) findViewById(R.id.list_tareas);
        arrTareas = new ArrayList<Tareas>();
        String resultado = executeGET();
        Gson gson = new Gson();
        JsonArray task = gson.fromJson(resultado, JsonArray.class);
        for (int i = 0; i <task.size (); i ++) {
            JsonObject expectJson = task.get(i).getAsJsonObject ();
            //System.out.println("*******************************"+expectJson+expectJson.get("idUs"));
            Tareas temTarea = new Tareas();
            temTarea.setIdUS(Integer.parseInt(expectJson.get("idUs").toString()));
            temTarea.setNombreUs(expectJson.get("nombreUs").getAsString());
            temTarea.setIdUserCreador(expectJson.get("idUserCreador").getAsInt());
            temTarea.setIdUserEditor(expectJson.get("idUserEditor").getAsInt());
            temTarea.setEstado(expectJson.get("estado").getAsString());
            temTarea.setFecha(expectJson.get("fecha").getAsString());
            temTarea.setFechaFin(expectJson.get("fechaFin").getAsString());
            try {
                fechaFinal= dateFormat.parse(temTarea.getFechaFin());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int dias=(int) ((fechaFinal.getTime()-hoy.getTime())/86400000);
            System.out.println("/*/*/*/*/*/*/**/*/*/diasssss> "+dias+hoy.getTime());
            if (dias+1 == 2){
                Toast.makeText(ModuloTareas.this,"La tarea "+temTarea.getNombreUs()+" finalizara en 2 dias",Toast.LENGTH_LONG).show();
            }
            temTarea.setIdSprint(expectJson.get("idSprint").getAsInt());
            arrTareas.add(temTarea);
         }

        myAdapter = new CustomAdapter(this, R.layout.activity_item_tareas, arrTareas);
        listView.setAdapter(myAdapter);
        listView.setHorizontalScrollBarEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_e = position;
                editTextNombre.setText(arrTareas.get(position).getNombreUs());
                editTextIdUsuarioCreador.setText(arrTareas.get(position).getIdUserCreador().toString());
                editTextIdUsuarioEditor.setText(arrTareas.get(position).getIdUserEditor().toString());
                editTextEstado.setText(arrTareas.get(position).getEstado().toString());
                editTextIdSprint.setText(arrTareas.get(position).getIdSprint().toString());

            }
        });

    }

    public void getTareas(){
        listView = (ListView) findViewById(R.id.list_tareas);
        editTextNombre = (EditText) findViewById(R.id.edit_nombre_tarea);
        editTextIdUsuarioCreador = (EditText) findViewById(R.id.edit_usuario_creador);
        editTextIdUsuarioEditor = (EditText) findViewById(R.id.edit_usuario_editor);
        editTextEstado = (EditText) findViewById(R.id.edit_estado);
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

                //Toast.makeText(this, "Clic en insertar", Toast.LENGTH_SHORT).show();
                Toast.makeText(ModuloTareas.this, "Iniciando registro", Toast.LENGTH_SHORT).show();
                try {
                    mensaje = executePOST();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
                System.out.println("Entra onClic+++"+mensaje);
                myAdapter.notifyDataSetChanged();
                Toast .makeText(ModuloTareas.this, mensaje, Toast.LENGTH_SHORT).show();
                spinner.setAdapter(myAdapter);
                break;
            case R.id.btn_editar:
                try {

                    Tareas tem = new Tareas();
                    tem.setNombreUs(editTextNombre.getText().toString());
                    tem.setIdUserEditor(Integer.parseInt(editTextIdUsuarioCreador.getText().toString()));
                    tem.setIdUserCreador(Integer.parseInt(editTextIdUsuarioEditor.getText().toString()));
                    tem.setEstado(editTextEstado.getText().toString());
                    tem.setIdSprint(Integer.parseInt(editTextIdSprint.getText().toString()));

                    System.out.println("****************Id: "+ id_e );

                    mensaje = executePUT(tem, arrTareas.get(id_e).getIdUS());
                    if (mensaje.contains("Actualizado")){
                        arrTareas.get(id_e).setNombreUs(tem.getNombreUs());
                        arrTareas.get(id_e).setIdUserEditor(tem.getIdUserEditor());
                        arrTareas.get(id_e).setIdUserCreador(tem.getIdUserCreador());
                        arrTareas.get(id_e).setEstado(tem.getEstado());
                        arrTareas.get(id_e).setIdSprint(tem.getIdSprint());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                id_e = -1;

                myAdapter.notifyDataSetChanged();
                Toast.makeText(ModuloTareas.this, mensaje, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_eliminar:
                Tareas tem2 = new Tareas();
                tem2.setNombreUs(arrTareas.get(id_e).getNombreUs());
                tem2.setIdUserCreador(arrTareas.get(id_e).getIdUserCreador());
                tem2.setIdUserEditor(arrTareas.get(id_e).getIdUserEditor());
                tem2.setIdSprint(arrTareas.get(id_e).getIdSprint());
                tem2.setEstado(arrTareas.get(id_e).getEstado());

                System.out.println("+****+*++*+*++*+*+*+*+*+*+*++*+*+*++*+*+*++*+*++*+*+"+id_e);


                try {
                    executeDELETE(arrTareas.get(id_e).getIdUS());
                    arrTareas.remove(id_e);
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
            url = "http://192.168.0.13:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/getTareas?";

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
            //System.out.println("Output from Server .... \n");
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

    private String executePOST() throws IOException {
        Tareas tem = new Tareas();
        tem.setNombreUs(editTextNombre.getText().toString());
        tem.setIdUserCreador(Integer.parseInt(editTextIdUsuarioCreador.getText().toString().trim()));
        tem.setIdUserEditor(Integer.parseInt(editTextIdUsuarioEditor.getText().toString().trim()));
        tem.setIdSprint(Integer.parseInt(editTextIdSprint.getText().toString().trim()));
        tem.setEstado(editTextEstado.getText().toString().trim());
        arrTareas.add(tem);
        String spockAsJson = new Gson().toJson(tem);
        System.out.println("*/*/*/*/*//*/*/*/*//*"+spockAsJson);

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


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output = br.readLine();
        System.out.println("Output from Server .... \n");
        if (output != null) {
            System.out.println("**********************************************************"+output);
        }

        //System.out.println("***********************************************"+conn.getRequestMethod()+conn.getResponseCode()+conn.getContent());


        if(mensaje == "llave duplicada"){
            arrTareas.remove(tem);
        }

        conn.disconnect();
        return  output;


    }

    private String executePUT(Tareas tem, int id) throws IOException {

        String spockAsJson = new Gson().toJson(tem);

        System.out.println("+-+-+-+-+-+-+"+id+"*-*-*-"+tem);

        //constants
        URL url = new URL("http://192.168.0.13:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/editarTarea/"+id+"?");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        System.out.println("***********************************************"+spockAsJson);
        //conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");


        OutputStream os = conn.getOutputStream();
        os.write(spockAsJson.getBytes("UTF-8"));

        os.flush();
        System.out.println("***********************************************"+conn.getResponseMessage());


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output =  br.readLine();
        System.out.println("Output from Server .... \n");
        if (br.readLine()!=null) {
            System.out.println("**********************************************************"+output);
        }

        conn.disconnect();
        return output;

    }

    private void executeDELETE(int id) throws IOException {

        //String spockAsJson = new Gson().toJson(tem);

        System.out.println("Dentro de DELETE");
        //constants
        URL url = new URL("http://192.168.0.13:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/eliminarTarea/"+id+"?");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("DELETE");
        //conn.setRequestProperty("Content-Type", "application/json");

        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //os.write(json.getBytes("UTF-8"));

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
