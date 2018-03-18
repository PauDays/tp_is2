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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios_1.findAll", query = "SELECT u FROM Usuarios_1 u")
    , @NamedQuery(name = "Usuarios_1.findByIdUsuario", query = "SELECT u FROM Usuarios_1 u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuarios_1.findByNombre", query = "SELECT u FROM Usuarios_1 u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuarios_1.findByApellido", query = "SELECT u FROM Usuarios_1 u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "Usuarios_1.findByTelefono", query = "SELECT u FROM Usuarios_1 u WHERE u.telefono = :telefono")
    , @NamedQuery(name = "Usuarios_1.findByDireccion", query = "SELECT u FROM Usuarios_1 u WHERE u.direccion = :direccion")
    , @NamedQuery(name = "Usuarios_1.findByMail", query = "SELECT u FROM Usuarios_1 u WHERE u.mail = :mail")})
public class Usuarios_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mail")
    private String mail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Login_1> loginCollection;
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne(optional = false)
    private Roles_1 idRol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private Collection<Equipos_1> equiposCollection;

    public Usuarios_1() {
    }

    public Usuarios_1(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios_1(Integer idUsuario, String nombre, String apellido, String telefono, String direccion, String mail) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.mail = mail;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @XmlTransient
    public Collection<Login_1> getLoginCollection() {
        return loginCollection;
    }

    public void setLoginCollection(Collection<Login_1> loginCollection) {
        this.loginCollection = loginCollection;
    }

    public Roles_1 getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles_1 idRol) {
        this.idRol = idRol;
    }

    @XmlTransient
    public Collection<Equipos_1> getEquiposCollection() {
        return equiposCollection;
    }

    public void setEquiposCollection(Collection<Equipos_1> equiposCollection) {
        this.equiposCollection = equiposCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios_1)) {
            return false;
        }
        Usuarios_1 other = (Usuarios_1) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Usuarios_1[ idUsuario=" + idUsuario + " ]";
    }
    
}
