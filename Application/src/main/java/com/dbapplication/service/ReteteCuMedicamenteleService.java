/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Medicamente;
import com.dbapplication.models.Retete;
import com.dbapplication.models.ReteteCuMedicamentele;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface ReteteCuMedicamenteleService {
    public List<ReteteCuMedicamentele> findAll();
    public ReteteCuMedicamentele save(ReteteCuMedicamentele reteta);
    public ReteteCuMedicamentele delete(ReteteCuMedicamentele reteta);
    public ReteteCuMedicamentele update(ReteteCuMedicamentele reteta ,Integer IDRetetaToBeUpdated ,String numeMedicament , Integer retetaID);
    public ReteteCuMedicamentele findById(Integer ID);
}
