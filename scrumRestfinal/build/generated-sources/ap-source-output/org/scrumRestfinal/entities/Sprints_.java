package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.UsersHistories;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-13T18:19:59")
@StaticMetamodel(Sprints.class)
public class Sprints_ { 

    public static volatile SingularAttribute<Sprints, String> nombreSprint;
    public static volatile CollectionAttribute<Sprints, UsersHistories> usersHistoriesCollection;
    public static volatile SingularAttribute<Sprints, Integer> idSprint;
    public static volatile SingularAttribute<Sprints, Integer> duracion;

}