package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.Roles;
import org.scrumRestfinal.entities.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-23T19:27:58")
@StaticMetamodel(UsuariosRoles.class)
public class UsuariosRoles_ { 

    public static volatile SingularAttribute<UsuariosRoles, Integer> idUsuarioRol;
    public static volatile SingularAttribute<UsuariosRoles, Roles> idRol;
    public static volatile SingularAttribute<UsuariosRoles, Usuarios> idUsuario;

}