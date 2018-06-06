package org.scrumRestfinal.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.Baklogs;
import org.scrumRestfinal.entities.Sprints;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-13T18:19:58")
@StaticMetamodel(UsersHistories.class)
public class UsersHistories_ { 

    public static volatile SingularAttribute<UsersHistories, String> descripcion;
    public static volatile CollectionAttribute<UsersHistories, Baklogs> baklogsCollection;
    public static volatile SingularAttribute<UsersHistories, Date> tiempoEjecutado;
    public static volatile SingularAttribute<UsersHistories, Integer> idUs;
    public static volatile SingularAttribute<UsersHistories, String> nombreUs;
    public static volatile SingularAttribute<UsersHistories, String> usuarioEditor;
    public static volatile SingularAttribute<UsersHistories, Sprints> idSprint;
    public static volatile SingularAttribute<UsersHistories, String> prioridad;
    public static volatile SingularAttribute<UsersHistories, String> usuarioCreador;

}