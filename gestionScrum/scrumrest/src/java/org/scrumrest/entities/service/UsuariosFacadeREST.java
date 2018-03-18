/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumrest.entities.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import org.scrumrest.entities.Usuarios;

/**
 *
 * @author pauli
 */
@javax.ejb.Stateless
@Path("org.scrumrest.entities.usuarios")
public class UsuariosFacadeREST extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "scrumrestPU")
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
        
        return em = Persistence.createEntityManagerFactory("scrumrestPU").createEntityManager();
    }
    ////////////////////////////////77
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
    public Usuarios login(Usuarios u) throws ClassNotFoundException, SQLException {
        Usuarios user = new Usuarios();
        user = uservice.login(u.getContrasenha());
       
     return user;
     
    }
  /*  @POST
    @Path("/addUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String addUser(Usuarios u) throws SQLException, ClassNotFoundException {
        Usuarios usuario = new Usuarios();
        usuario.setIdUsuario(u.getIdUsuario());
        usuario.setNombre(u.getNombre());
        //usuario.setApellido(u.getApellido());
        usuario.setTelefono(u.getTelefono());
        usuario.setDireccion(u.getDireccion());
        usuario.setMail(u.getMail());
        usuario.setContrasenha(u.getContrasenha());
        uservice.addUsuario(usuario);
        String result = "Usuario guardado: " + usuario.getNombre()+" "+usuario.getApellido()+", "+usuario.getMail();
        
        return result;
    }*/
    
    
    
    
}
