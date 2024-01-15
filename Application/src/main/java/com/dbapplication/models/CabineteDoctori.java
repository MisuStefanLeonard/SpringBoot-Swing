/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author misustefan
 */
@Entity
@Table(name = "cabinete_doctori")
@Getter
@Setter
public class CabineteDoctori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cabinet_id")
    private Integer cabinetId;
    @ManyToOne
    @JoinColumn(name = "doctor_CUI")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctori doctori;
    @Basic(optional = false)
    @Column(name = "cabinet_nume")
    private String cabinetNume;
    @Basic(optional = false)
    @Column(name = "cabinet_cod_postal")
    private String cabinetCodPostal;
    @Basic(optional = false)
    @Column(name = "cabinet_oras")
    private String cabinetOras;
    @Column(name = "cabinet_strada")
    private String cabinetStrada;
    @Basic(optional = false)
    @Column(name = "cabinet_numar_strada")
    private int cabinetNumarStrada;
    @Column(name = "cabinet_nr_telefon")
    private String cabinetNrTelefon;

    public CabineteDoctori() {
    }

    public CabineteDoctori(CabineteDoctori other){
        this.cabinetId = other.cabinetId;
        this.cabinetNume = other.cabinetNume;
        this.cabinetCodPostal = other.cabinetCodPostal;
        this.cabinetOras = other.cabinetOras;
        this.cabinetStrada = other.cabinetStrada;
        this.cabinetNumarStrada = other.cabinetNumarStrada;
        this.cabinetNrTelefon = other.cabinetNrTelefon;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cabinetId != null ? cabinetId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CabineteDoctori)) {
            return false;
        }
        CabineteDoctori other = (CabineteDoctori) object;
        if ((this.cabinetId == null && other.cabinetId != null) || (this.cabinetId != null && !this.cabinetId.equals(other.cabinetId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.CabineteDoctori[ cabinetId=" + cabinetId + " ]";
    }
    
}
