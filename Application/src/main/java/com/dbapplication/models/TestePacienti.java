/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author misustefan
 */
@Entity
@Table(name = "teste_pacienti")
@Getter
@Setter

public class TestePacienti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pacienti_test_id")
    private Integer pacientiTestId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacient_cnp")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pacienti pacienti;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nume_test" , referencedColumnName = "nume_test")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TesteLaborator testeLaborator;
    @Basic(optional = false)
    @Column(name = "rezultate")
    private String rezultate;
    @Column(name = "valoare_test")
    private BigDecimal valoareTest;
    @Column(name = "data_emitere")
    @Temporal(TemporalType.DATE)
    private Date dataEmitere;

    public TestePacienti() {}
    
    public TestePacienti(TestePacienti other){
        this.pacientiTestId = other.pacientiTestId;
        this.rezultate = other.rezultate;
        this.valoareTest = other.valoareTest;
        this.dataEmitere = other.dataEmitere;
        
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacientiTestId != null ? pacientiTestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestePacienti)) {
            return false;
        }
        TestePacienti other = (TestePacienti) object;
        if ((this.pacientiTestId == null && other.pacientiTestId != null) || (this.pacientiTestId != null && !this.pacientiTestId.equals(other.pacientiTestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.TestePacienti[ pacientiTestId=" + pacientiTestId + " ]";
    }
    
}
