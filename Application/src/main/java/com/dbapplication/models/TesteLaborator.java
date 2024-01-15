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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author misustefan
 */
@Entity
@Table(name = "teste_laborator")
@Getter
@Setter
public class TesteLaborator implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_test")
    private Integer idTest;
    @Basic(optional = false)
    @Column(name = "nume_test")
    private String numeTest;
    @Column(name = "valoare_minima")
    private Integer valoareMinima;
    @Column(name = "valoare_maxima")
    private Integer valoareMaxima;
    @Transient
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "testeLaborator" , cascade = {CascadeType.MERGE , CascadeType.REMOVE})
    private List<TestePacienti> testePacientiLaborator;
    @Basic(optional = false)
    @Column(name = "acuaratete")
    private BigDecimal acuaratete;
 
    public TesteLaborator() {}
    
    public TesteLaborator(TesteLaborator other){
        this.idTest = other.idTest;
        this.numeTest = other.numeTest;
        this.valoareMinima = other.valoareMinima;
        this.valoareMaxima = other.valoareMaxima;
        this.testePacientiLaborator = new ArrayList<>();
        for(TestePacienti testeIT : other.testePacientiLaborator){
            this.testePacientiLaborator.add(new TestePacienti(testeIT));
        }
        this.acuaratete = other.acuaratete;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTest != null ? idTest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TesteLaborator)) {
            return false;
        }
        TesteLaborator other = (TesteLaborator) object;
        if ((this.idTest == null && other.idTest != null) || (this.idTest != null && !this.idTest.equals(other.idTest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.TesteLaborator[ idTest=" + idTest + " ]";
    }
    
}
