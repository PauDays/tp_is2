package com.example.pauli.finallogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
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

public class VentanaEditarUsu extends Activity implements OnClickListener {

    EditText et1,et2,et3,et4,et5;
    String general;

    Button btnActualizar, btnEliminar;

    int memberid;

    //SQLControlador dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_editar_usu);



        et1 = (EditText) findViewById(R.id.et_nombre);
        et2 = (EditText) findViewById(R.id.et_apellido);
        et3 = (EditText) findViewById(R.id.et_username);
        et4 = (EditText) findViewById(R.id.et_mail);
        et5 = (EditText) findViewById(R.id.et_password);

        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Intent i = getIntent();


         memberid=i.getIntExtra("miembroId",0);
        String memberName = i.getStringExtra("miembroNombre");
        String memberLastname=i.getStringExtra("miembroApellido");
        String memberUsername=i.getStringExtra("miembroUsername");
        String memberMail=i.getStringExtra("miembroMail");
        String memberPassword=i.getStringExtra("miembroPassword");



    //   member_id = Long.parseLong(memberId);

        et1.setText(memberName);
        et2.setText(memberLastname);
        et3.setText(memberUsername);
        et4.setText(memberMail);
        et5.setText(memberPassword);
        general=memberUsername;

        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Person modificado=new Person();
        String message;
        RestCalling rc=new RestCalling();
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
                    message = rc.executePut("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/editarusuario/"+general+"?", loginParams.toString());
                    if (message.equals("")){
                        Toast.makeText(VentanaEditarUsu.this,"No se pudo modifica al usuario seleccionado", Toast.LENGTH_SHORT).show();

                    }else{

                        Toast.makeText(VentanaEditarUsu.this,message, Toast.LENGTH_LONG).show();

                        Intent main = new Intent(VentanaEditarUsu.this, windowSeeUser.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                    }

                }
                catch(NullPointerException e){
                    Toast.makeText(VentanaEditarUsu.this,"No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();

                }

                this.returnHome();
                break;
            /**************************************************************/
            case R.id.btnEliminar:
             //   dbcon.deleteData(member_id);

              try {
                    message = rc.executeDelete("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/eliminarusuario/"+memberid+"?");

                    if (message.equals("")){
                        Toast.makeText(VentanaEditarUsu.this,"No se pudo eliminar el usuario seleccionado", Toast.LENGTH_SHORT).show();


                    }else{

                        Toast.makeText(VentanaEditarUsu.this,message, Toast.LENGTH_LONG).show();



                    }
                  Intent main = new Intent(VentanaEditarUsu.this, windowSeeUser.class)
                          .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  startActivity(main);

                }
                catch(NullPointerException e){
                    Toast.makeText(VentanaEditarUsu.this,"No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();

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


}