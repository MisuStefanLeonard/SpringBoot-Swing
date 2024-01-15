/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.models.VIEWModels;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author misustefan
 */
@Entity
@Getter
@Setter
@Table(name = "cabinete_view")
public class CabineteDoctoriVIEW {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabinet_id")
    @Basic(optional = false)
    private Integer cabinetID;
    @Column(name = "doctor_CUI")
    @Basic(optional = false)
    private Integer doctorCUI;
    @Column(name = "cabinet_nume")
    @Basic(optional = false)
    private String cabinetNume;
    @Column(name = "cabinet_cod_postal")
    @Basic(optional = false)
    private String codPostal;
    @Column(name = "cabinet_oras")
    @Basic(optional = false)
    private String cabinetOras;
    @Column(name = "cabinet_numar_strada")
    @Basic(optional = false)
    private Integer cabinetNrStrada;
    
    public CabineteDoctoriVIEW(){};
    
    public CabineteDoctoriVIEW(CabineteDoctoriVIEW otherCabinetFromView){
        this.cabinetID = otherCabinetFromView.cabinetID;
        this.doctorCUI = otherCabinetFromView.doctorCUI;
        this.cabinetNume = otherCabinetFromView.cabinetNume;
        this.codPostal = otherCabinetFromView.codPostal;
        this.cabinetOras = otherCabinetFromView.cabinetOras;
        this.cabinetNrStrada = otherCabinetFromView.cabinetNrStrada;
    }
    
}
