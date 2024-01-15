/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.models;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author misustefan
 */
@Entity
@Table(name = "tip_doctori")
@Getter
@Setter
public class TipDoctori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tip_doctor_id")
    private Integer tipDoctorId;
    @Basic(optional = false)
    @Column(name = "nume_specializare")
    private String numeSpecializare;
    @Transient
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "tipDoctor" , cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private List<Doctori> doctori;
    
    public TipDoctori() {
    
    }
    
    public TipDoctori(TipDoctori other){
        this.tipDoctorId = other.tipDoctorId;
        this.numeSpecializare = other.numeSpecializare;
        this.doctori = new ArrayList<>();
        for(Doctori doctor : other.doctori){
            this.doctori.add(new Doctori(doctor));
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipDoctorId != null ? tipDoctorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipDoctori)) {
            return false;
        }
        TipDoctori other = (TipDoctori) object;
        if ((this.tipDoctorId == null && other.tipDoctorId != null) || (this.tipDoctorId != null && !this.tipDoctorId.equals(other.tipDoctorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.TipDoctori[ tipDoctorId=" + tipDoctorId + " ]";
    }
    
}
