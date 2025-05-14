/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Doctori;
import com.dbapplication.models.Pacienti;
import com.dbapplication.models.Retete;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface ReteteService {
     List<Retete> findAll();
     Retete save(Retete retete);
     Retete delete(Retete retete);
     Retete update(Retete retete, Integer retetaID ,Integer CUI , String CNP);
     Retete findById(Integer ID);
     List<Retete> findByPacient(Pacienti pacient);
}
