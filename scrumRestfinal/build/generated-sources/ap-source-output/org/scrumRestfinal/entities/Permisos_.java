package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.RolesPermisos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-09T12:05:39")
@StaticMetamodel(Permisos.class)
public class Permisos_ { 

    public static volatile SingularAttribute<Permisos, Integer> idPermiso;
    public static volatile SingularAttribute<Permisos, String> nombrePermiso;
    public static volatile CollectionAttribute<Permisos, RolesPermisos> rolesPermisosCollection;

}