package org.scrumRestfinal.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-23T19:27:58")
@StaticMetamodel(Sprints.class)
public class Sprints_ { 

    public static volatile SingularAttribute<Sprints, String> nombreSprint;
    public static volatile SingularAttribute<Sprints, Date> fecha;
    public static volatile SingularAttribute<Sprints, Boolean> estado;
    public static volatile SingularAttribute<Sprints, Integer> idUsuario;
    public static volatile SingularAttribute<Sprints, Integer> idSprint;
    public static volatile SingularAttribute<Sprints, Integer> duracion;

}