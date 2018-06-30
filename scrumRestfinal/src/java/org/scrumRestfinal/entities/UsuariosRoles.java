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
@Table(name = "usuarios_roles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuariosRoles.findAll", query = "SELECT u FROM UsuariosRoles u")
    , @NamedQuery(name = "UsuariosRoles.findByIdUsuarioRol", query = "SELECT u FROM UsuariosRoles u WHERE u.idUsuarioRol = :idUsuarioRol")})
public class UsuariosRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_usuario_rol")
    private Integer idUsuarioRol;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Integer idRol;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Integer idUsuario;

    public UsuariosRoles() {
    }

    public UsuariosRoles(Integer idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public Integer getIdUsuarioRol() {
        return idUsuarioRol;
    }

    public void setIdUsuarioRol(Integer idUsuarioRol) {
        this.idUsuarioRol = idUsuarioRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuarioRol != null ? idUsuarioRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuariosRoles)) {
            return false;
        }
        UsuariosRoles other = (UsuariosRoles) object;
        if ((this.idUsuarioRol == null && other.idUsuarioRol != null) || (this.idUsuarioRol != null && !this.idUsuarioRol.equals(other.idUsuarioRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumRestfinal.entities.UsuariosRoles[ idUsuarioRol=" + idUsuarioRol + " ]";
    }
    
}
