/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities;

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
@Table(name = "users_histories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsersHistories.findAll", query = "SELECT u FROM UsersHistories u")
    , @NamedQuery(name = "UsersHistories.findByIdUs", query = "SELECT u FROM UsersHistories u WHERE u.idUs = :idUs")
    , @NamedQuery(name = "UsersHistories.findByDescripcion", query = "SELECT u FROM UsersHistories u WHERE u.descripcion = :descripcion")
    , @NamedQuery(name = "UsersHistories.findByNombreUs", query = "SELECT u FROM UsersHistories u WHERE u.nombreUs = :nombreUs")
    , @NamedQuery(name = "UsersHistories.findByPrioridad", query = "SELECT u FROM UsersHistories u WHERE u.prioridad = :prioridad")
    , @NamedQuery(name = "UsersHistories.findByTiempoEjecutado", query = "SELECT u FROM UsersHistories u WHERE u.tiempoEjecutado = :tiempoEjecutado")
    , @NamedQuery(name = "UsersHistories.findByEstado", query = "SELECT u FROM UsersHistories u WHERE u.estado = :estado")})
public class UsersHistories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_us")
    private Integer idUs;
    @Size(max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 255)
    @Column(name = "nombre_us")
    private String nombreUs;
    @Size(max = 255)
    @Column(name = "prioridad")
    private String prioridad;
    @Column(name = "tiempo_ejecutado")
    @Temporal(TemporalType.DATE)
    private Date tiempoEjecutado;
    @Size(max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Column(name = "id_user_creador")
    @ManyToOne
    private Integer idUserCreador;
    @Column(name = "id_user_editor")
    @ManyToOne
    private Integer idUserEditor;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "fecha_fin")
    private String fechaFin;

    public UsersHistories() {
    }

    public UsersHistories(Integer idUs) {
        this.idUs = idUs;
    }

    public Integer getIdUs() {
        return idUs;
    }

    public void setIdUs(Integer idUs) {
        this.idUs = idUs;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreUs() {
        return nombreUs;
    }

    public void setNombreUs(String nombreUs) {
        this.nombreUs = nombreUs;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Date getTiempoEjecutado() {
        return tiempoEjecutado;
    }

    public void setTiempoEjecutado(Date tiempoEjecutado) {
        this.tiempoEjecutado = tiempoEjecutado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Integer getIdUserCreador() {
        return idUserCreador;
    }

    public void setIdUserCreador(Integer idUserCreador) {
        this.idUserCreador = idUserCreador;
    }

    public Integer getIdUserEditor() {
        return idUserEditor;
    }

    public void setIdUserEditor(Integer idUserEditor) {
        this.idUserEditor = idUserEditor;
    }
    
    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        return "org.scrumRestfinal.entities.UsersHistories[ idUs=" + idUs + " ]";
    }
    
}
