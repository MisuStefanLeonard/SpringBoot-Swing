/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Pacienti;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface PacientiService {
    public List<Pacienti> findAll();
    public Pacienti save(Pacienti pacienti);
    public Pacienti delete(Pacienti pacienti);
    public Pacienti update(Pacienti pacienti , String oldCNP);
    public Pacienti findById(String pacientCnp);
    public List<Pacienti> pacientsWhoHaveOnPrescriptionMedicamentWithNameAndWithTestName(String medicamentName , String numeTest);
}
