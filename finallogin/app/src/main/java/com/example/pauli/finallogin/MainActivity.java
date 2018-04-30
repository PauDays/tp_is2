package com.example.pauli.finallogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import android.app.Activity;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//implements OnClickListener

public class MainActivity extends Activity implements OnClickListener{

    Button ok,back,exit;
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Login button clicked
        ok = findViewById(R.id.btn_login);
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

}
