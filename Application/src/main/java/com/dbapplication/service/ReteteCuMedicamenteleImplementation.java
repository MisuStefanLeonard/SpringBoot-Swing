/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Medicamente;
import com.dbapplication.models.Retete;
import com.dbapplication.models.ReteteCuMedicamentele;
import com.dbapplication.repository.MedicamenteRepository;
import com.dbapplication.repository.ReteteCuMedicamenteleRepository;
import com.dbapplication.repository.ReteteRepository;

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
@Service
@Transactional
public class ReteteCuMedicamenteleImplementation implements ReteteCuMedicamenteleService{
    
    private final ReteteCuMedicamenteleRepository reteteCuMedicamenteRepository;
    private final MedicamenteRepository medicamenteRepository;
    private final ReteteRepository reteteRepository;
    private final String TableName = "retete_cu_medicamente";

    @Autowired
    public ReteteCuMedicamenteleImplementation(ReteteCuMedicamenteleRepository reteteCuMedicamenteRepository, MedicamenteRepository medicamenteRepository, ReteteRepository reteteRepository) {
        this.reteteCuMedicamenteRepository = reteteCuMedicamenteRepository;
        this.medicamenteRepository = medicamenteRepository;
        this.reteteRepository = reteteRepository;
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
    public List<ReteteCuMedicamentele> findAll() {
        return reteteCuMedicamenteRepository.findAll();
    }

    @Override
    public ReteteCuMedicamentele save(ReteteCuMedicamentele reteta) {
        reteteCuMedicamenteRepository.save(reteta);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "INSERT",ts});
        return reteta;
    }

    @Override
    public ReteteCuMedicamentele delete(ReteteCuMedicamentele reteta) {
        reteteCuMedicamenteRepository.delete(reteta);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "DELETE",ts});
        return reteta;
    }

    @Override
    public ReteteCuMedicamentele update(ReteteCuMedicamentele reteta,Integer IDRetetaToBeUpdated , String nume_medicament , Integer retetaID) {
        ReteteCuMedicamentele toBeUpdatedReteteCuMedicamentul = reteteCuMedicamenteRepository.findById(IDRetetaToBeUpdated).orElse(null);
        Medicamente correspondingMedicament = medicamenteRepository.findBymedicamentNume(nume_medicament);
        Retete correspondingReteta = reteteRepository.findById(retetaID).orElse(null);
        if(toBeUpdatedReteteCuMedicamentul != null){
            ReteteCuMedicamentele newUpdatedRetetaCuMedicamentul = new ReteteCuMedicamentele(toBeUpdatedReteteCuMedicamentul);
            
            newUpdatedRetetaCuMedicamentul.setCantitate(reteta.getCantitate());
            newUpdatedRetetaCuMedicamentul.setMedicament(correspondingMedicament);
            newUpdatedRetetaCuMedicamentul.setReteta(correspondingReteta);
            
            reteteCuMedicamenteRepository.save(newUpdatedRetetaCuMedicamentul);
            String ts = LocalDateTime.now().toString();
            CsvService.writeNext(new String[]{TableName , "UPDATE",ts});
        }
        return reteta;
    }

    @Override
    public ReteteCuMedicamentele findById(Integer ID) {
        return reteteCuMedicamenteRepository.findById(ID).orElse(null);
    }
    
}
