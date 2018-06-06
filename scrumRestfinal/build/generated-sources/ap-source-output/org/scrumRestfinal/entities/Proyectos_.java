package org.scrumRestfinal.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.scrumRestfinal.entities.Baklogs;
import org.scrumRestfinal.entities.Equipos;
import org.scrumRestfinal.entities.Estados;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-13T18:19:59")
@StaticMetamodel(Proyectos.class)
public class Proyectos_ { 

    public static volatile SingularAttribute<Proyectos, String> descripcion;
    public static volatile SingularAttribute<Proyectos, Integer> idProyecto;
    public static volatile SingularAttribute<Proyectos, String> estado;
    public static volatile SingularAttribute<Proyectos, Equipos> idEquipo;
    public static volatile SingularAttribute<Proyectos, Estados> idEstado;
    public static volatile SingularAttribute<Proyectos, Date> fechaInicio;
    public static volatile SingularAttribute<Proyectos, String> nombreProyecto;
    public static volatile SingularAttribute<Proyectos, Integer> duracion;
    public static volatile SingularAttribute<Proyectos, Date> fechaFin;
    public static volatile SingularAttribute<Proyectos, String> usuarioCreador;
    public static volatile SingularAttribute<Proyectos, Baklogs> idBacklog;

}