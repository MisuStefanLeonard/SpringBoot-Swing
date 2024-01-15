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
    public List<Retete> findAll();
    public Retete save(Retete retete);
    public Retete delete(Retete retete);
    public Retete update(Retete retete, Integer retetaID ,Integer CUI , String CNP);
    public Retete findById(Integer ID);
    public List<Retete> findByPacient(Pacienti pacient);
}
