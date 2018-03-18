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
@Table(name = "estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estados_1.findAll", query = "SELECT e FROM Estados_1 e")
    , @NamedQuery(name = "Estados_1.findByIdEstado", query = "SELECT e FROM Estados_1 e WHERE e.idEstado = :idEstado")
    , @NamedQuery(name = "Estados_1.findByNombreEstado", query = "SELECT e FROM Estados_1 e WHERE e.nombreEstado = :nombreEstado")})
public class Estados_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_estado")
    private String nombreEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private Collection<Kambam_1> kambamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado")
    private Collection<Proyectos_1> proyectosCollection;

    public Estados_1() {
    }

    public Estados_1(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Estados_1(Integer idEstado, String nombreEstado) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    @XmlTransient
    public Collection<Kambam_1> getKambamCollection() {
        return kambamCollection;
    }

    public void setKambamCollection(Collection<Kambam_1> kambamCollection) {
        this.kambamCollection = kambamCollection;
    }

    @XmlTransient
    public Collection<Proyectos_1> getProyectosCollection() {
        return proyectosCollection;
    }

    public void setProyectosCollection(Collection<Proyectos_1> proyectosCollection) {
        this.proyectosCollection = proyectosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados_1)) {
            return false;
        }
        Estados_1 other = (Estados_1) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Estados_1[ idEstado=" + idEstado + " ]";
    }
    
}
