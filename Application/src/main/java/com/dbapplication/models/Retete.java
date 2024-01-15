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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author misustefan
 */
@Entity
@Table(name = "retete")
@Getter
@Setter
public class Retete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "reteta_id")
    private Integer retetaId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_CUI", referencedColumnName = "doctor_CUI")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Doctori doctorRetete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacient_cnp", referencedColumnName = "pacient_cnp")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pacienti pacientRetete; 
    @Transient
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "reteta" , cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<ReteteCuMedicamentele> RetetaCuMedicamentul;
    @Column(name = "reteta_data_emitere")
    @Temporal(TemporalType.DATE)
    @Basic(optional = false)
    private Date retetaDataEmitere;
    @Column(name = "reteta_data_expirare")
    @Temporal(TemporalType.DATE)
    private Date retetaDataExpirare;
    
    @Column(name = "tip_reteta")
    private String tipReteta;
    
    public Retete() {
    }
    
    public Retete(Retete other){
        this.retetaId = other.retetaId;
        this.retetaDataEmitere = other.retetaDataEmitere;
        this.retetaDataExpirare = other.retetaDataExpirare;
        this.tipReteta = other.tipReteta;
        this.RetetaCuMedicamentul = new ArrayList<>();
        for(ReteteCuMedicamentele reteteIT : other.RetetaCuMedicamentul){
            ReteteCuMedicamentele retetaClone = new ReteteCuMedicamentele(reteteIT);
            this.RetetaCuMedicamentul.add(retetaClone);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (retetaId != null ? retetaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Retete)) {
            return false;
        }
        Retete other = (Retete) object;
        if ((this.retetaId == null && other.retetaId != null) || (this.retetaId != null && !this.retetaId.equals(other.retetaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.Retete[ retetaId=" + retetaId + " ]";
    }
    
}
