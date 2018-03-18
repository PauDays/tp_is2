/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumrest.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pauli
 */
@Entity
@Table(name = "users_histories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersHistories.findAll", query = "SELECT u FROM UsersHistories u")
    , @NamedQuery(name = "UsersHistories.findByIdUs", query = "SELECT u FROM UsersHistories u WHERE u.idUs = :idUs")
    , @NamedQuery(name = "UsersHistories.findByNombreUs", query = "SELECT u FROM UsersHistories u WHERE u.nombreUs = :nombreUs")
    , @NamedQuery(name = "UsersHistories.findByDescripcion", query = "SELECT u FROM UsersHistories u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "UsersHistories.findByTiempoEjecutado", query = "SELECT u FROM UsersHistories u WHERE u.tiempoEjecutado = :tiempoEjecutado")
    , @NamedQuery(name = "UsersHistories.findByPrioridad", query = "SELECT u FROM UsersHistories u WHERE u.prioridad = :prioridad")
    , @NamedQuery(name = "UsersHistories.findByUsuarioCreador", query = "SELECT u FROM UsersHistories u WHERE u.usuarioCreador = :usuarioCreador")
    , @NamedQuery(name = "UsersHistories.findByUsuarioEditor", query = "SELECT u FROM UsersHistories u WHERE u.usuarioEditor = :usuarioEditor")})
public class UsersHistories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_us")
    private Integer idUs;
    @Basic(optional = false)
    @Column(name = "nombre_us")
    private String nombreUs;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "tiempo_ejecutado")
    @Temporal(TemporalType.DATE)
    private Date tiempoEjecutado;
    @Basic(optional = false)
    @Column(name = "prioridad")
    private String prioridad;
    @Basic(optional = false)
    @Column(name = "usuario_creador")
    private String usuarioCreador;
    @Basic(optional = false)
    @Column(name = "usuario_editor")
    private String usuarioEditor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUs")
    private Collection<Baklogs> baklogsCollection;
    @JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint")
    @ManyToOne(optional = false)
    private Sprints idSprint;

    public UsersHistories() {
    }

    public UsersHistories(Integer idUs) {
        this.idUs = idUs;
    }

    public UsersHistories(Integer idUs, String nombreUs, String descripcion, Date tiempoEjecutado, String prioridad, String usuarioCreador, String usuarioEditor) {
        this.idUs = idUs;
        this.nombreUs = nombreUs;
        this.descripcion = descripcion;
        this.tiempoEjecutado = tiempoEjecutado;
        this.prioridad = prioridad;
        this.usuarioCreador = usuarioCreador;
        this.usuarioEditor = usuarioEditor;
    }

    public Integer getIdUs() {
        return idUs;
    }

    public void setIdUs(Integer idUs) {
        this.idUs = idUs;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getTiempoEjecutado() {
        return tiempoEjecutado;
    }

    public void setTiempoEjecutado(Date tiempoEjecutado) {
        this.tiempoEjecutado = tiempoEjecutado;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(String usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public String getUsuarioEditor() {
        return usuarioEditor;
    }

    public void setUsuarioEditor(String usuarioEditor) {
        this.usuarioEditor = usuarioEditor;
    }

    @XmlTransient
    public Collection<Baklogs> getBaklogsCollection() {
        return baklogsCollection;
    }

    public void setBaklogsCollection(Collection<Baklogs> baklogsCollection) {
        this.baklogsCollection = baklogsCollection;
    }

    public Sprints getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Sprints idSprint) {
        this.idSprint = idSprint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUs != null ? idUs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsersHistories)) {
            return false;
        }
        UsersHistories other = (UsersHistories) object;
        if ((this.idUs == null && other.idUs != null) || (this.idUs != null && !this.idUs.equals(other.idUs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumrest.entities.UsersHistories[ idUs=" + idUs + " ]";
    }
    
}
