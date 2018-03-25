/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumrest2.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pauli
 */
@Entity
@Table(name = "sprints")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprints.findAll", query = "SELECT s FROM Sprints s")
    , @NamedQuery(name = "Sprints.findByIdSprint", query = "SELECT s FROM Sprints s WHERE s.idSprint = :idSprint")
    , @NamedQuery(name = "Sprints.findByNombreSprint", query = "SELECT s FROM Sprints s WHERE s.nombreSprint = :nombreSprint")
    , @NamedQuery(name = "Sprints.findByDuracion", query = "SELECT s FROM Sprints s WHERE s.duracion = :duracion")})
public class Sprints implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Basic(optional = false)
    @Column(name = "nombre_sprint")
    private String nombreSprint;
    @Basic(optional = false)
    @Column(name = "duracion")
    private int duracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSprint")
    private Collection<UsersHistories> usersHistoriesCollection;

    public Sprints() {
    }

    public Sprints(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Sprints(Integer idSprint, String nombreSprint, int duracion) {
        this.idSprint = idSprint;
        this.nombreSprint = nombreSprint;
        this.duracion = duracion;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public String getNombreSprint() {
        return nombreSprint;
    }

    public void setNombreSprint(String nombreSprint) {
        this.nombreSprint = nombreSprint;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    @XmlTransient
    public Collection<UsersHistories> getUsersHistoriesCollection() {
        return usersHistoriesCollection;
    }

    public void setUsersHistoriesCollection(Collection<UsersHistories> usersHistoriesCollection) {
        this.usersHistoriesCollection = usersHistoriesCollection;
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
        return "org.scrumrest2.entities.Sprints[ idSprint=" + idSprint + " ]";
    }
    
}
