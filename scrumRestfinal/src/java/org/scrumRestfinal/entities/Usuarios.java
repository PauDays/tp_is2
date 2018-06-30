/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario")
    , @NamedQuery(name = "Usuarios.findByApellido", query = "SELECT u FROM Usuarios u WHERE u.apellido = :apellido")
    , @NamedQuery(name = "Usuarios.findByContrasenha", query = "SELECT u FROM Usuarios u WHERE u.contrasenha = :contrasenha")
    , @NamedQuery(name = "Usuarios.findByMail", query = "SELECT u FROM Usuarios u WHERE u.mail = :mail")
    , @NamedQuery(name = "Usuarios.findByNombre", query = "SELECT u FROM Usuarios u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "Usuarios.findByUsuario", query = "SELECT u FROM Usuarios u WHERE u.usuario = :usuario")
    , @NamedQuery(name = "Usuarios.findByEstado", query = "SELECT u FROM Usuarios u WHERE u.estado = :estado")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 255)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 255)
    @Column(name = "contrasenha")
    private String contrasenha;
    @Size(max = 255)
    @Column(name = "mail")
    private String mail;
    @Size(max = 255)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(mappedBy = "idUserCreador")
    private Collection<UsersHistories> usersHistoriesCollection;
    @OneToMany(mappedBy = "idUserEditor")
    private Collection<UsersHistories> usersHistoriesCollection1;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Sprints> sprintsCollection;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<UsersHistories> getUsersHistoriesCollection() {
        return usersHistoriesCollection;
    }

    public void setUsersHistoriesCollection(Collection<UsersHistories> usersHistoriesCollection) {
        this.usersHistoriesCollection = usersHistoriesCollection;
    }

    @XmlTransient
    public Collection<UsersHistories> getUsersHistoriesCollection1() {
        return usersHistoriesCollection1;
    }

    public void setUsersHistoriesCollection1(Collection<UsersHistories> usersHistoriesCollection1) {
        this.usersHistoriesCollection1 = usersHistoriesCollection1;
    }

    @XmlTransient
    public Collection<Sprints> getSprintsCollection() {
        return sprintsCollection;
    }

    public void setSprintsCollection(Collection<Sprints> sprintsCollection) {
        this.sprintsCollection = sprintsCollection;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumRestfinal.entities.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
