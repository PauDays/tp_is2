/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumis2.entities.service;

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
        resources.add(org.scrumis2.entities.service.BaklogsFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Baklogs_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.EquiposFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Equipos_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.EstadosFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Estados_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.KambamFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Kambam_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.LoginFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Login_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.PermisosFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Permisos_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.ProyectosFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Proyectos_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.RolesFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Roles_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.SprintsFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Sprints_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.UsersHistoriesFacadeREST.class);
        resources.add(org.scrumis2.entities.service.UsersHistories_1FacadeREST.class);
        resources.add(org.scrumis2.entities.service.UsuariosFacadeREST.class);
        resources.add(org.scrumis2.entities.service.Usuarios_1FacadeREST.class);
    }
    
}
