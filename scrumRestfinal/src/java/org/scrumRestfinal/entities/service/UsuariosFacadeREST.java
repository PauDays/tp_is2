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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.scrumRestfinal.entities.Usuarios;

/**
 *
 * @author Pauli
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

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Usuarios entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuarios find(@PathParam("id") Integer id) {
        return super.find(id);
    }

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
    
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(Usuarios u) throws ClassNotFoundException, SQLException {
        Usuarios user = new Usuarios();
        user = uservice.login(u.getUsuario(),u.getContrasenha()); //heeere 
       if (user==null){
     return "false";
       }
       else
       {
           return "true";
       }
       
     
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
        uservice.addUsuario(usuario);
        String result = "Usuario guardado: " + usuario.getUsuario()+", "+usuario.getMail();
        
        return result;
    }
     @DELETE
    @Path("/eliminarUsu")
    @Produces("text/plain")
    public String eliminarUsu(@QueryParam("usuario") String usuario) throws ClassNotFoundException, SQLException {
        uservice.eliminarUsu(usuario);
        String result = "Usuario eliminado correctamente!";
        return result;
    }
     @PUT
    @Path("/editarUsu")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String editarUsu(@QueryParam("usuario") String usuario, Usuarios user) throws SQLException, ClassNotFoundException {
        uservice.editarUsu(usuario, user);
        String result = "Usuario modificado correctamente!";
        return result;
    }
    
    
}
