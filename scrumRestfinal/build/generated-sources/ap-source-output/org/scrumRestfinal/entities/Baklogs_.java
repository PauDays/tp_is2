package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.Kambam;
import org.scrumRestfinal.entities.Proyectos;
import org.scrumRestfinal.entities.UsersHistories;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-13T18:19:58")
@StaticMetamodel(Baklogs.class)
public class Baklogs_ { 

    public static volatile SingularAttribute<Baklogs, String> nombreBacklog;
    public static volatile CollectionAttribute<Baklogs, Proyectos> proyectosCollection;
    public static volatile SingularAttribute<Baklogs, UsersHistories> idUs;
    public static volatile CollectionAttribute<Baklogs, Kambam> kambamCollection;
    public static volatile SingularAttribute<Baklogs, Integer> idBacklog;

}