/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities.service;

import java.sql.SQLException;
import java.text.ParseException;
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
import org.scrumRestfinal.entities.Sprints;
import org.scrumRestfinal.entities.UsersHistories;

/**
 *
 * @author Pauli
 */
@javax.ejb.Stateless
@Path("org.scrumrestfinal.entities.usershistories")
public class UsersHistoriesFacadeREST extends AbstractFacade<UsersHistories> {

    @PersistenceContext(unitName = "scrumRestfinalPU")
    private EntityManager em;

    public UsersHistoriesFacadeREST() {
        super(UsersHistories.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(UsersHistories entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, UsersHistories entity) {
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
    public UsersHistories find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UsersHistories> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<UsersHistories> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
    tareaServicio tareas = new tareaServicio();
    //////////////////////////////////////////////7
    
    @GET
    @Path("/getTareas")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UsersHistories> getTareas() throws ClassNotFoundException, SQLException {
        return tareas.getTareas();
    }
    
    @POST
    @Path("/addTarea")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String addTarea(UsersHistories s) throws SQLException, ClassNotFoundException, ParseException {
        //System.out.println("-------------------------------------"+s.getFecha());
        String resultado = tareas.addTareas(s);
        if(resultado != "Insertado"){
            resultado = "No existe sprint";
        }
        return resultado;
    }
    
    @PUT
    @Path("/editarTarea/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces("text/plain")
    public String editTarea(@PathParam("id") int id, UsersHistories tarea) throws SQLException, ClassNotFoundException, ParseException {
        System.out.println("Tarea id: " + id);
        
        String result = tareas.editarTarea(id, tarea);
        if(result != "Actualizado"){
            result = "No existe sprint";
        }
        
        return result;
    }
    
    
    @GET
    @Path("/idUsuarioTareas/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ArrayList<UsersHistories> findId(@PathParam("id") Integer id) throws SQLException, ClassNotFoundException {
        System.out.println("Tarea id: " + id);
        ArrayList<UsersHistories> resultado = tareas.getUsuarioTareas(id);
        return resultado;
    }
    
    @DELETE
    @Path("eliminarTarea/{id}")
    @Produces("text/plain")
    public String remove(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        System.err.println("tarea: "+id);
        tareas.eliminarTarea(id);
        
        String result = "Eliminado";
        return result;   
}
    
}
