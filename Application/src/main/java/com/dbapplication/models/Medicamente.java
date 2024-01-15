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
@Table(name = "medicamente")
@Getter
@Setter
public class Medicamente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "medicament_id")
    private Integer medicamentId;
    @Basic(optional = false)
    @Column(name = "medicament_nume")
    private String medicamentNume;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "medicament" , cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    @Transient
    private List<ReteteCuMedicamentele> MedicamentePeRetete;
    @Column(name = "medicament_gramaj")
    private Integer medicamentGramajMg;
    @Basic(optional = false)
    @Column(name = "tip_medicament")
    private String tipMedicament;

    public Medicamente() {
    
    }
    
    public Medicamente(Medicamente other){
        this.medicamentId = other.medicamentId;
        this.medicamentNume = other.medicamentNume;
        this.medicamentGramajMg = other.medicamentGramajMg;
        this.tipMedicament = other.tipMedicament;
        this.MedicamentePeRetete = new ArrayList<>();
        for(ReteteCuMedicamentele reteteIT : other.MedicamentePeRetete){
            this.MedicamentePeRetete.add(new ReteteCuMedicamentele(reteteIT));
        }
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicamentId != null ? medicamentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamente)) {
            return false;
        }
        Medicamente other = (Medicamente) object;
        if ((this.medicamentId == null && other.medicamentId != null) || (this.medicamentId != null && !this.medicamentId.equals(other.medicamentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.Medicamente[ medicamentId=" + medicamentId + " ]";
    }
    
}
