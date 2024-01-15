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
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author misustefan
 */
@Entity
@Table(name = "doctori")
@Getter
@Setter
@NamedQuery(
    name = "Doctori.getEveryDoctorsReteteLessThanACount",
    query = "SELECT NEW com.dbapplication.DTOmodels.DoctoriDTO(COUNT(r.retetaId), r.doctorRetete.doctorCUI) " +
            "FROM Retete r " +
            "GROUP BY r.doctorRetete.doctorCUI " +
            "HAVING COUNT(r.retetaId) > :reteteCount"
)
public class Doctori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "doctor_CUI")
    private Integer doctorCUI;
    @Transient
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "doctori" , cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private List<CabineteDoctori> cabineteDoctoriList;
    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "doctorRetete", cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private List<Retete> ReteteDoctoriList;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tip_doctor_id" , referencedColumnName = "tip_doctor_id")
    private TipDoctori tipDoctor;
    @Basic(optional = false)
    @Column(name = "doctor_nume")
    private String doctorNume;
    @Basic(optional = false)
    @Column(name = "doctor_prenume")
    private String doctorPrenume;
    @Column(name = "doctor_data_nastere")
    private Date doctorDataNastere;
    
    public Doctori() {}
    
    public Doctori(Doctori other) {
    this.doctorCUI = other.doctorCUI;
    this.cabineteDoctoriList = new ArrayList<>();
    for(CabineteDoctori cabinete: other.cabineteDoctoriList){
        CabineteDoctori cabinetClone = new CabineteDoctori(cabinete);
        this.cabineteDoctoriList.add(cabinetClone);
    }
    this.ReteteDoctoriList = new ArrayList<>();
    for(Retete reteteIT : other.ReteteDoctoriList){
        Retete retetaClone = new Retete(reteteIT);
        this.ReteteDoctoriList.add(retetaClone);
    }
    this.doctorNume = other.doctorNume;
    this.doctorPrenume = other.doctorPrenume;
    this.doctorDataNastere = other.doctorDataNastere;
}


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (doctorCUI != null ? doctorCUI.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctori)) {
            return false;
        }
        Doctori other = (Doctori) object;
        if ((this.doctorCUI == null && other.doctorCUI != null) || (this.doctorCUI != null && !this.doctorCUI.equals(other.doctorCUI))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.Doctori[ doctorCUI=" + doctorCUI + " ]";
    }
    
}
