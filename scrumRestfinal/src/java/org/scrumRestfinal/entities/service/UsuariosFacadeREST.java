/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.scrumRestfinal.entities.Roles;
import org.scrumRestfinal.entities.Usuarios;
import org.scrumRestfinal.entities.UsuariosRoles;

/**
 *
 * @author Sara Chamorro
 */
@Stateless
@Path("org.scrumrestfinal.entities.usuarios")
public class UsuariosFacadeREST extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "scrumRestfinalPU")
    private EntityManager em;

    public UsuariosFacadeREST() {
        super(Usuarios.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuarios entity) {
        super.create(entity);
    }
/*
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuarios entity) {
        super.edit(entity);
    }
/*
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) throws ClassNotFoundException, SQLException {
        System.err.println("usuario: "+id);
        uservice.eliminarUsu(id);
        String result = "Usuario eliminado correctamente!";
        System.err.println(result);
    

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuarios find(@PathParam("id") Integer id) {
        return super.find(id);
    }
/*
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuarios> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuarios> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
*/
    @Override
    protected EntityManager getEntityManager() {
           return em=Persistence.createEntityManagerFactory("scrumRestfinalPU").createEntityManager();
    }
    
     //////
    usuSer uservice = new usuSer();
    //////////////////////////////////////////////7
    @GET
    @Path("/getusers")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Usuarios> getUsers() throws ClassNotFoundException, SQLException {
        return uservice.getUsers();
    }
    
    @GET
    @Path("/getroles")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Roles> getRoles() throws ClassNotFoundException, SQLException {
        return uservice.getUsersRol();
    }
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuarios login(Usuarios u) throws ClassNotFoundException, SQLException {
        Usuarios user = new Usuarios();
        user = uservice.login(u.getUsuario(),u.getContrasenha()); //heeere 
       if (user==null){
            return null;
       }
       else
       {
           return user;
       }
       
     
    }
    
    @POST
    @Path("/addRolUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String addRolUser(UsuariosRoles ur) throws SQLException, ClassNotFoundException {
       
        uservice.addUsuariosRol(ur);
        String result = "Usuario guardado: " + ur.getIdUsuario();
        
        return result;
    }
    
     @POST
    @Path("/adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String addUser(Usuarios u) throws SQLException, ClassNotFoundException {
        Usuarios usuario = new Usuarios();
        usuario.setNombre(u.getNombre());
        usuario.setApellido(u.getApellido());
        usuario.setUsuario(u.getUsuario());
        usuario.setMail(u.getMail());
        usuario.setContrasenha(u.getContrasenha());
        usuario.setRolusu(u.getRolusu());
        //aca hay que ver si el rol existe
        String test =uservice.addUsuario(usuario);
        
        ///////////////////////////////////////7
        return test;
    }
    
    
   
    
    @PUT
    @Path("/editarusuario/{usuario}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces("text/plain")
    public String editUsuario(@PathParam("usuario") String usuario, Usuarios usuarios) throws SQLException, ClassNotFoundException {
        System.out.println("Nombre: " + usuario);
        uservice.editarUsu(usuario, usuarios);
        String result = "Usuario modificado correctamente!";
        return result;
    }
    
    @DELETE
    @Path("eliminarusuario/{id}")
    public String remove(@PathParam("id") int id_usuario) throws ClassNotFoundException, SQLException {
        System.err.println("usuario: "+id_usuario);
        uservice.eliminarUsu(id_usuario);
        String result = "Usuario eliminado correctamente!";
        return result;
    
   
    
}
}