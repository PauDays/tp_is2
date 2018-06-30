package org.scrumRestfinal.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-30T12:25:27")
@StaticMetamodel(UsersHistories.class)
public class UsersHistories_ { 

    public static volatile SingularAttribute<UsersHistories, String> descripcion;
    public static volatile SingularAttribute<UsersHistories, String> fecha;
    public static volatile SingularAttribute<UsersHistories, String> estado;
    public static volatile SingularAttribute<UsersHistories, Integer> idUserEditor;
    public static volatile SingularAttribute<UsersHistories, Date> tiempoEjecutado;
    public static volatile SingularAttribute<UsersHistories, Integer> idUs;
    public static volatile SingularAttribute<UsersHistories, String> nombreUs;
    public static volatile SingularAttribute<UsersHistories, Integer> idSprint;
    public static volatile SingularAttribute<UsersHistories, Integer> idUserCreador;
    public static volatile SingularAttribute<UsersHistories, String> fechaFin;
    public static volatile SingularAttribute<UsersHistories, String> prioridad;

}