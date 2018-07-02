package com.example.pauli.finallogin;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestCalling extends Activity {
    String ip="";



    //Métodos PARA USUARIOS

    public JSONObject executePost(String targetURL, String urlParameters) {
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
            return new JSONObject(response.toString());

        } catch (Exception e) {
            Toast.makeText(RestCalling.this,"Error de conexión "+e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    @SuppressLint("WrongConstant")
    public String executeGet(String targetURL) {
        int timeout=15000;
        URL url;
        HttpURLConnection connection = null;
        try {
            //se obtiene la conexión

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
            Toast.makeText(this,"Error "+e.getMessage(), 10).show();
            e.printStackTrace();
        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
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


    public String executeDelete(String targetURL) {
        int timeout=15000;
        URL url;
        HttpURLConnection connection = null;
        try {
            // Create connection

            url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");


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

    //Metodos para Tareas

    public String executeGET() {
        int timeout=15000;
        String url;
        HttpURLConnection connection = null;

        try {
            url = "http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/getTareas?";

            URL loginUrl = new URL(url);
            HttpURLConnection con = (HttpURLConnection) loginUrl.openConnection();

            //add reuqest header
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");

            if (con.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + con.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (con.getInputStream())));

            String output = br.readLine();
            //System.out.println("Output from Server .... \n");
            while (br.readLine() != null) {
                output=br.readLine();
            }
            System.out.println(output);
            //System.out.print
            return output.toString();

        } catch (Exception e) {
            Toast.makeText(this.getBaseContext(),"Error de conexión $e.message",Toast.LENGTH_SHORT).show();
            //Toast.makeText(this , "Error de conexión $e.message", 10).show();
            e.printStackTrace();
        } finally {
            System.out.println("--------------------------*****************************doIn");
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;

    }

    public String executePUT(Tareas tem, int id) throws IOException {

        String spockAsJson = new Gson().toJson(tem);

        System.out.println("+-+-+-+-+-+-+"+id+"*-*-*-"+tem);

        //constants
        URL url = new URL("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/editarTarea/"+id+"?");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        System.out.println("***********************************************"+spockAsJson);
        //conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");


        OutputStream os = conn.getOutputStream();
        os.write(spockAsJson.getBytes("UTF-8"));

        os.flush();
        System.out.println("***********************************************"+conn.getResponseMessage());


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output =  br.readLine();
        System.out.println("Output from Server .... \n");
        if (br.readLine()!=null) {
            System.out.println("**********************************************************"+output);
        }

        conn.disconnect();
        return output;

    }

    public void executeDELETE(int id) throws IOException {

        //String spockAsJson = new Gson().toJson(tem);

        System.out.println("Dentro de DELETE");
        //constants
        URL url = new URL("http://192.168.0.36:8084/scrumRestfinal/webresources/org.scrumrestfinal.entities.usershistories/eliminarTarea/"+id+"?");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("DELETE");
        //conn.setRequestProperty("Content-Type", "application/json");

        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        //os.write(json.getBytes("UTF-8"));

        System.out.println("***********************************************"+conn.getResponseMessage());


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            System.out.println("**********************************************************"+output);
        }

        conn.disconnect();


    }



}
