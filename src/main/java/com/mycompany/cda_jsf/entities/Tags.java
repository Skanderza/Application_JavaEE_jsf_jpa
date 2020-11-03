/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cda_jsf.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author alliese
 */
@Entity
@Table(name = "tags")
@NamedQueries({
    @NamedQuery(name = "Tags.findAll", query = "SELECT t FROM Tags t"),
    @NamedQuery(name = "Tags.findByIdTag", query = "SELECT t FROM Tags t WHERE t.idTag = :idTag"),
    @NamedQuery(name = "Tags.findByLibelle", query = "SELECT t FROM Tags t WHERE t.libelle = :libelle")})
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tag")
    private Integer idTag;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "libelle")
    private String libelle;

    public Tags() {
    }

    public Tags(Integer idTag) {
        this.idTag = idTag;
    }

    public Tags(Integer idTag, String libelle) {
        this.idTag = idTag;
        this.libelle = libelle;
    }

    public Integer getIdTag() {
        return idTag;
    }

    public void setIdTag(Integer idTag) {
        this.idTag = idTag;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTag != null ? idTag.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tags)) {
            return false;
        }
        Tags other = (Tags) object;
        if ((this.idTag == null && other.idTag != null) || (this.idTag != null && !this.idTag.equals(other.idTag))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cda_jsf.entities.Tags[ idTag=" + idTag + " ]";
    }
    
}
