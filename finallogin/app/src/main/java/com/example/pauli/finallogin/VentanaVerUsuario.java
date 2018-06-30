package com.example.pauli.finallogin;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class VentanaVerUsuario extends AppCompatActivity {

    class Person {

        public String name1;
        public String apellido1;
        public String nom_usuario1;
        public String mail1;
        public String contrasenha1;
    }

    ArrayList<Person> arrayOfWebData = new ArrayList<Person>();
    FancyAdapter aa=null;
    static ArrayList<String> resultRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ventana_ver_usuario);
            String message = "";
            /////////////////////////////////
            try {
                message = executeGet("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/getusers");
                //http://localhost:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/getusers
            } catch (NullPointerException e) {
                Toast.makeText(this, "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();

            }
            try {
                JSONArray jArray = new JSONArray(message);
                for (int i = 0; i < jArray.length(); i++) {
                    //get our object, this is one person's worth of data
                    JSONObject json_data = jArray.getJSONObject(i);
                    //create a new person
                    Person resultRow = new Person();
                    //set that person's attributes
                    resultRow.name1 = json_data.getString("nombre");
                    resultRow.apellido1 = json_data.getString("apellido");
                    resultRow.nom_usuario1 = json_data.getString("usuario");
                    resultRow.mail1 = json_data.getString("mail");
                    //this is our arrayList object, we add our Person object to it
                    arrayOfWebData.add(resultRow);
                }
            } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
            }
            /////////////////////////////////

            ListView myListView = (ListView) findViewById(R.id.myListView);
            aa = new FancyAdapter();
            myListView.setAdapter(aa);
        } catch (Exception e) {

            // this is the line of code that sends a real error message to the log
            Log.e("ERROR", "ERROR IN CODE: " + e.toString());

            // this is the line that prints out the location in
            // the code where the error occurred.
            e.printStackTrace();
        }
    }




        class FancyAdapter extends ArrayAdapter<Person> {
        FancyAdapter() {
            super(VentanaVerUsuario.this, android.R.layout.simple_list_item_1, arrayOfWebData);
        }

        public View getView(int position, View convertView,
                            ViewGroup parent) {
            ViewHolder holder;
            //we call an if statement on our view that is passed in,
            //to see if it has been recycled or not.  if it has been recycled,
            //then it already exists and we do not need to call the inflater function
            //this saves us A HUGE AMOUNT OF RESOURCES AND PROCESSING
            //this is the proper way to do it
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.formato_fila, null);

                //here is something new.  we are using a class called a view holder
                holder = new ViewHolder(convertView);
                //we are using that class to cache the result of the findViewById function
                //which we then store in a tag on the view
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.populateFrom(arrayOfWebData.get(position));

            return (convertView);

        }
    }

    class ViewHolder {
       // public TextView nombre=null;
       // public TextView apellido=null;
        public TextView usu=null;
       // public TextView mail=null;

        ViewHolder(View row) {
           // nombre=(TextView)row.findViewById(R.id.name);
           // apellido=(TextView)row.findViewById(R.id.lastname);
            usu=(TextView)row.findViewById(R.id.miembro_nombre);
           // mail=(TextView)row.findViewById(R.id.mail);
        }
        //notice we had to change our populate from to take an arguement of type person
        void populateFrom(Person r) {
         //   nombre.setText(r.mail1);
          //  apellido.setText(r.apellido1);
            usu.setText(r.nom_usuario1);
         //   mail.setText(r.mail1);
        }
    }

    @SuppressLint("WrongConstant")
        public String executeGet(String targetURL) {
            int timeout=15000;
            URL url;
            HttpURLConnection connection = null;
            try {
                //establece la conexion

                url = new URL(targetURL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                //connection.setRequestProperty("Content-Type", "application/json");

               // connection.setUseCaches(false);
               // connection.setDoInput(true);
               // connection.setDoOutput(true);
               // connection.setConnectTimeout(timeout);
              //  connection.setReadTimeout(timeout);

                //envia la peticion
              // DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                //wr.writeBytes();
              //  wr.flush();
             //   wr.close();

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
