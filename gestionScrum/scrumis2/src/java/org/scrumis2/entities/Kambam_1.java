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
@Table(name = "kambam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kambam_1.findAll", query = "SELECT k FROM Kambam_1 k")
    , @NamedQuery(name = "Kambam_1.findByIdKambam", query = "SELECT k FROM Kambam_1 k WHERE k.idKambam = :idKambam")
    , @NamedQuery(name = "Kambam_1.findByNombreKambam", query = "SELECT k FROM Kambam_1 k WHERE k.nombreKambam = :nombreKambam")})
public class Kambam_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_kambam")
    private Integer idKambam;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre_kambam")
    private String nombreKambam;
    @JoinColumn(name = "id_backlog", referencedColumnName = "id_backlog")
    @ManyToOne(optional = false)
    private Baklogs_1 idBacklog;
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private Estados_1 idEstado;

    public Kambam_1() {
    }

    public Kambam_1(Integer idKambam) {
        this.idKambam = idKambam;
    }

    public Kambam_1(Integer idKambam, String nombreKambam) {
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

    public Baklogs_1 getIdBacklog() {
        return idBacklog;
    }

    public void setIdBacklog(Baklogs_1 idBacklog) {
        this.idBacklog = idBacklog;
    }

    public Estados_1 getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estados_1 idEstado) {
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
        if (!(object instanceof Kambam_1)) {
            return false;
        }
        Kambam_1 other = (Kambam_1) object;
        if ((this.idKambam == null && other.idKambam != null) || (this.idKambam != null && !this.idKambam.equals(other.idKambam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.scrumis2.entities.Kambam_1[ idKambam=" + idKambam + " ]";
    }
    
}
