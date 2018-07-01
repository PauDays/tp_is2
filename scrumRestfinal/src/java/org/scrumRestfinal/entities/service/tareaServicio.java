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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.scrumRestfinal.entities.Conexion;
import org.scrumRestfinal.entities.Sprints;
import org.scrumRestfinal.entities.UsersHistories;
import org.scrumRestfinal.entities.Usuarios;

/**
 *
 * @author Pauli
 */
public class tareaServicio {
     Conexion con;
    Connection conex;
    public tareaServicio() {
        con = new Conexion();
        conex = null;
    }
    
   String addTareas(UsersHistories s) throws SQLException, ClassNotFoundException, ParseException {
        String sql="INSERT INTO public.users_histories(id_us, nombre_us, id_user_editor, id_user_creador, estado, id_sprint) values(?,?,?,?,?,?)";
        String sql2="INSERT INTO public.sprints(id_sprint, fecha, fecha_fin) values(?,?,?)";
        conex = con.conectarBD();
        PreparedStatement pst=conex.prepareStatement(sql);
        PreparedStatement pst2=conex.prepareStatement(sql2);
        System.out.println("/*/*/**/*"+s);
        String mensaje=null;
        try{

            pst.setInt(1,this.obtenerIdMaxUS());
            pst.setString(2,s.getNombreUs());
            System.out.println("/*/*/**/*"+s.getIdUserEditor());
            pst.setInt(3,s.getIdUserEditor());
            pst.setInt(4,s.getIdUserCreador());
            pst.setString(5,s.getEstado());
            pst.setInt(6,s.getIdSprint());
             pst.execute();
            mensaje = "Insertado";
        }catch(ClassNotFoundException | SQLException e){
            mensaje = e.toString();
            pst.close();
        }
        
      
        conex.close();
        con.cerrarBD();    
        return mensaje;
    }
    
    public ArrayList<UsersHistories> getTareas() throws SQLException, ClassNotFoundException {
        ArrayList<UsersHistories> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select us.id_us, us.nombre_us, us.id_user_editor, us.id_user_creador, us.estado,\n" +
"s.fecha, s.fecha_fin, us.id_sprint \n" +
"from users_histories us,  sprints s where us.id_sprint = s.id_sprint");
        while (rs.next()) {
            UsersHistories tm = new UsersHistories ();
            tm.setIdUs(rs.getInt(1));
            tm.setNombreUs(rs.getString(2));
            tm.setIdUserEditor(rs.getInt(3));
            tm.setIdUserCreador(rs.getInt(4));
            tm.setEstado(rs.getString(5));
            tm.setFecha(rs.getString(6));
            System.out.println("***********"+rs.getString(6)+"--"+rs.getString(7));
            tm.setFechaFin(rs.getString(7));
            tm.setIdSprint(rs.getInt(8));
            lista.add(tm);
            System.out.println("****************"+tm.getFechaFin());
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    
    public ArrayList<UsersHistories> getUsuarioTareas(Integer id) throws SQLException, ClassNotFoundException {
        ArrayList<UsersHistories> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        System.out.println("Tarea id: " + id);
        ResultSet rs = st.executeQuery("select us.id_us, us.nombre_us, us.id_user_editor, us.id_user_creador, us.estado,\n" +
"s.fecha, s.fecha_fin, us.id_sprint from users_histories us,  sprints s where us.id_sprint = s.id_sprint and us.id_user_editor = "+id);
        while (rs.next()) {
            UsersHistories tm = new UsersHistories ();
            System.out.println("Tarea id: " + rs.getInt(1));
            tm.setIdUs(rs.getInt(1));
            tm.setNombreUs(rs.getString(2));
            tm.setIdUserEditor(rs.getInt(3));
            tm.setIdUserCreador(rs.getInt(4));
            tm.setEstado(rs.getString(5));
            tm.setFecha(rs.getString(6));
            System.out.println("***********"+rs.getString(6)+"--"+rs.getString(7));
            tm.setFechaFin(rs.getString(7));
            tm.setIdSprint(rs.getInt(8));
            lista.add(tm);
            System.out.println("****************"+tm.getFechaFin());
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
     ///
     public int obtenerIdMaxUS() throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        //Usuarios user = new Usuarios();
        int maxId=0;
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select max(id_us) from \"users_histories\"");
        if (rs.next()) {
            maxId=rs.getInt(1);
           
        }
        
        return maxId+1;
    }
     
     public int obtenerIdMaxSprint() throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        //Usuarios user = new Usuarios();
        int maxId=0;
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select max(id_sprint) from \"sprints\"");
        if (rs.next()) {
            maxId=rs.getInt(1);
           
        }
        
        return maxId+1;
    }
    
     public String editarTarea(int id,UsersHistories tarea) throws SQLException, ClassNotFoundException, ParseException {
        String sql = "UPDATE public.users_histories SET  id_us= ?, nombre_us= ?, id_user_editor= ?, id_user_creador= ?, estado = ?, id_sprint = ? where id_us = ?";
       
        conex = con.conectarBD();
        String mensaje="Actualizado";
        PreparedStatement pst = conex.prepareStatement(sql);
        try{
        pst.setInt(1, id);
        pst.setString(2, tarea.getNombreUs());
        pst.setInt(3, tarea.getIdUserEditor());
        pst.setInt(4, tarea.getIdUserCreador());
        pst.setString(5, tarea.getEstado());
        pst.setInt(6, tarea.getIdSprint());
        pst.setInt(7, id);
         pst.executeUpdate();
        }catch(SQLException e){
            mensaje = e.toString();

            conex.close();
            con.cerrarBD();
        }
            pst.close();
        System.out.println("ps: "+pst);
       return mensaje;     
        
    }
     
     public void eliminarTarea(int id) throws ClassNotFoundException, SQLException {
      //  String sql="delete from \"usuarios\" set status = false where usuario = ?";
       String sql="delete from users_histories where id_us = ?";
        conex = con.conectarBD();
        
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setInt(1, id);
        pst.execute();
        pst.close();
        conex.close();
        con.cerrarBD();
    }
     
    
}
