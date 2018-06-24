package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.Proyectos;
import org.scrumRestfinal.entities.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-23T19:27:58")
@StaticMetamodel(Equipos.class)
public class Equipos_ { 

    public static volatile SingularAttribute<Equipos, Integer> idEquipo;
    public static volatile CollectionAttribute<Equipos, Proyectos> proyectosCollection;
    public static volatile SingularAttribute<Equipos, String> nombreEquipo;
    public static volatile SingularAttribute<Equipos, Usuarios> idUsuario;

}