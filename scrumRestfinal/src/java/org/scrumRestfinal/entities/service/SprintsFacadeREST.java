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
import org.scrumRestfinal.entities.Sprints;
import org.scrumRestfinal.entities.Usuarios;

/**
 *
 * @author Sara Chamorro
 */
@Stateless
@Path("org.scrumrestfinal.entities.sprints")
public class SprintsFacadeREST extends AbstractFacade<Sprints> {

    @PersistenceContext(unitName = "scrumRestfinalPU")
    private EntityManager em;

    public SprintsFacadeREST() {
        super(Sprints.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Sprints entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Sprints entity) {
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
    public Sprints find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprints> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Sprints> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    /*
    @GET
    @Path("/getTareas")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Sprints> getTareas() throws ClassNotFoundException, SQLException {
        return tareas.getTareas();
    }
    
    @POST
    @Path("/addTarea")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String addTarea(Sprints s) throws SQLException, ClassNotFoundException, ParseException {
        //System.out.println("-------------------------------------"+s.getFecha());
        tareas.addTareas(s);
        String result = "Usuario guardado: " + s.getNombreSprint();
        return result;
    }
    
    @PUT
    @Path("/editarTarea/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces("text/plain")
    public String editTarea(@PathParam("id") int id, Sprints tarea) throws SQLException, ClassNotFoundException, ParseException {
        System.out.println("Tarea id: " + id);
        tareas.editarTarea(id, tarea);
        String result = "Tarea modificada correctamente!";
        return result;
    }*/
}
