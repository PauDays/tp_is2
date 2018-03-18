/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumis2.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sara Chamorro
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectos_1.findAll", query = "SELECT p FROM Proyectos_1 p")
    , @NamedQuery(name = "Proyectos_1.findByIdProyecto", query = "SELECT p FROM Proyectos_1 p WHERE p.idProyecto = :idProyecto")
    , @NamedQuery(name = "Proyectos_1.findByNombreProyecto", query = "SELECT p FROM Proyectos_1 p WHERE p.nombreProyecto = :nombreProyecto")
    , @NamedQuery(name = "Proyectos_1.findByDescripcion", query = "SELECT p FROM Proyectos_1 p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Proyectos_1.findByFechaInicio", query = "SELECT p FROM Proyectos_1 p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Proyectos_1.findByFechaFin", query = "SELECT p FROM Proyectos_1 p WHERE p.fechaFin = :fechaFin")
    , @NamedQuery(name = "Proyectos_1.findByUsuarioCreador", query = "SELECT p FROM Proyectos_1 p WHERE p.usuarioCreador = :usuarioCreador")
    , @NamedQuery(name = "Proyectos_1.findByDuracion", query = "SELECT p FROM Proyectos_1 p WHERE p.duracion = :duracion")
    , @NamedQuery(name = "Proyectos_1.findByEstado", query = "SELECT p FROM Proyectos_1 p WHERE p.estado = :estado")})
public class Proyectos_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usuario_creador")
    private String usuarioCreador;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    private int duracion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_backlog", referencedColumnName = "id_backlog")
    @ManyToOne(optional = false)
    private Baklogs_1 idBacklog;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false)
    private Equipos_1 idEquipo;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estados_1 idEstado;

    public Proyectos_1() {
    }

    public Proyectos_1(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyectos_1(Integer idProyecto, String nombreProyecto, String descripcion, Date fechaInicio, Date fechaFin, String usuarioCreador, int duracion, String estado) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuarioCreador = usuarioCreador;
        this.duracion = duracion;
        this.estado = estado;
    }

    public Integer getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Baklogs_1 getIdBacklog() {
        return idBacklog;
    }

    public void setIdBacklog(Baklogs_1 idBacklog) {
        this.idBacklog = idBacklog;
    }

    public Equipos_1 getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipos_1 idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Estados_1 getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados_1 idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos_1)) {
            return false;
        }
        Proyectos_1 other = (Proyectos_1) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Proyectos_1[ idProyecto=" + idProyecto + " ]";
    }
    
}
