package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.RolesPermisos;
import org.scrumRestfinal.entities.UsuariosRoles;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-07-01T11:16:07")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, Integer> idRol;
    public static volatile SingularAttribute<Roles, String> nombreRol;
    public static volatile CollectionAttribute<Roles, UsuariosRoles> usuariosRolesCollection;
    public static volatile CollectionAttribute<Roles, RolesPermisos> rolesPermisosCollection;

}