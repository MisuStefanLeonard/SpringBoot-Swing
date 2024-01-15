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
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author misustefan
 */
@Entity
@Table(name = "pacienti")
@Getter
@Setter
@NamedQuery(
    name = "Pacienti.pacientsWhoHaveOnPrescriptionMedicamentWithNameAndWithTestName",
    query = "SELECT p FROM Pacienti p " +
            "JOIN Retete r ON p.pacientCnp = r.pacientRetete.pacientCnp " +
            "JOIN ReteteCuMedicamentele rm ON r.retetaId = rm.reteta.retetaId " +
            "JOIN TestePacienti t ON p.pacientCnp = t.pacienti.pacientCnp " +
            "WHERE rm.medicament.medicamentId = (SELECT m.medicamentId " +
                                                "FROM Medicamente m " +
                                                "WHERE m.medicamentNume = :medicament_name) " +
            "AND t.testeLaborator.numeTest = (SELECT tl.numeTest " +
                                             "FROM TesteLaborator tl " +
                                             "WHERE tl.numeTest = :test_name)"
)
public class Pacienti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "pacient_data_nastere")
    @Temporal(TemporalType.DATE)
    private Date pacientDataNastere;
    @Id
    @Basic(optional = false)
    @Column(name = "pacient_cnp")
    private String pacientCnp;
    @Transient
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "pacienti" , cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private List<TestePacienti> testePacienti;
    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pacientRetete" , cascade = {CascadeType.REMOVE,CascadeType.MERGE} )
    private List<Retete> retetePacienti;
    @Basic(optional = false)
    @Column(name = "pacient_nume")
    private String pacientNume;
    @Basic(optional = false)
    @Column(name = "pacient_prenume")
    private String pacientPrenume;
    @Basic(optional = false)
    @Column(name = "pacient_oras_nastere")
    private String pacientOrasNastere;
    @Column(name = "pacient_polita_medicala")
    private String pacientPolitaMedicala;

    public Pacienti() {
    
    }

    public Pacienti(Pacienti other){
        this.pacientCnp = other.pacientCnp;
        this.pacientNume = other.pacientNume;
        this.pacientPrenume = other.pacientPrenume;
        this.pacientOrasNastere = other.pacientOrasNastere;
        this.pacientPolitaMedicala = other.pacientPolitaMedicala;
        this.testePacienti = new ArrayList<>();
        for(TestePacienti testeIT : other.testePacienti){
            this.testePacienti.add(new TestePacienti(testeIT));
        }
        this.retetePacienti = new ArrayList<>();
        for(Retete reteteIT : other.retetePacienti){
            this.retetePacienti.add(new Retete(reteteIT));
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacientCnp != null ? pacientCnp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pacienti)) {
            return false;
        }
        Pacienti other = (Pacienti) object;
        if ((this.pacientCnp == null && other.pacientCnp != null) || (this.pacientCnp != null && !this.pacientCnp.equals(other.pacientCnp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dbapplication.Pacienti[ pacientCnp=" + pacientCnp + " ]";
    }
    
}
