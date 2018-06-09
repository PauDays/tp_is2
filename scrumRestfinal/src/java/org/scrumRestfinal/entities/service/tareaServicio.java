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
import org.scrumRestfinal.entities.Sprints;
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
    
   void addTareas(Sprints s) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO public.sprints(id_sprint, duracion, nombre_sprint, id_usuario, fecha, estado) values(?,?,?,?,?,?)";
        conex = con.conectarBD();
            
        PreparedStatement pst=conex.prepareStatement(sql);
        pst.setInt(1,this.obtenerIdMax());
        pst.setInt(2,s.getDuracion());
        pst.setString(3,s.getNombreSprint());
        pst.setInt(4,s.getIdUsuario().getIdUsuario());
        pst.setDate(5, new java.sql.Date(s.getFecha().getTime()));
        pst.setBoolean(6,s.getEstado());
        
        pst.execute();
        pst.close();
        conex.close();
        con.cerrarBD();    
    }
    
    public ArrayList<Sprints> getTareas() throws SQLException, ClassNotFoundException {
        ArrayList<Sprints> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("SELECT id_sprint, nombre_sprint, duracion, id_usuario, fecha FROM public.sprints where  estado = true");
        while (rs.next()) {
            Sprints tm = new Sprints ();
            tm.setIdSprint(rs.getInt(1));
            tm.setNombreSprint(rs.getString(2));
            tm.setDuracion(rs.getInt(3));
            tm.setIdUsuario((Usuarios) rs.getObject(4));
            tm.setFecha(rs.getDate(5));
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
     ///
     public int obtenerIdMax() throws ClassNotFoundException, SQLException {
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
    
     public void editarTarea(int id,Sprints tarea) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE public.sprints SET  duracion=?, nombre_sprint=?, id_usuario=?, fecha=?, estado=? where id_sprints = ?";
        conex = con.conectarBD();
         
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setInt(1, tarea.getDuracion());
        pst.setString(2, tarea.getNombreSprint());
        pst.setInt(3, tarea.getIdUsuario().getIdUsuario());
        pst.setDate(4, new java.sql.Date(tarea.getFecha().getTime()));
        pst.setBoolean(5, tarea.getEstado());
        pst.setInt(6, tarea.getIdSprint());
        
        System.out.println("ps: "+pst);
        pst.executeUpdate();
        pst.close();
        conex.close();
        con.cerrarBD();
    }
}
