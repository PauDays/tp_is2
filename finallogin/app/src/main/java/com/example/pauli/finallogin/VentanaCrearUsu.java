package com.example.pauli.finallogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VentanaCrearUsu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_crear_usu);

        Button btnGuardar, btnCancelar;
        btnGuardar=(Button)findViewById(R.id.buttonGuardar);
        btnCancelar=(Button)findViewById(R.id.buttonCancelar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
               // startActivity(new Intent(OpcionesUsuarios.this, ModuloUsuarios.class));
                //ServicioRestAgregarUsuario

                EditText nombre=findViewById(R.id.editTextNombre);
                EditText apellido=findViewById(R.id.editTextApellido);
                EditText nomUsu=findViewById(R.id.editTextUsuario);
                EditText mail=findViewById(R.id.editTextMail);
                EditText password=findViewById(R.id.editTextContrasenha);
                EditText nomRol=findViewById(R.id.editTextRol);
                String message;
                JSONObject loginParams = new JSONObject();
                try {
                        loginParams.put("nombre", nombre.getText().toString());
                        loginParams.put("apellido", apellido.getText().toString());
                        loginParams.put("usuario", nomUsu.getText().toString());
                        loginParams.put("contrasenha", password.getText().toString());
                        loginParams.put("mail", mail.getText().toString());
                        loginParams.put("rolusu",nomRol.getText().toString());
                } catch (JSONException e) {
                        e.printStackTrace();
                }

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

                StrictMode.setThreadPolicy(policy);

                try {
                    message = executePost("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/adduser", loginParams.toString());

                    if (message.equals("")){
                        Toast.makeText(VentanaCrearUsu.this,"FAILED ADD USER", Toast.LENGTH_SHORT).show();

                    }
                    if(message.equalsIgnoreCase("false")){
                        Toast.makeText(VentanaCrearUsu.this,"FAILED ADD USER", Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(VentanaCrearUsu.this,"Usuario agregado", Toast.LENGTH_LONG).show();

                    }

                }
                catch(NullPointerException e){
                    Toast.makeText(VentanaCrearUsu.this,"No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();

                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                finish();
            }
        });

    }

    @SuppressLint("WrongConstant")
    public String executePost(String targetURL, String urlParameters) {
        int timeout=15000;
        URL url;
        HttpURLConnection connection = null;
        try {
            //establece la conexion

            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            //envia la peticion
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // obtiene la respuesta
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            rd.close();
            return response.toString();

        } catch (Exception e) {
            Toast.makeText(this,"Error de conexión "+e.getMessage(), 10).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

}
