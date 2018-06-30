package com.example.pauli.finallogin;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class windowSeeUser extends Activity {

    Button btnAgregarMiembro;
    ListView lista;
    //SQLControlador dbconeccion;
    TextView tv_miemID, tv_miemNombre;



    ArrayList<Person> arrayOfWebData = new ArrayList<Person>();

    static ArrayList<String> resultRow;
    FancyAdapter aa=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_see_user);

        //dbconeccion = new SQLControlador(this);
       // dbconeccion.abrirBaseDeDatos();
        btnAgregarMiembro = (Button) findViewById(R.id.btnAgregarMiembro);
        lista = (ListView) findViewById(R.id.listViewMiembros);

        //acción del boton agregar miembro
        btnAgregarMiembro.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iagregar = new Intent(windowSeeUser.this, VentanaCrearUsu.class);
                startActivity(iagregar);
            }
        });
        //ESTA PARTE LO QUE QUIERE HACER ES UN GET USERS QUE MUESTRE SOLO LOS NOMBRES DE LOS USUARIOS
        String message = "";
        /////////////////////////////////
        try {
            message = executeGet("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/getusers");
            //http://localhost:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/getusers
        } catch (NullPointerException e) {
            Toast.makeText(this, "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();

        }try {
            JSONArray jArray = new JSONArray(message);
            for (int i = 0; i < jArray.length(); i++) {
                //get our object, this is one person's worth of data
                JSONObject json_data = jArray.getJSONObject(i);
                //create a new person
                Person resultRow = new Person();
                //set that person's attributes
                resultRow.setId_usuario(json_data.getInt("idUsuario"));
                resultRow.setName1(json_data.getString("nombre"));
                resultRow.setApellido1(json_data.getString("apellido"));
                resultRow.setNom_usuario1(json_data.getString("usuario"));
                resultRow.setMail1(json_data.getString("mail"));
                resultRow.setContrasenha1(json_data.getString("contrasenha"));
                //this is our arrayList object, we add our Person object to it
                arrayOfWebData.add(resultRow);
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }

        ListView myListView = (ListView) findViewById(R.id.listViewMiembros);
        aa = new FancyAdapter();
        myListView.setAdapter(aa);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

               // tv_miemID = (TextView) view.findViewById(R.id.miembro_id);
                tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);

               // String aux_miembroId = tv_miemID.getText().toString();
                String aux_miembroNombre = tv_miemNombre.getText().toString();
                /////////////////////////////
                Person buscado=encontrarParaModificar(aux_miembroNombre);



                ////////////////////////////////

                Intent modify_intent = new Intent(getApplicationContext(), VentanaEditarUsu.class);
              // modify_intent.putExtra("miembroId", buscado.getId_usuario());
               modify_intent.putExtra("miembroId",buscado.getId_usuario());
                modify_intent.putExtra("miembroNombre", buscado.getName1());
                modify_intent.putExtra("miembroApellido", buscado.getApellido1());
                modify_intent.putExtra("miembroUsername", buscado.getNom_usuario1());
                modify_intent.putExtra("miembroMail", buscado.getMail1());
                modify_intent.putExtra("miembroPassword", buscado.getContrasenha1());
                startActivity(modify_intent);
            }
        });
    }  //termina el onCreate


    class FancyAdapter extends ArrayAdapter<Person> {
        FancyAdapter() {
            super(windowSeeUser.this, R.layout.formato_fila, arrayOfWebData);
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
     //   public TextView nombre=null;
      //  public TextView apellido=null;
        public TextView usu=null;
      //  public TextView mail=null;

        ViewHolder(View row) {
          //  nombre=(TextView)row.findViewById(R.id.name);
          //  apellido=(TextView)row.findViewById(R.id.lastname);
            usu=(TextView)row.findViewById(R.id.miembro_nombre);
          //  mail=(TextView)row.findViewById(R.id.mail);
        }
        //notice we had to change our populate from to take an arguement of type person
        void populateFrom(Person r) {
       //     nombre.setText(r.mail1);
       //     apellido.setText(r.apellido1);
            usu.setText(r.getNom_usuario1());
        //    mail.setText(r.mail1);
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

    public Person encontrarParaModificar(String buscado)
    {
        Person encontrado=null;

        for (Person prof : arrayOfWebData) {
            if (prof.getNom_usuario1().equals(buscado)) {
                encontrado = prof;
                break;
            }
        }
        return encontrado;


    }


} //termina clase