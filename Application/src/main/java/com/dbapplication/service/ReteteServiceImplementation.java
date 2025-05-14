/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Doctori;
import com.dbapplication.models.Pacienti;
import com.dbapplication.models.Retete;
import com.dbapplication.models.ReteteCuMedicamentele;
import com.dbapplication.repository.DoctoriRepository;
import com.dbapplication.repository.PacientiRepository;
import com.dbapplication.repository.ReteteCuMedicamenteleRepository;
import com.dbapplication.repository.ReteteRepository;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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
@Service("ReteteService")
@Transactional
public class ReteteServiceImplementation implements ReteteService{
    
    private final ReteteRepository reteteRepository;
    private final DoctoriRepository doctoriRepository;
    private final PacientiRepository pacientiRepository;
    private final ReteteCuMedicamenteleRepository reteteCuMedicamenteleRepository;
    private final String TableName = "retete";


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
    @Autowired
    public ReteteServiceImplementation(ReteteRepository reteteRepository, DoctoriRepository doctoriRepository, PacientiRepository pacientiRepository, ReteteCuMedicamenteleRepository reteteCuMedicamenteleRepository) {
        this.reteteRepository = reteteRepository;
        this.doctoriRepository = doctoriRepository;
        this.pacientiRepository = pacientiRepository;
        this.reteteCuMedicamenteleRepository = reteteCuMedicamenteleRepository;
    }
    
    
    
    @Override
    public List<Retete> findAll() {
        return reteteRepository.findAll();
    }
    
    @Override
    public Retete save(Retete retete) {
        reteteRepository.save(retete);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "INSERT",ts});
        return retete;
    }

    @Override
    public Retete delete(Retete retete) {
        reteteRepository.delete(retete);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "DELETE",ts});
        return retete;
    }

    @Override
    public Retete update(Retete retete , Integer retetaID , Integer CUI , String CNP) {
        Retete toBeUpdatedReteta = reteteRepository.findById(retetaID).orElse(null);
        Doctori correspondingDoctor = doctoriRepository.findById(CUI).orElse(null);
        Pacienti correspondingPacient = pacientiRepository.findById(CNP).orElse(null);
        List<ReteteCuMedicamentele> ReteteCuMedicamenteleForReteta = reteteCuMedicamenteleRepository.findByReteta(toBeUpdatedReteta);
        toBeUpdatedReteta.setRetetaCuMedicamentul(ReteteCuMedicamenteleForReteta);
        if(toBeUpdatedReteta != null){
            Retete newReteta = new Retete(toBeUpdatedReteta);
            newReteta.setRetetaDataEmitere(retete.getRetetaDataEmitere());
            newReteta.setRetetaDataExpirare(retete.getRetetaDataExpirare());
            newReteta.setDoctorRetete(correspondingDoctor);
            newReteta.setPacientRetete(correspondingPacient);
            newReteta.setTipReteta(retete.getTipReteta());
            reteteRepository.save(newReteta);
        }
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "UPDATE",ts});
        return toBeUpdatedReteta;
    }

    @Override
    public Retete findById(Integer ID) {
        return reteteRepository.findById(ID).orElse(null);
    }

    @Override
    public List<Retete> findByPacient(Pacienti pacient) {
        return reteteRepository.findBypacientRetete(pacient);
    }
    
}
