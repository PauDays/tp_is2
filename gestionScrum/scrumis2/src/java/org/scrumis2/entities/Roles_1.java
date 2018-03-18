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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles_1.findAll", query = "SELECT r FROM Roles_1 r")
    , @NamedQuery(name = "Roles_1.findByIdRol", query = "SELECT r FROM Roles_1 r WHERE r.idRol = :idRol")
    , @NamedQuery(name = "Roles_1.findByNombreRol", query = "SELECT r FROM Roles_1 r WHERE r.nombreRol = :nombreRol")})
public class Roles_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_rol")
    private Integer idRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_rol")
    private String nombreRol;
    @JoinColumn(name = "id_permiso", referencedColumnName = "id_permiso")
    @ManyToOne(optional = false)
    private Permisos_1 idPermiso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRol")
    private Collection<Usuarios_1> usuariosCollection;

    public Roles_1() {
    }

    public Roles_1(Integer idRol) {
        this.idRol = idRol;
    }

    public Roles_1(Integer idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Permisos_1 getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Permisos_1 idPermiso) {
        this.idPermiso = idPermiso;
    }

    @XmlTransient
    public Collection<Usuarios_1> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios_1> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles_1)) {
            return false;
        }
        Roles_1 other = (Roles_1) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Roles_1[ idRol=" + idRol + " ]";
    }
    
}
