/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Medicamente;
import com.dbapplication.models.ReteteCuMedicamentele;
import com.dbapplication.repository.MedicamenteRepository;
import com.dbapplication.repository.ReteteCuMedicamenteleRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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
    private final String TableName = "medicamente";
    @Autowired
    public MedicamenteServiceImplementation(MedicamenteRepository medicamenteRepository) {
        this.medicamenteRepository = medicamenteRepository;
    }

    @PostConstruct
    public void initCsv() {
        try {
            CsvService.init("audit.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void closeCsv() {
        try {
            CsvService.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Medicamente> findAll() {
        return medicamenteRepository.findAll();
    }

    @Override
    public Medicamente save(Medicamente medicament) {
        medicamenteRepository.save(medicament);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "INSERT",ts});
        return medicament;
    }

    @Override
    public Medicamente delete(Medicamente medicament) {
        medicamenteRepository.delete(medicament);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "DELETE",ts});
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
            String ts = LocalDateTime.now().toString();
            CsvService.writeNext(new String[]{TableName , "UPDATE",ts});
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
