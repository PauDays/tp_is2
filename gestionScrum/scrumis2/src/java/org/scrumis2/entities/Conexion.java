
package org.scrumis2.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author G01
 */
public class Conexion {
    Connection con;
    String user;
    String pass;
    String servidor;
    String database;
    public Conexion () {
        con = null;
        user = "postgres";
        pass = "postgres";
        servidor = "localhost:5432";
        database = "gestion";
    }
    
    public Connection conectarBD() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url="jdbc:postgresql://"+servidor+"/"+database;
        con = DriverManager.getConnection(url, user, pass);
        return con;      
    }
    
    public void cerrarBD() throws SQLException {
        con.close();
    }
}
