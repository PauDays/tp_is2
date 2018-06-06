package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.UsersHistories;
import org.scrumRestfinal.entities.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T19:26:07")
@StaticMetamodel(Sprints.class)
public class Sprints_ { 

    public static volatile SingularAttribute<Sprints, String> nombreSprint;
    public static volatile CollectionAttribute<Sprints, UsersHistories> usersHistoriesCollection;
    public static volatile SingularAttribute<Sprints, Usuarios> idUsuario;
    public static volatile SingularAttribute<Sprints, Integer> duracion;
    public static volatile SingularAttribute<Sprints, Integer> idSprint;

}