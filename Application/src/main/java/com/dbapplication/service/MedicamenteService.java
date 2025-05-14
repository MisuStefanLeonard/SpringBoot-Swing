/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Medicamente;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface MedicamenteService {
     List<Medicamente> findAll();
     Medicamente save(Medicamente medicament);
     Medicamente delete(Medicamente medicament);
     Medicamente update(Medicamente medicament , Integer ID);
     Medicamente findById(Integer ID);
     Medicamente findByNumeMedicament(String nume);
}
