package org.scrumRestfinal.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.Kambam;
import org.scrumRestfinal.entities.Proyectos;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-06T19:26:07")
@StaticMetamodel(Estados.class)
public class Estados_ { 

    public static volatile SingularAttribute<Estados, Integer> idEstado;
    public static volatile SingularAttribute<Estados, String> nombreEstado;
    public static volatile CollectionAttribute<Estados, Proyectos> proyectosCollection;
    public static volatile CollectionAttribute<Estados, Kambam> kambamCollection;

}