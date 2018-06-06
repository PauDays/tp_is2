/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Pauli
 */
@Entity
@Table(name = "roles_permisos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolesPermisos.findAll", query = "SELECT r FROM RolesPermisos r")
    , @NamedQuery(name = "RolesPermisos.findByIdRolPermiso", query = "SELECT r FROM RolesPermisos r WHERE r.idRolPermiso = :idRolPermiso")})
public class RolesPermisos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_rol_permiso")
    private Integer idRolPermiso;
    @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso")
    @ManyToOne(optional = false)
    private Permisos idPermiso;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Roles idRol;

    public RolesPermisos() {
    }

    public RolesPermisos(Integer idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public Integer getIdRolPermiso() {
        return idRolPermiso;
    }

    public void setIdRolPermiso(Integer idRolPermiso) {
        this.idRolPermiso = idRolPermiso;
    }

    public Permisos getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Permisos idPermiso) {
        this.idPermiso = idPermiso;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRolPermiso != null ? idRolPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolesPermisos)) {
            return false;
        }
        RolesPermisos other = (RolesPermisos) object;
        if ((this.idRolPermiso == null && other.idRolPermiso != null) || (this.idRolPermiso != null && !this.idRolPermiso.equals(other.idRolPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumRestfinal.entities.RolesPermisos[ idRolPermiso=" + idRolPermiso + " ]";
    }
    
}
