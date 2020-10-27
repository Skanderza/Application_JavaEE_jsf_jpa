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
@Table(name = "themes")
@NamedQueries({
    @NamedQuery(name = "Themes.findAll", query = "SELECT t FROM Themes t"),
    @NamedQuery(name = "Themes.findByIdTheme", query = "SELECT t FROM Themes t WHERE t.idTheme = :idTheme"),
    @NamedQuery(name = "Themes.findByLibelle", query = "SELECT t FROM Themes t WHERE t.libelle = :libelle")})
public class Themes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_theme")
    private Integer idTheme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "libelle")
    private String libelle;

    public Themes() {
    }

    public Themes(Integer idTheme) {
        this.idTheme = idTheme;
    }

    public Themes(Integer idTheme, String libelle) {
        this.idTheme = idTheme;
        this.libelle = libelle;
    }

    public Integer getIdTheme() {
        return idTheme;
    }

    public void setIdTheme(Integer idTheme) {
        this.idTheme = idTheme;
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
        hash += (idTheme != null ? idTheme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Themes)) {
            return false;
        }
        Themes other = (Themes) object;
        if ((this.idTheme == null && other.idTheme != null) || (this.idTheme != null && !this.idTheme.equals(other.idTheme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cda_jsf.entities.Themes[ idTheme=" + idTheme + " ]";
    }
    
}
