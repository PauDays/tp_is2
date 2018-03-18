/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumis2.entities;

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
    @NamedQuery(name = "Sprints_1.findAll", query = "SELECT s FROM Sprints_1 s")
    , @NamedQuery(name = "Sprints_1.findByIdSprint", query = "SELECT s FROM Sprints_1 s WHERE s.idSprint = :idSprint")
    , @NamedQuery(name = "Sprints_1.findByNombreSprint", query = "SELECT s FROM Sprints_1 s WHERE s.nombreSprint = :nombreSprint")
    , @NamedQuery(name = "Sprints_1.findByDuracion", query = "SELECT s FROM Sprints_1 s WHERE s.duracion = :duracion")})
public class Sprints_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_sprint")
    private String nombreSprint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duracion")
    private int duracion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSprint")
    private Collection<UsersHistories_1> usersHistoriesCollection;

    public Sprints_1() {
    }

    public Sprints_1(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Sprints_1(Integer idSprint, String nombreSprint, int duracion) {
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
    public Collection<UsersHistories_1> getUsersHistoriesCollection() {
        return usersHistoriesCollection;
    }

    public void setUsersHistoriesCollection(Collection<UsersHistories_1> usersHistoriesCollection) {
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
        if (!(object instanceof Sprints_1)) {
            return false;
        }
        Sprints_1 other = (Sprints_1) object;
        if ((this.idSprint == null && other.idSprint != null) || (this.idSprint != null && !this.idSprint.equals(other.idSprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Sprints_1[ idSprint=" + idSprint + " ]";
    }
    
}
