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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
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
@Table(name = "retete_cu_medicamentele")
@Getter
@Setter
public class ReteteCuMedicamentele implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reteta_cu_medicamentele_id")
    private Integer retetaCuMedicamenteleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reteta_id" , referencedColumnName = "reteta_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Retete reteta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id" , referencedColumnName = "medicament_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Medicamente medicament;
    @Column(name = "cantitate")
    private Integer cantitate;
    
    public ReteteCuMedicamentele() {}
    
    public ReteteCuMedicamentele(ReteteCuMedicamentele other){
        this.retetaCuMedicamenteleId = other.retetaCuMedicamenteleId;
        this.cantitate = other.cantitate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retetaCuMedicamenteleId != null ? retetaCuMedicamenteleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReteteCuMedicamentele)) {
            return false;
        }
        ReteteCuMedicamentele other = (ReteteCuMedicamentele) object;
        if ((this.retetaCuMedicamenteleId == null && other.retetaCuMedicamenteleId != null) || (this.retetaCuMedicamenteleId != null && !this.retetaCuMedicamenteleId.equals(other.retetaCuMedicamenteleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.ReteteCuMedicamentele[ retetaCuMedicamenteleId=" + retetaCuMedicamenteleId + " ]";
    }
    
}
