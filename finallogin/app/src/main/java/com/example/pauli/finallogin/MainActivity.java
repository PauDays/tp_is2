package com.example.pauli.finallogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
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
        setContentView(R.layout.activity_main);
        // Login button clicked
        ok = findViewById(R.id.btn_login);
        ok.setOnClickListener(this); ///////acaaaa fue el último cambio

    }

       @Override
    public void onClick(View view) {
        if(view == ok){
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
        RestCalling rc=new RestCalling();
        try {

            message=rc.executePost("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/login", loginParams.toString());
            if (message==null){

                Toast.makeText(this,"Login Incorrecto", 5).show();

            }else{

                Toast.makeText(this,"Login correcto", 25000).show();

                Person resultRow = new Person();
                //acá paso las variables al siguiente intent
                resultRow.setId_usuario(message.getInt("idUsuario"));
                resultRow.setRolusu(message.getString("rolusu"));

                Intent firstIntent= new Intent(this, OpcionesUsuarios.class);
                firstIntent.putExtra("IDUSER",resultRow.getId_usuario());
                firstIntent.putExtra("NOMROL", resultRow.getRolusu());
                firstIntent.putExtra("Persona",message.toString());
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


}
