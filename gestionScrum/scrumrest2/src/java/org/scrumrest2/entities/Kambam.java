/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrumrest2.entities;

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
 * @author pauli
 */
@Entity
@Table(name = "kambam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kambam.findAll", query = "SELECT k FROM Kambam k")
    , @NamedQuery(name = "Kambam.findByIdKambam", query = "SELECT k FROM Kambam k WHERE k.idKambam = :idKambam")
    , @NamedQuery(name = "Kambam.findByNombreKambam", query = "SELECT k FROM Kambam k WHERE k.nombreKambam = :nombreKambam")})
public class Kambam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_kambam")
    private Integer idKambam;
    @Basic(optional = false)
    @Column(name = "nombre_kambam")
    private String nombreKambam;
    @JoinColumn(name = "id_backlog", referencedColumnName = "id_backlog")
    @ManyToOne(optional = false)
    private Baklogs idBacklog;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estados idEstado;

    public Kambam() {
    }

    public Kambam(Integer idKambam) {
        this.idKambam = idKambam;
    }

    public Kambam(Integer idKambam, String nombreKambam) {
        this.idKambam = idKambam;
        this.nombreKambam = nombreKambam;
    }

    public Integer getIdKambam() {
        return idKambam;
    }

    public void setIdKambam(Integer idKambam) {
        this.idKambam = idKambam;
    }

    public String getNombreKambam() {
        return nombreKambam;
    }

    public void setNombreKambam(String nombreKambam) {
        this.nombreKambam = nombreKambam;
    }

    public Baklogs getIdBacklog() {
        return idBacklog;
    }

    public void setIdBacklog(Baklogs idBacklog) {
        this.idBacklog = idBacklog;
    }

    public Estados getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKambam != null ? idKambam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kambam)) {
            return false;
        }
        Kambam other = (Kambam) object;
        if ((this.idKambam == null && other.idKambam != null) || (this.idKambam != null && !this.idKambam.equals(other.idKambam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumrest2.entities.Kambam[ idKambam=" + idKambam + " ]";
    }
    
}
