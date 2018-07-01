package com.example.pauli.finallogin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class windowOnlyModify extends AppCompatActivity implements View.OnClickListener {

    EditText et1,et2,et3,et4,et5;
    String general;

    Button btnActualizar, btnEliminar;

    int memberid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_only_modify);

        Intent nuevoIntent = getIntent();
        int idUsuario = nuevoIntent.getIntExtra("idUsuario", 0);
        String personaJson=nuevoIntent.getStringExtra("personaJson");
        et1 = (EditText) findViewById(R.id.et_nombre);
        et2 = (EditText) findViewById(R.id.et_apellido);
        et3 = (EditText) findViewById(R.id.et_username);
        et4 = (EditText) findViewById(R.id.et_mail);
        et5 = (EditText) findViewById(R.id.et_password);


        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        try {
            JSONObject ps=new JSONObject(personaJson);
            // resultRow.setId_usuario(message.getInt("idUsuario"));
            et1.setText(ps.getString("nombre"));
            et2.setText(ps.getString("apellido"));
            et3.setText(ps.getString("usuario"));
            et4.setText(ps.getString("mail"));
            et5.setText(ps.getString("contrasenha"));
            general=ps.getString("usuario");

            btnActualizar.setOnClickListener(this);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        Person modificado=new Person();
        String message;
        switch (v.getId()) {
            case R.id.btnActualizar:
                /***********************/
                modificado.setName1(et1.getText().toString());
                modificado.setApellido1(et2.getText().toString());
                modificado.setNom_usuario1(et3.getText().toString());
                modificado.setMail1(et4.getText().toString());
                modificado.setContrasenha1(et5.getText().toString());

                JSONObject loginParams = new JSONObject();
                try {
                    loginParams.put("nombre", modificado.getName1());
                    loginParams.put("apellido", modificado.getApellido1());
                    loginParams.put("usuario", modificado.getNom_usuario1());
                    loginParams.put("contrasenha", modificado.getContrasenha1());
                    loginParams.put("mail", modificado.getMail1());
                    //   loginParams.put("rolusu",modificado.getRolusu());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                try {
                    message = executePut("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/editarusuario/"+general+"?", loginParams.toString());
                    if (message.equals("")){
                        Toast.makeText(windowOnlyModify.this,"No se pudo modifica al usuario seleccionado", Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(windowOnlyModify.this,message, Toast.LENGTH_LONG).show();

                        Intent main = new Intent(windowOnlyModify.this, windowSeeUser.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                    }

                }
                catch(NullPointerException e){
                    Toast.makeText(windowOnlyModify.this,"No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();

                }

                this.returnHome();
                break;

        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                windowSeeUser.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }

    public String executePut(String targetURL,String urlParameters) {
        int timeout=15000;
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection

            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type","application/json");



            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);

            // Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            // Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            String res = response.toString();
            return res;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

}
