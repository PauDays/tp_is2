package com.example.sarachamorro.registroalumno.feature;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class VerUsuariosActivity extends AppCompatActivity {
    private ListView listaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuarios);

        listaUsuarios = (ListView) findViewById(R.id.listUsuarios);
        InvocarServicioObtenerUsuarios ws = new InvocarServicioObtenerUsuarios();
        ws.execute();
    }

    private class InvocarServicioObtenerUsuarios extends AsyncTask<String, String, String> {
        private String resp;
        HttpURLConnection urlConnection;
        @Override
        protected String doInBackground(String... params) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL("http://localhost:8085/NuevoProyecto/webresources/nuevoproyecto.entities.usuarios/list");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
            } catch (Exception e) {
                Toast.makeText(VerUsuariosActivity.this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                urlConnection.disconnect();
            }
            return result.toString();
        }
        @Override
        protected void onPostExecute(String result) {
            jsonInsertLocal(result);
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(String... text) {
        }
    }
    private void jsonInsertLocal(String msgjson) {
        try {
            List<String> contes = new ArrayList<String>();
            JSONObject obj = new JSONObject(msgjson);
            JSONArray lista = obj.optJSONArray("contenidos");
            for (int i = 0; i < lista.length(); i++) {
                JSONObject json_data = lista.getJSONObject(i);
                String conte = json_data.getString("param1") + " " + json_data.getString("param2") + " " +
                        json_data.getString("param3");
                contes.add(conte);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    contes);
            listaUsuarios.setAdapter(adapter);
        } catch (Exception ex) {
            Toast.makeText(this, "Error garga lista:" + ex.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
        }
    }

}
