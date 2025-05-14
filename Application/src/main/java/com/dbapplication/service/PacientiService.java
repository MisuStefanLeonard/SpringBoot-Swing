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
     List<Pacienti> findAll();
     Pacienti save(Pacienti pacienti);
     Pacienti delete(Pacienti pacienti);
     Pacienti update(Pacienti pacienti , String oldCNP);
     Pacienti findById(String pacientCnp);
     List<Pacienti> pacientsWhoHaveOnPrescriptionMedicamentWithNameAndWithTestName(String medicamentName , String numeTest);
}
