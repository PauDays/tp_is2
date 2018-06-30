/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "sprints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprints.findAll", query = "SELECT s FROM Sprints s")
    , @NamedQuery(name = "Sprints.findByIdSprint", query = "SELECT s FROM Sprints s WHERE s.idSprint = :idSprint")
    , @NamedQuery(name = "Sprints.findByDuracion", query = "SELECT s FROM Sprints s WHERE s.duracion = :duracion")
    , @NamedQuery(name = "Sprints.findByNombreSprint", query = "SELECT s FROM Sprints s WHERE s.nombreSprint = :nombreSprint")
    , @NamedQuery(name = "Sprints.findByFecha", query = "SELECT s FROM Sprints s WHERE s.fecha = :fecha")
    , @NamedQuery(name = "Sprints.findByEstado", query = "SELECT s FROM Sprints s WHERE s.estado = :estado")
    , @NamedQuery(name = "Sprints.findByFechaFin", query = "SELECT s FROM Sprints s WHERE s.fechaFin = :fechaFin")})
public class Sprints implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Column(name = "duracion")
    private Integer duracion;
    @Size(max = 255)
    @Column(name = "nombre_sprint")
    private String nombreSprint;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "fecha_fin")
    private String fechaFin;
    @OneToMany(mappedBy = "idSprint")
    private Collection<UsersHistories> usersHistoriesCollection;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuarios idUsuario;

    public Sprints() {
    }

    public Sprints(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getNombreSprint() {
        return nombreSprint;
    }

    public void setNombreSprint(String nombreSprint) {
        this.nombreSprint = nombreSprint;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public Collection<UsersHistories> getUsersHistoriesCollection() {
        return usersHistoriesCollection;
    }

    public void setUsersHistoriesCollection(Collection<UsersHistories> usersHistoriesCollection) {
        this.usersHistoriesCollection = usersHistoriesCollection;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSprint != null ? idSprint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprints)) {
            return false;
        }
        Sprints other = (Sprints) object;
        if ((this.idSprint == null && other.idSprint != null) || (this.idSprint != null && !this.idSprint.equals(other.idSprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumRestfinal.entities.Sprints[ idSprint=" + idSprint + " ]";
    }
    
}
