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
@Table(name = "permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Permisos_1.findAll", query = "SELECT p FROM Permisos_1 p")
    , @NamedQuery(name = "Permisos_1.findByIdPermiso", query = "SELECT p FROM Permisos_1 p WHERE p.idPermiso = :idPermiso")
    , @NamedQuery(name = "Permisos_1.findByNombrePermiso", query = "SELECT p FROM Permisos_1 p WHERE p.nombrePermiso = :nombrePermiso")})
public class Permisos_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_permiso")
    private Integer idPermiso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_permiso")
    private String nombrePermiso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPermiso")
    private Collection<Roles_1> rolesCollection;

    public Permisos_1() {
    }

    public Permisos_1(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Permisos_1(Integer idPermiso, String nombrePermiso) {
        this.idPermiso = idPermiso;
        this.nombrePermiso = nombrePermiso;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getNombrePermiso() {
        return nombrePermiso;
    }

    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }

    @XmlTransient
    public Collection<Roles_1> getRolesCollection() {
        return rolesCollection;
    }

    public void setRolesCollection(Collection<Roles_1> rolesCollection) {
        this.rolesCollection = rolesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPermiso != null ? idPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permisos_1)) {
            return false;
        }
        Permisos_1 other = (Permisos_1) object;
        if ((this.idPermiso == null && other.idPermiso != null) || (this.idPermiso != null && !this.idPermiso.equals(other.idPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Permisos_1[ idPermiso=" + idPermiso + " ]";
    }
    
}
