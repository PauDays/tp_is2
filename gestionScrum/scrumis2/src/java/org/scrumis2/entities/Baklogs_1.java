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
@Table(name = "baklogs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Baklogs_1.findAll", query = "SELECT b FROM Baklogs_1 b")
    , @NamedQuery(name = "Baklogs_1.findByIdBacklog", query = "SELECT b FROM Baklogs_1 b WHERE b.idBacklog = :idBacklog")
    , @NamedQuery(name = "Baklogs_1.findByNombreBacklog", query = "SELECT b FROM Baklogs_1 b WHERE b.nombreBacklog = :nombreBacklog")})
public class Baklogs_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_backlog")
    private Integer idBacklog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_backlog")
    private String nombreBacklog;
    @JoinColumn(name = "id_us", referencedColumnName = "id_us")
    @ManyToOne(optional = false)
    private UsersHistories_1 idUs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBacklog")
    private Collection<Kambam_1> kambamCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBacklog")
    private Collection<Proyectos_1> proyectosCollection;

    public Baklogs_1() {
    }

    public Baklogs_1(Integer idBacklog) {
        this.idBacklog = idBacklog;
    }

    public Baklogs_1(Integer idBacklog, String nombreBacklog) {
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

    public UsersHistories_1 getIdUs() {
        return idUs;
    }

    public void setIdUs(UsersHistories_1 idUs) {
        this.idUs = idUs;
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
        hash += (idBacklog != null ? idBacklog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baklogs_1)) {
            return false;
        }
        Baklogs_1 other = (Baklogs_1) object;
        if ((this.idBacklog == null && other.idBacklog != null) || (this.idBacklog != null && !this.idBacklog.equals(other.idBacklog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Baklogs_1[ idBacklog=" + idBacklog + " ]";
    }
    
}
