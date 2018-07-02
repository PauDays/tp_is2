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
    TextView tv_miemNombre;

    ArrayList<Person> arrayOfWebData = new ArrayList<Person>();

    FancyAdapter aa=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_see_user);
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
        RestCalling rc=new RestCalling();
        try {
            message = rc.executeGet("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/getusers");

        } catch (NullPointerException e) {
            Toast.makeText(this, "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();

        }try {
            JSONArray jArray = new JSONArray(message);
            for (int i = 0; i < jArray.length(); i++) {
                //obtenemos los objetos donde están los datos de los usuarios
                JSONObject json_data = jArray.getJSONObject(i);
                Person resultRow = new Person();
                //se guardan los atributos
                resultRow.setId_usuario(json_data.getInt("idUsuario"));
                resultRow.setName1(json_data.getString("nombre"));
                resultRow.setApellido1(json_data.getString("apellido"));
                resultRow.setNom_usuario1(json_data.getString("usuario"));
                resultRow.setMail1(json_data.getString("mail"));
                resultRow.setContrasenha1(json_data.getString("contrasenha"));
                //cada resultrow se colocal en el array
                arrayOfWebData.add(resultRow);
            }
        } catch (JSONException e) {
            Log.e("log_tag", "Error al parsear " + e.toString());
        }

        ListView myListView = (ListView) findViewById(R.id.listViewMiembros);
        aa = new FancyAdapter();
        myListView.setAdapter(aa);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);

                String aux_miembroNombre = tv_miemNombre.getText().toString();
               /*cuando se haga click en el nombre de una persona
               uso la función para sacar los datos del arraylist y mostralos en la ventana
               */

                Person buscado=encontrarParaModificar(aux_miembroNombre);

                Intent modify_intent = new Intent(getApplicationContext(), VentanaEditarUsu.class);
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
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.formato_fila, null);

                //usamos la clase viewholder
                holder = new ViewHolder(convertView);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.populateFrom(arrayOfWebData.get(position));

            return (convertView);

        }
    }

    class ViewHolder {

        public TextView usu=null;


        ViewHolder(View row) {
          // solamente va a mostrar el nombre del usuario
            usu=(TextView)row.findViewById(R.id.miembro_nombre);
          //  mail=(TextView)row.findViewById(R.id.mail);
        }

        void populateFrom(Person r) {

            usu.setText(r.getNom_usuario1());

        }
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