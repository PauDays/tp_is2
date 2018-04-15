/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumrest2.entities.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.scrumrest2.entities.Usuarios;

/**
 *
 * @author pauli
 */

public class usuSer {
    
    Conexion con;
    Connection conex;
    public usuSer() {
        con = new Conexion();
        conex = null;
    }
    
   /* void addUsuario(Usuarios u) throws SQLException, ClassNotFoundException {
        String sql="insert into \"usuarios\" (id_usuario, nombre,apellido,telefono, direccion, mail,contrasenha) values(?,?,?,?,?,?,?)";
        conex = con.conectarBD();
            
        PreparedStatement pst=conex.prepareStatement(sql);
        pst.setInt(1,u.getIdUsuario());
        pst.setString(2,u.getNombre());
        pst.setString(3,u.getApellido());
        pst.setString(4,u.getTelefono());
        pst.setString(5,u.getDireccion());
        pst.setString(6,u.getMail());
        pst.setString(7,u.getContrasenha());
        pst.execute();
        pst.close();
        conex.close();
        con.cerrarBD();    
    }*/
    
    public ArrayList<Usuarios> getUsers() throws SQLException, ClassNotFoundException {
        ArrayList<Usuarios> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select nombre,mail from \"usuarios\"");
        while (rs.next()) {
            Usuarios tm = new Usuarios ();
            tm.setNombre(rs.getString("nombre"));
            tm.setMail(rs.getString("mail"));
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    public Usuarios login(String usuario) throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        Usuarios user = new Usuarios();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select id_usuario, nombre, mail from \"usuarios\" where usuario = '"+usuario+"'");
        if (rs.next()) {
            user.setIdUsuario(rs.getInt(1));
            user.setNombre(rs.getString(2));
            user.setMail(rs.getString(3));
            
        }
        else {
            user = null;
        }
        return user;
    }
        
        
    
}
