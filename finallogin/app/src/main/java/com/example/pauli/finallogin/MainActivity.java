package com.example.pauli.finallogin;

<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
=======
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
import java.net.URL;
>>>>>>> 9a8c7209048aea3d06045e6e816dbcba69be7574
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
<<<<<<< HEAD
=======
import org.json.JSONException;
import org.json.JSONObject;
>>>>>>> 9a8c7209048aea3d06045e6e816dbcba69be7574

import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
<<<<<<< HEAD

//implements OnClickListener

public class MainActivity extends Activity implements OnClickListener{
=======
import android.widget.Toast;

//implements OnClickListener

public class MainActivity extends AppCompatActivity implements OnClickListener {
>>>>>>> 9a8c7209048aea3d06045e6e816dbcba69be7574

    Button ok,back,exit;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
=======
       // setContentView(R.layout.activity_main);
>>>>>>> 9a8c7209048aea3d06045e6e816dbcba69be7574
        setContentView(R.layout.activity_main);

        // Login button clicked
        ok = findViewById(R.id.btn_login);
<<<<<<< HEAD
        ok.setOnClickListener(this);

        result =findViewById(R.id.lbl_result);
    }

    public void postLoginData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();

        /* login.php returns true if username and password is equal to saranga */
        HttpPost httppost = new HttpPost("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/login");
        // new HttpPost("http://localhost:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/log");

        try {
            // Add user name and password
            EditText uname = findViewById(R.id.txt_username);
            String username = uname.getText().toString();

            EditText pword = findViewById(R.id.txt_password);
            String password = pword.getText().toString();

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("usuario", username));
            nameValuePairs.add(new BasicNameValuePair("contrasenha", password));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            Log.w("LOGIN", "Execute HTTP Post Request");
            HttpResponse response = httpclient.execute(httppost);

            String str = inputStreamToString(response.getEntity().getContent()).toString();
            //String str = inputStreamToString(response.getEntity().getContent()).toString();

            Log.w("LOGIN", str);

            if(str.toString().equalsIgnoreCase("true"))
            {
                Log.w("LOGIN", "TRUE");
                result.setText("Login successful");
            }else
            {
                Log.w("LOGIN", "FALSE");
                result.setText(str);
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder inputStreamToString(InputStream is) {
        String line = "";
        StringBuilder total = new StringBuilder();
        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        // Read response until the end
        try {
            while ((line = rd.readLine()) != null) {
                total.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Return full string
        return total;
    }

    @Override
    public void onClick(View view) {
        if(view == ok){
            postLoginData();
        }
    }

=======
        ok.setOnClickListener(this); ///////acaaaa fue el último cambio

        //result =findViewById(R.id.lbl_result);
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
        String message;
        
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
            Intent intent = new Intent(this, RegistroUsuarios.class);
            if (message.equals("")){
                Toast.makeText(this,"FAILED LOGIN", 5).show();
                return;
            }
            if(message.equalsIgnoreCase("false")){
                Toast.makeText(this,"Los datos ingresados no coinciden", 25000).show();

            }else{

                Toast.makeText(this,"Login correcto", 25000).show();
                startActivity(intent);
            }

        }
        catch(NullPointerException e){
            Toast.makeText(this,"No se pudo conectar con el servidor", 5).show();
        }

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



>>>>>>> 9a8c7209048aea3d06045e6e816dbcba69be7574
}
