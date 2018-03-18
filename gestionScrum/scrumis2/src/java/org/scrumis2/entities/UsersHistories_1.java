/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumis2.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sara Chamorro
 */
@Entity
@Table(name = "users_histories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersHistories_1.findAll", query = "SELECT u FROM UsersHistories_1 u")
    , @NamedQuery(name = "UsersHistories_1.findByIdUs", query = "SELECT u FROM UsersHistories_1 u WHERE u.idUs = :idUs")
    , @NamedQuery(name = "UsersHistories_1.findByNombreUs", query = "SELECT u FROM UsersHistories_1 u WHERE u.nombreUs = :nombreUs")
    , @NamedQuery(name = "UsersHistories_1.findByDescripcion", query = "SELECT u FROM UsersHistories_1 u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "UsersHistories_1.findByTiempoEjecutado", query = "SELECT u FROM UsersHistories_1 u WHERE u.tiempoEjecutado = :tiempoEjecutado")
    , @NamedQuery(name = "UsersHistories_1.findByPrioridad", query = "SELECT u FROM UsersHistories_1 u WHERE u.prioridad = :prioridad")
    , @NamedQuery(name = "UsersHistories_1.findByUsuarioCreador", query = "SELECT u FROM UsersHistories_1 u WHERE u.usuarioCreador = :usuarioCreador")
    , @NamedQuery(name = "UsersHistories_1.findByUsuarioEditor", query = "SELECT u FROM UsersHistories_1 u WHERE u.usuarioEditor = :usuarioEditor")})
public class UsersHistories_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_us")
    private Integer idUs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_us")
    private String nombreUs;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tiempo_ejecutado")
    @Temporal(TemporalType.DATE)
    private Date tiempoEjecutado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "prioridad")
    private String prioridad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usuario_creador")
    private String usuarioCreador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "usuario_editor")
    private String usuarioEditor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUs")
    private Collection<Baklogs_1> baklogsCollection;
    @JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint")
    @ManyToOne(optional = false)
    private Sprints_1 idSprint;

    public UsersHistories_1() {
    }

    public UsersHistories_1(Integer idUs) {
        this.idUs = idUs;
    }

    public UsersHistories_1(Integer idUs, String nombreUs, String descripcion, Date tiempoEjecutado, String prioridad, String usuarioCreador, String usuarioEditor) {
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
    public Collection<Baklogs_1> getBaklogsCollection() {
        return baklogsCollection;
    }

    public void setBaklogsCollection(Collection<Baklogs_1> baklogsCollection) {
        this.baklogsCollection = baklogsCollection;
    }

    public Sprints_1 getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Sprints_1 idSprint) {
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
        if (!(object instanceof UsersHistories_1)) {
            return false;
        }
        UsersHistories_1 other = (UsersHistories_1) object;
        if ((this.idUs == null && other.idUs != null) || (this.idUs != null && !this.idUs.equals(other.idUs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.UsersHistories_1[ idUs=" + idUs + " ]";
    }
    
}
