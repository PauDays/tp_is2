/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumis2.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sara Chamorro
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login_1.findAll", query = "SELECT l FROM Login_1 l")
    , @NamedQuery(name = "Login_1.findByIdLogin", query = "SELECT l FROM Login_1 l WHERE l.idLogin = :idLogin")
    , @NamedQuery(name = "Login_1.findByNombre", query = "SELECT l FROM Login_1 l WHERE l.nombre = :nombre")
    , @NamedQuery(name = "Login_1.findByContrasenha", query = "SELECT l FROM Login_1 l WHERE l.contrasenha = :contrasenha")})
public class Login_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_login")
    private Integer idLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "contrasenha")
    private String contrasenha;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios_1 idUsuario;

    public Login_1() {
    }

    public Login_1(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Login_1(Integer idLogin, String nombre, String contrasenha) {
        this.idLogin = idLogin;
        this.nombre = nombre;
        this.contrasenha = contrasenha;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public Usuarios_1 getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios_1 idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLogin != null ? idLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login_1)) {
            return false;
        }
        Login_1 other = (Login_1) object;
        if ((this.idLogin == null && other.idLogin != null) || (this.idLogin != null && !this.idLogin.equals(other.idLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Login_1[ idLogin=" + idLogin + " ]";
    }
    
}
