/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumrest.entities.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author pauli
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.scrumrest.entities.service.BaklogsFacadeREST.class);
        resources.add(org.scrumrest.entities.service.EquiposFacadeREST.class);
        resources.add(org.scrumrest.entities.service.EstadosFacadeREST.class);
        resources.add(org.scrumrest.entities.service.KambamFacadeREST.class);
        resources.add(org.scrumrest.entities.service.PermisosFacadeREST.class);
        resources.add(org.scrumrest.entities.service.ProyectosFacadeREST.class);
        resources.add(org.scrumrest.entities.service.RolesFacadeREST.class);
        resources.add(org.scrumrest.entities.service.SprintsFacadeREST.class);
        resources.add(org.scrumrest.entities.service.UsersHistoriesFacadeREST.class);
        resources.add(org.scrumrest.entities.service.UsuariosFacadeREST.class);
    }
    
}
