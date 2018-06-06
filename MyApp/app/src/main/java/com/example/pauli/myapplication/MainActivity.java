package com.example.pauli.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.pauli.myapplication.MESSAGE";

    EditText usuario, contrasenha;
    Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario=findViewById(R.id.editText1);
        contrasenha=findViewById(R.id.editText2);
        enviar=findViewById(R.id.button);
        enviar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                    callService();
            }
        });
    }

        public void callService()
        {
            String user= usuario.getText().toString();
            String con=contrasenha.getText().toString();
            ServiceTask serv=new ServiceTask(getApplicationContext(),"http://192.168.0.36:8084/scrumrest2/webresources/org.scrumrest2.entities.usuarios/login",user,con);
            serv.execute();
        }

    /** Called when the user taps the Send button */
 /*/   public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, ActividadMostrarMensaje.class);
        EditText editText = (EditText) findViewById(R.id.editText1);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }*/


}
