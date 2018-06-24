package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.Permisos;
import org.scrumRestfinal.entities.Roles;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-23T19:27:58")
@StaticMetamodel(RolesPermisos.class)
public class RolesPermisos_ { 

    public static volatile SingularAttribute<RolesPermisos, Roles> idRol;
    public static volatile SingularAttribute<RolesPermisos, Permisos> idPermiso;
    public static volatile SingularAttribute<RolesPermisos, Integer> idRolPermiso;

}