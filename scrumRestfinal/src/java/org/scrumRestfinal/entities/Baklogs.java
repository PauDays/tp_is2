/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumRestfinal.entities;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Pauli
 */
@Entity
@Table(name = "baklogs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baklogs.findAll", query = "SELECT b FROM Baklogs b")
    , @NamedQuery(name = "Baklogs.findByIdBacklog", query = "SELECT b FROM Baklogs b WHERE b.idBacklog = :idBacklog")
    , @NamedQuery(name = "Baklogs.findByNombreBacklog", query = "SELECT b FROM Baklogs b WHERE b.nombreBacklog = :nombreBacklog")})
public class Baklogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_backlog")
    private Integer idBacklog;
    @Basic(optional = false)
    @Column(name = "nombre_backlog")
    private String nombreBacklog;
    @JoinColumn(name = "id_us", referencedColumnName = "id_us")
    @ManyToOne(optional = false)
    private UsersHistories idUs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBacklog")
    private Collection<Kambam> kambamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBacklog")
    private Collection<Proyectos> proyectosCollection;

    public Baklogs() {
    }

    public Baklogs(Integer idBacklog) {
        this.idBacklog = idBacklog;
    }

    public Baklogs(Integer idBacklog, String nombreBacklog) {
        this.idBacklog = idBacklog;
        this.nombreBacklog = nombreBacklog;
    }

    public Integer getIdBacklog() {
        return idBacklog;
    }

    public void setIdBacklog(Integer idBacklog) {
        this.idBacklog = idBacklog;
    }

    public String getNombreBacklog() {
        return nombreBacklog;
    }

    public void setNombreBacklog(String nombreBacklog) {
        this.nombreBacklog = nombreBacklog;
    }

    public UsersHistories getIdUs() {
        return idUs;
    }

    public void setIdUs(UsersHistories idUs) {
        this.idUs = idUs;
    }

    @XmlTransient
    public Collection<Kambam> getKambamCollection() {
        return kambamCollection;
    }

    public void setKambamCollection(Collection<Kambam> kambamCollection) {
        this.kambamCollection = kambamCollection;
    }

    @XmlTransient
    public Collection<Proyectos> getProyectosCollection() {
        return proyectosCollection;
    }

    public void setProyectosCollection(Collection<Proyectos> proyectosCollection) {
        this.proyectosCollection = proyectosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBacklog != null ? idBacklog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baklogs)) {
            return false;
        }
        Baklogs other = (Baklogs) object;
        if ((this.idBacklog == null && other.idBacklog != null) || (this.idBacklog != null && !this.idBacklog.equals(other.idBacklog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumRestfinal.entities.Baklogs[ idBacklog=" + idBacklog + " ]";
    }
    
}
