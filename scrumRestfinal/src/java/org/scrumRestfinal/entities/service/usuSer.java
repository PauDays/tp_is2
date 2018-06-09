/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.scrumRestfinal.entities.Conexion;
import org.scrumRestfinal.entities.Roles;
import org.scrumRestfinal.entities.Usuarios;
import org.scrumRestfinal.entities.UsuariosRoles;

/**
 *
 * @author Pauli
 */
public class usuSer {
     Conexion con;
    Connection conex;
    public usuSer() {
        con = new Conexion();
        conex = null;
    }

    void addRol(UsuariosRoles ur) throws ClassNotFoundException, SQLException{
        String sql="INSERT INTO public.usuarios_roles(	id_usuario_rol, id_rol, id_usuario) VALUES (?, ?, ?)";
        conex = con.conectarBD();
            
        PreparedStatement pst=conex.prepareStatement(sql);
        pst.setInt(1,this.obtenerIdMax());
        pst.setInt(2,ur.getIdRol().getIdRol());
        pst.setInt(3,ur.getIdUsuario().getIdUsuario());
        
        pst.execute();
        pst.close();
        conex.close();
        con.cerrarBD();    
    
    }
    
   void addUsuario(Usuarios u) throws SQLException, ClassNotFoundException {
        String sql="insert into \"usuarios\" (id_usuario,nombre,apellido,usuario,contrasenha, mail, estado) values(?,?,?,?,?,?,true)";
        conex = con.conectarBD();
            
        PreparedStatement pst=conex.prepareStatement(sql);
        pst.setInt(1,this.obtenerIdMax());
        pst.setString(2,u.getNombre());
        pst.setString(3,u.getApellido());
        pst.setString(4,u.getUsuario());
        pst.setString(5,u.getContrasenha());
        pst.setString(6,u.getMail());
        
        pst.execute();
        pst.close();
        conex.close();
        con.cerrarBD();    
    }
   
    public ArrayList<Roles> getUsersRol() throws SQLException, ClassNotFoundException {
        ArrayList<Roles> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_rol, nombre_rol FROM public.roles");
        while (rs.next()) {
            Roles tm = new Roles ();
            tm.setIdRol(rs.getInt(1));
            tm.setNombreRol(rs.getString(2));
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
   
    public ArrayList<Usuarios> getUsers() throws SQLException, ClassNotFoundException {
        ArrayList<Usuarios> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select id_usuario, nombre, apellido, usuario, contrasenha, mail from \"usuarios\" where estado=true");
        while (rs.next()) {
            Usuarios tm = new Usuarios ();
            tm.setNombre(rs.getString("nombre"));
            tm.setApellido(rs.getString("apellido"));
            tm.setUsuario(rs.getString("usuario"));
            tm.setContrasenha(rs.getString("contrasenha"));
            tm.setMail(rs.getString("mail"));
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    public Usuarios login(String usuario, String contrasenha) throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        Usuarios user = new Usuarios();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select id_usuario, nombre, mail from \"usuarios\" where usuario = '"+usuario+"' and contrasenha ='"+contrasenha+"'");
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
        
     ///
     public int obtenerIdMax() throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        //Usuarios user = new Usuarios();
        int maxId=0;
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select max(id_usuario) from \"usuarios\"");
        if (rs.next()) {
            maxId=rs.getInt(1);
           
        }
        
        return maxId+1;
    }
    
     public int obtenerIdMaxRol() throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        //Usuarios user = new Usuarios();
        int maxId=0;
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select max(id_usuario_rol) from \"usuarios_roles\"");
        if (rs.next()) {
            maxId=rs.getInt(1);  
        }
        return maxId+1;
    }
     
     public void eliminarUsu(String usuario) throws ClassNotFoundException, SQLException {
        String sql="update \"Usuarios\" set status = false where usuario = ?";
        conex = con.conectarBD();
        
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setString(1, usuario);
        pst.executeUpdate();
        pst.close();
        conex.close();
        con.cerrarBD();
    }
     public void editarUsu(String nomUsu,Usuarios user) throws SQLException, ClassNotFoundException {
        String sql = "update usuarios set nombre = ?, apellido=?, usuario=?, contrasenha=?, mail = ?, estado=? where usuario = ?";
        conex = con.conectarBD();
         
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setString(1, user.getNombre());
        pst.setString(2, user.getApellido());
        pst.setString(3, user.getUsuario());
        pst.setString(4, user.getContrasenha());
        pst.setString(5, user.getMail());
        pst.setBoolean(6, user.getEstado());
        pst.setString(7, nomUsu);
        System.out.println("ps: "+pst);
        pst.executeUpdate();
        pst.close();
        conex.close();
        con.cerrarBD();
    }
}
