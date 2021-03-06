package com.example.pauli.finallogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ModuloTareasByUser extends AppCompatActivity implements View.OnClickListener{
    int id_e = -1;
    ListView listView;
    EditText editTextNombre;
    EditText editTextIdUsuarioCreador;
    EditText editTextIdUsuarioEditor;
    EditText editTextIdSprint;
    EditText editTextEstado;
    Button buttonEditar;
    ArrayList<Tareas> arrTareas, pruebas;
    CustomAdapter myAdapter;
    String mensaje = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo_tareas_by_user);
        getTareas();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date hoy = Calendar.getInstance().getTime();

        Date fechaFinal= null;

        Intent nuevoIntent = getIntent();
        int idUsuario = nuevoIntent.getIntExtra("idUsuario", 0);
        System.out.println("*******************************+-+-+-+-+-+-+-+"+idUsuario);
        listView = (ListView) findViewById(R.id.list_tareas);
        arrTareas = new ArrayList<Tareas>();

        String resultado = executeGET(idUsuario);
        Gson gson = new Gson();
        JsonArray task = gson.fromJson(resultado, JsonArray.class);
        for (int i = 0; i <task.size (); i ++) {
            JsonObject expectJson = task.get(i).getAsJsonObject ();

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
                Toast.makeText(ModuloTareasByUser.this,"La tarea "+temTarea.getNombreUs()+" finalizara en 2 dias",Toast.LENGTH_LONG).show();
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
        editTextIdSprint =  findViewById(R.id.edit_id_sprint);
        buttonEditar = (Button) findViewById(R.id.btn_editar);
        buttonEditar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        RestCalling RC=new RestCalling();
        switch (v.getId()){
            case R.id.btn_editar:
                try {

                    Tareas tem = new Tareas();
                    tem.setNombreUs(editTextNombre.getText().toString());
                    tem.setIdUserEditor(Integer.parseInt(editTextIdUsuarioEditor.getText().toString()));
                    tem.setIdUserCreador(Integer.parseInt(editTextIdUsuarioCreador.getText().toString()));
                    tem.setEstado(editTextEstado.getText().toString());
                    tem.setIdSprint(Integer.parseInt(editTextIdSprint.getText().toString()));

                    System.out.println("****************Id: "+ id_e );

                    mensaje = RC.executePUT(tem, arrTareas.get(id_e).getIdUS());
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

                myAdapter.notifyDataSetChanged();
                Toast.makeText(ModuloTareasByUser.this, mensaje, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public String executeGET(int id) {
        int timeout=15000;
        String url;
        HttpURLConnection connection = null;


        try {
            url = "http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/idUsuarioTareas/"+id+"?";

            URL loginUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) loginUrl.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");


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


}
