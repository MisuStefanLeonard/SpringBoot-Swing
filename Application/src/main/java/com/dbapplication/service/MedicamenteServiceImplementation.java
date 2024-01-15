/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Medicamente;
import com.dbapplication.models.ReteteCuMedicamentele;
import com.dbapplication.repository.MedicamenteRepository;
import com.dbapplication.repository.ReteteCuMedicamenteleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author misustefan
 */
@Service("MedicamenteService")
@Transactional
public class MedicamenteServiceImplementation implements MedicamenteService{
    private final MedicamenteRepository medicamenteRepository;
    private final ReteteCuMedicamenteleRepository ReteteCuMedicamenteleRepository;
    
    @Autowired
    public MedicamenteServiceImplementation(MedicamenteRepository medicamenteRepository, ReteteCuMedicamenteleRepository ReteteCuMedicamenteleRepository) {
        this.medicamenteRepository = medicamenteRepository;
        this.ReteteCuMedicamenteleRepository = ReteteCuMedicamenteleRepository;
    }
    
    @Override
    public List<Medicamente> findAll() {
        return medicamenteRepository.findAll();
    }

    @Override
    public Medicamente save(Medicamente medicament) {
        medicamenteRepository.save(medicament);
        return medicament;
    }

    @Override
    public Medicamente delete(Medicamente medicament) {
        medicamenteRepository.delete(medicament);
        return medicament;
    }
    
    @Override
    public Medicamente update(Medicamente medicament , Integer ID){
        Medicamente medicamentToBeUpdated = medicamenteRepository.findById(ID).orElse(null);
        if(medicamentToBeUpdated != null){
            medicamentToBeUpdated.setMedicamentNume(medicament.getMedicamentNume());
            medicamentToBeUpdated.setMedicamentGramajMg(medicament.getMedicamentGramajMg());
            medicamentToBeUpdated.setTipMedicament(medicament.getTipMedicament());
            
            medicamenteRepository.save(medicamentToBeUpdated);
        }else{
            throw new Error("Medicament not found.Cannot update!");
        }
        return medicamentToBeUpdated;
    }

    @Override
    public Medicamente findById(Integer ID) {
        return medicamenteRepository.findById(ID).orElse(null);
    }

    @Override
    public Medicamente findByNumeMedicament(String nume) {
        return medicamenteRepository.findBymedicamentNume(nume);
    }
    
}
