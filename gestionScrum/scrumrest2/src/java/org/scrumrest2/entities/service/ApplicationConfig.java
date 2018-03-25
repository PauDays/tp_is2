/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumrest2.entities.service;

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
        resources.add(org.scrumrest2.entities.service.BaklogsFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.EquiposFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.EstadosFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.KambamFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.PermisosFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.ProyectosFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.RolesFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.RolesPermisosFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.SprintsFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.UsersHistoriesFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.UsuariosFacadeREST.class);
        resources.add(org.scrumrest2.entities.service.UsuariosRolesFacadeREST.class);
    }
    
}
