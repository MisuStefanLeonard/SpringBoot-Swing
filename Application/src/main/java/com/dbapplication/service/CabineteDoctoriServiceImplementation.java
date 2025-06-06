/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.CabineteDoctori;
import com.dbapplication.repository.CabineteDoctoriRepository;
import com.dbapplication.repository.DoctoriRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
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
@Service("CabineteDoctoriService")
@Transactional
public class CabineteDoctoriServiceImplementation implements CabineteDoctoriService{
    
    private final CabineteDoctoriRepository cabineteDoctoriRepository;
    private final String TableName = "cabinete_doctori";
    @Autowired
    public CabineteDoctoriServiceImplementation(CabineteDoctoriRepository cabineteDoctoriRepository) {
        this.cabineteDoctoriRepository = cabineteDoctoriRepository;
        
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
    public List<CabineteDoctori> findAll(){
        return cabineteDoctoriRepository.findAll();
    }

    @Override
    public CabineteDoctori save(CabineteDoctori cabinetDoctori) {
       cabineteDoctoriRepository.save(cabinetDoctori);
       String ts = LocalDateTime.now().toString();
       CsvService.writeNext(new String[]{TableName , "INSERT",ts});
       return cabinetDoctori;
    }

    @Override
    public CabineteDoctori delete(CabineteDoctori cabinetDoctori) {
       cabineteDoctoriRepository.delete(cabinetDoctori);
       String ts = LocalDateTime.now().toString();
       CsvService.writeNext(new String[]{TableName , "DELETE",ts});
       return cabinetDoctori;
    }
    
    @Override
    public CabineteDoctori update(CabineteDoctori cabinetDoctori , Integer cabinetID){
        CabineteDoctori cabinetToBeUpdated = cabineteDoctoriRepository.findById(cabinetID).orElse(null);
            if(cabinetToBeUpdated != null){
                cabinetToBeUpdated.setCabinetCodPostal(cabinetDoctori.getCabinetCodPostal());
                cabinetToBeUpdated.setCabinetNrTelefon(cabinetDoctori.getCabinetNrTelefon());
                cabinetToBeUpdated.setCabinetNumarStrada(cabinetDoctori.getCabinetNumarStrada());
                cabinetToBeUpdated.setCabinetNume(cabinetDoctori.getCabinetNume());
                cabinetToBeUpdated.setCabinetOras(cabinetDoctori.getCabinetOras());
                cabinetToBeUpdated.setCabinetStrada(cabinetDoctori.getCabinetStrada());
                cabinetToBeUpdated.setDoctori(cabinetDoctori.getDoctori());
                String ts = LocalDateTime.now().toString();
                CsvService.writeNext(new String[]{TableName , "UPDATE",ts});
                cabineteDoctoriRepository.save(cabinetToBeUpdated);
            }else{
                throw new Error("CABINET NOT FOUND!");
            }
        return cabinetToBeUpdated;
    }

    @Override
    public CabineteDoctori findById(Integer ID ) {
        return cabineteDoctoriRepository.findById(ID).orElse(null);
    }

    @Override
    public CabineteDoctori findByPhoneNumber(String phoneNumber) {
        return cabineteDoctoriRepository.findBycabinetNrTelefon(phoneNumber);
    }
}
