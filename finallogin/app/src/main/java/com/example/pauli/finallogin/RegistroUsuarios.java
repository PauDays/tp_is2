package com.example.pauli.finallogin;

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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class RegistroUsuarios extends AppCompatActivity {

    private Button btRegistrarUsu;
    private EditText etNomUsu, etApeUsu, etMailUsu, etPassUsu, etUsu;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        btRegistrarUsu = (Button) findViewById(R.id.registrarUsuBt);
        etNomUsu = (EditText) findViewById(R.id.nomUsuTxt);
        etApeUsu = (EditText) findViewById(R.id.apeUsuTxt);
        etUsu = (EditText) findViewById(R.id.usuTxt);
        etMailUsu = (EditText) findViewById(R.id.mailUsuTxt);
        etPassUsu = (EditText) findViewById(R.id.passUsuTxt);
        btRegistrarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistroUsuarios.this, "Iniciando registro", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(RegistroUsuarios.this,"Usuarios registrado", Toast.LENGTH_SHORT).show();
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
        parameters.put("param3", etUsu.getText().toString());
        parameters.put("param4", etMailUsu.getText().toString());
        parameters.put("param5", etPassUsu.getText().toString());
        String response = "";
        try {
            URL url = new URL("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/adduser");
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
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        //StringBuilder[] sb = new StringBuilder[100];
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


}

