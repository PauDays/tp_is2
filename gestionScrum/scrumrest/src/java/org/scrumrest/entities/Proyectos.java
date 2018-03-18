/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumrest.entities;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pauli
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p")
    , @NamedQuery(name = "Proyectos.findByIdProyecto", query = "SELECT p FROM Proyectos p WHERE p.idProyecto = :idProyecto")
    , @NamedQuery(name = "Proyectos.findByNombreProyecto", query = "SELECT p FROM Proyectos p WHERE p.nombreProyecto = :nombreProyecto")
    , @NamedQuery(name = "Proyectos.findByDescripcion", query = "SELECT p FROM Proyectos p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Proyectos.findByFechaInicio", query = "SELECT p FROM Proyectos p WHERE p.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Proyectos.findByFechaFin", query = "SELECT p FROM Proyectos p WHERE p.fechaFin = :fechaFin")
    , @NamedQuery(name = "Proyectos.findByUsuarioCreador", query = "SELECT p FROM Proyectos p WHERE p.usuarioCreador = :usuarioCreador")
    , @NamedQuery(name = "Proyectos.findByDuracion", query = "SELECT p FROM Proyectos p WHERE p.duracion = :duracion")
    , @NamedQuery(name = "Proyectos.findByEstado", query = "SELECT p FROM Proyectos p WHERE p.estado = :estado")})
public class Proyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_proyecto")
    private Integer idProyecto;
    @Basic(optional = false)
    @Column(name = "nombre_proyecto")
    private String nombreProyecto;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "usuario_creador")
    private String usuarioCreador;
    @Basic(optional = false)
    @Column(name = "duracion")
    private int duracion;
    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "id_backlog", referencedColumnName = "id_backlog")
    @ManyToOne(optional = false)
    private Baklogs idBacklog;
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    @ManyToOne(optional = false)
    private Equipos idEquipo;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estados idEstado;

    public Proyectos() {
    }

    public Proyectos(Integer idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyectos(Integer idProyecto, String nombreProyecto, String descripcion, Date fechaInicio, Date fechaFin, String usuarioCreador, int duracion, String estado) {
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

    public Baklogs getIdBacklog() {
        return idBacklog;
    }

    public void setIdBacklog(Baklogs idBacklog) {
        this.idBacklog = idBacklog;
    }

    public Equipos getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Equipos idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
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
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumrest.entities.Proyectos[ idProyecto=" + idProyecto + " ]";
    }
    
}
