package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.Equipos;
import org.scrumRestfinal.entities.Sprints;
import org.scrumRestfinal.entities.UsuariosRoles;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T19:26:07")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile SingularAttribute<Usuarios, String> mail;
    public static volatile CollectionAttribute<Usuarios, Equipos> equiposCollection;
    public static volatile SingularAttribute<Usuarios, Integer> idUsuario;
    public static volatile SingularAttribute<Usuarios, String> apellido;
    public static volatile CollectionAttribute<Usuarios, UsuariosRoles> usuariosRolesCollection;
    public static volatile SingularAttribute<Usuarios, String> usuario;
    public static volatile CollectionAttribute<Usuarios, Sprints> sprintsCollection;
    public static volatile SingularAttribute<Usuarios, String> nombre;
    public static volatile SingularAttribute<Usuarios, String> contrasenha;

}