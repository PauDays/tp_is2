package com.example.pauli.finallogin;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//implements OnClickListener

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button ok,back,exit;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        // Login button clicked
        ok = findViewById(R.id.btn_login);
        ok.setOnClickListener(this); ///////acaaaa fue el último cambio

        //result =findViewById(R.id.lbl_result);
    }

       @Override
    public void onClick(View view) {
        if(view == ok){
          //  boolean answer=Login();
            Login();

        }
    }

    @SuppressLint("WrongConstant")
    public void Login(){
        //recupera los valores ingresados por el usuario

        EditText editTextUserName = findViewById(R.id.txt_username);
        EditText editTextPassword = findViewById(R.id.txt_password);
        JSONObject message=new JSONObject();

        JSONObject loginParams = new JSONObject();

        try {
            loginParams.put("usuario", editTextUserName.getText().toString());
            loginParams.put("contrasenha", editTextPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            message = executePost("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/login", loginParams.toString());

            if (message==null){
                Toast.makeText(this,"FAILED LOGIN", 5).show();
              //  return false;

            }else{

                Toast.makeText(this,"Login correcto", 25000).show();
               // return true;
                Person resultRow = new Person();
                //set that person's attributes
                resultRow.setId_usuario(message.getInt("idUsuario"));
                resultRow.setName1(message.getString("rolusu"));


                Intent firstIntent= new Intent(this, OpcionesUsuarios.class);

                firstIntent.putExtra("ID_USER",resultRow.getId_usuario());
                firstIntent.putExtra("NOM_ROL", resultRow.getRolusu());
                startActivity(firstIntent);




            }

        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
           // return false;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("WrongConstant")
    public JSONObject executePost(String targetURL, String urlParameters) {
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
            return new JSONObject(response.toString());

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