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
    public List<Medicamente> findAll();
    public Medicamente save(Medicamente medicament);
    public Medicamente delete(Medicamente medicament);
    public Medicamente update(Medicamente medicament , Integer ID);
    public Medicamente findById(Integer ID);
    public Medicamente findByNumeMedicament(String nume);
}
