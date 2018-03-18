
package org.scrumis2.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author G01
 */
public class UsuarioService {
    
    Conexion con;
    Connection conex;
    public UsuarioService() {
        con = new Conexion();
        conex = null;
    }

    void addUsuario(Login u) throws SQLException, ClassNotFoundException {
        String sql="insert into \"usuarios\" (nombre,contrasenha) values(?,?)";
        conex = con.conectarBD();
            
        PreparedStatement pst=conex.prepareStatement(sql);
        pst.setString(1,u.getNombre());
        pst.setString(2,u.getContrasenha());   
        pst.execute();
        pst.close();
        conex.close();
        con.cerrarBD();    
    }
    //==========================================================================
    
    public void deleteUser(int id) throws ClassNotFoundException, SQLException {
        String sql="delete from \"Usuarios\" where id = ?";
        conex = con.conectarBD();
        
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();
        pst.close();
        conex.close();
        con.cerrarBD();
    }
    //==========================================================================
    
    public void editUser(int id, Login user) throws SQLException, ClassNotFoundException {
        String sql = "update \"Usuarios\" set nombre = ?, contrasenha= ? where id = ?";
        conex = con.conectarBD();
        
        PreparedStatement pst = conex.prepareStatement(sql);
        pst.setString(1, user.getNombre());
        pst.setString(2, user.getContrasenha());
        pst.setInt(3, id);
        pst.executeUpdate();
        pst.close();
        conex.close();
        con.cerrarBD();
    }
    //==========================================================================
    
    public ArrayList<Usuario> getUsers() throws SQLException, ClassNotFoundException {
        ArrayList<Usuario> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select nombre,correo from \"Usuarios\"");
        while (rs.next()) {
            Usuario tm = new Usuario ();
            tm.setNombre(rs.getString("nombre"));
            tm.setCorreo(rs.getString("correo"));
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    //==========================================================================
    
    public ArrayList<Hijo> getHijos(int userId) throws SQLException, ClassNotFoundException {
        ArrayList<Hijo> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        String sql = "select nombre,apellido,fecha_nacimiento::varchar fecha,"
                   + "lugar_nacimiento, id_hijo::varchar id_hijo, barrio, responsable,"
                   + "sexo, direccion, nacionalidad, departamento, municipio, "
                   + "referencia_domicilio, responsable, referencia_domicilio, "
                   + "telefono_contacto, seguro_medico, alergia "
                   + "from \"Hijos\" "
                   + "where id_usuario = "+userId;
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Hijo tm = new Hijo();
            tm.setId(rs.getString("id_hijo"));
            tm.setNombre(rs.getString("nombre"));
            tm.setApellido(rs.getString("apellido"));
            tm.setSexo(rs.getString("sexo"));
            tm.setFechaNacimiento(rs.getString("fecha"));
            tm.setLugarNacimiento(rs.getString("lugar_nacimiento"));
            tm.setDireccion(rs.getString("direccion"));
            tm.setNacionalidad(rs.getString("nacionalidad"));
            tm.setMunicipio(rs.getString("municipio"));
            tm.setDepartamento(rs.getString("departamento"));
            tm.setBarrio(rs.getString("barrio"));
            tm.setReferenciaDomicilio(rs.getString("referencia_domicilio"));
            tm.setResponsable(rs.getString("responsable"));
            tm.setTelefonoContacto(rs.getString("telefono_contacto"));
            tm.setSeguroMedico(rs.getString("seguro_medico"));
            tm.setAlergia(rs.getString("alergia"));
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
     //==========================================================================
    
    public ArrayList<Registro> getRegistros(int userId) throws SQLException, ClassNotFoundException {
        ArrayList<Registro> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        String sql = "select rv.estado, coalesce(rv.fecha::varchar,'') fecha, v.nombre, "
                   + "coalesce(rv.responsable,'') responsable, "
                   + "v.id_vacuna, rv.id_hijo::varchar id_hijo, rv.dosis, rv.edad_meses, coalesce(rv.lote,'') lote "
                   + "from \"RegistroVacuna\" rv "
                   + "join \"Vacunas\" v on v.id_vacuna=rv.id_vacuna "
                   + "join \"Hijos\" h on rv.id_hijo = h.id_hijo "
                   + "where h.id_usuario = "+userId;
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Registro tm = new Registro();
            tm.setNombreVacuna(rs.getString("nombre"));
            tm.setEstado(rs.getInt("estado"));
            tm.setFecha(rs.getString("fecha"));
            tm.setDosis(rs.getInt("dosis"));
            tm.setEdad(rs.getInt("edad_meses"));
            tm.setLote(rs.getString("lote"));
            tm.setHijoId(rs.getString("id_hijo"));
            tm.setVacunaId(rs.getInt("id_vacuna"));
            tm.setResponsable(rs.getString("responsable"));
            
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    
      public ArrayList<Fecha> getFechas(int userId) throws SQLException, ClassNotFoundException {
        ArrayList<Fecha> lista = new ArrayList();
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        String sql = "select DISTINCT(coalesce(rv.fecha::varchar,'')) fecha "
                  
                   
                   + "from \"RegistroVacuna\" rv "
                   + "join \"Vacunas\" v on v.id_vacuna=rv.id_vacuna "
                   + "join \"Hijos\" h on rv.id_hijo = h.id_hijo "
                   + "where h.id_usuario = "+userId + "and rv.estado = 0";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            Fecha tm = new Fecha();
            
            tm.setFecha(rs.getString("fecha"));
            
            lista.add(tm);
        }
        conex.close();
        con.cerrarBD();
        return lista;
    }
    //==========================================================================
    
    public Usuario getUserById(int id) throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select nombre,correo from \"Usuarios\" where id_usuario = "+id);
        Usuario user = new Usuario();
        while (rs.next()) {
            user.setNombre(rs.getString("nombre"));
            user.setCorreo(rs.getString("correo"));
        }
        conex.close();
        con.cerrarBD();
        return user;
    }
    //==========================================================================
    
    public Usuario isUser(String correo) throws ClassNotFoundException, SQLException {
        conex = con.conectarBD();
        Usuario user = new Usuario();
        Statement st = conex.createStatement();
        ResultSet rs = st.executeQuery("select id_usuario, nombre, correo from \"Usuarios\" where correo = '"+correo+"'");
        if (rs.next()) {
            user.setId_usuario(rs.getInt(1));
            user.setNombre(rs.getString(2));
            user.setCorreo(rs.getString(3));
        }
        else {
            user = null;
        }
        return user;
    }
}
