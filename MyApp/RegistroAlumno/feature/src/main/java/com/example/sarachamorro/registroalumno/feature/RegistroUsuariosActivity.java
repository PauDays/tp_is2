package com.example.sarachamorro.registroalumno.feature;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import static com.example.sarachamorro.registroalumno.feature.R.id.nomUsuTxt;

public class RegistroUsuariosActivity extends AppCompatActivity {

    private Button btRegistrarUsu;
    private EditText etNomUsu, etApeUsu, etMailUsu, etPassUsu;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        btRegistrarUsu = (Button) findViewById(R.id.registrarUsuBt);
        etNomUsu = (EditText) findViewById(R.id.nomUsuTxt);
        etApeUsu = (EditText) findViewById(R.id.apeUsuTxt);
        etMailUsu = (EditText) findViewById(R.id.mailUsuTxt);
        etPassUsu = (EditText) findViewById(R.id.passUsuTxt);
        btRegistrarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistroUsuariosActivity.this, "Iniciando registro", Toast.LENGTH_SHORT).show();
                InvocarServicioRegistrarUsuarios ws = new InvocarServicioRegistrarUsuarios();
                ws.execute();
            }
        });
    }

    private class InvocarServicioRegistrarUsuarios extends AsyncTask<Void, Integer, Void> {
            private int progreso;
            @Override
            protected Void doInBackground(Void... arg0) {
                registrarServicio();
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                btRegistrarUsu.setClickable(true);
                Toast.makeText(RegistroUsuariosActivity.this,"Usuarios registrado", Toast.LENGTH_SHORT).show();
            }
            @Override
            protected void onPreExecute() {
                progreso = 0;
                btRegistrarUsu.setClickable(false);
            }
            @Override
            protected void onProgressUpdate(Integer... values) {
            }
        }

        private void registrarServicio() {
            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.put("param1", etNomUsu.getText().toString());
            parameters.put("param2", etApeUsu.getText().toString());
            parameters.put("param3", etMailUsu.getText().toString());
            parameters.put("param4", etPassUsu.getText().toString());
            String response = "";
            try {
                URL url = new URL("http://localhost:8085/NuevoProyecto/webresources/nuevoproyecto.entities.usuarios/list");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(parameters));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;
                    }
                } else {
                    response = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



}
