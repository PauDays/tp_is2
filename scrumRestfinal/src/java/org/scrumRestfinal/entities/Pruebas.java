/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import com.google.gson.Gson;
import com.google.common.reflect.TypeToken;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.reflect.Type;

/**
 *
 * @author Sara Chamorro
 */
public class Pruebas {
    
    public void prueba(){
    try {
    String url = "http://localhost:8085/scrumRestfinal/webresources/org.scrumrestfinal.entities.usuarios/getroles?";
        
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
		System.out.println("Output from Server .... \n");
		while (br.readLine() != null) {
			output=br.readLine();
		}
                System.out.println(output);
                Gson gson = new Gson();
                
                JsonArray staff = gson.fromJson(output, JsonArray.class);
                   for (int i = 0; i <staff.size (); i ++) {
        JsonObject expectJson = staff.get(i).getAsJsonObject ();
                       System.out.println(expectJson.get("idRol"));
    }
                //System.out.println(prueba.get("idRol"));
 } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }   
}
    
    public static void main(String[] args) {
        Pruebas p = new Pruebas();
        p.prueba();
    }
    }