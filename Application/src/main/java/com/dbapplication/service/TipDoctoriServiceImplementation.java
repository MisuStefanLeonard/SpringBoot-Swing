/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.CabineteDoctori;
import com.dbapplication.models.Doctori;
import com.dbapplication.models.Retete;
import com.dbapplication.models.ReteteCuMedicamentele;
import com.dbapplication.models.TipDoctori;
import com.dbapplication.repository.CabineteDoctoriRepository;
import com.dbapplication.repository.DoctoriRepository;
import com.dbapplication.repository.ReteteCuMedicamenteleRepository;
import com.dbapplication.repository.ReteteRepository;
import com.dbapplication.repository.TipDoctoriRepository;

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
@Service("TipDoctoriService")
@Transactional
public class TipDoctoriServiceImplementation implements TipDoctoriService{
    
    private final TipDoctoriRepository tipDoctoriRepository;
    private final DoctoriRepository doctoriRepository;
    private final ReteteRepository retetaRepository;
    private final CabineteDoctoriRepository cabineteRepository;
    private final ReteteCuMedicamenteleRepository reteteCuMedicamenteleRepository;
    private final String TableName = "tip_doctori";

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
    public TipDoctoriServiceImplementation(TipDoctoriRepository tipDoctoriRepository, DoctoriRepository doctoriRepository, 
                                            ReteteRepository retetaRepository, CabineteDoctoriRepository cabineteRepository, 
                                            ReteteCuMedicamenteleRepository reteteCuMedicamenteleRepository) {
        this.tipDoctoriRepository = tipDoctoriRepository;
        this.doctoriRepository = doctoriRepository;
        this.retetaRepository = retetaRepository;
        this.cabineteRepository = cabineteRepository;
        this.reteteCuMedicamenteleRepository = reteteCuMedicamenteleRepository;
    }
    
    @Override
    public List<TipDoctori> findAll() {
        return tipDoctoriRepository.findAll();
    }

    @Override
    public TipDoctori save(TipDoctori tipDoctor) {
        tipDoctoriRepository.save(tipDoctor);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "INSERT",ts});
        return tipDoctor;
    }

    @Override
    public TipDoctori delete(TipDoctori tipDoctor) {
        tipDoctoriRepository.delete(tipDoctor);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "DELETE",ts});
        return tipDoctor;
    }

    @Override
    public TipDoctori update(TipDoctori tipDoctor , String oldName) {
        TipDoctori toBeUpdatedTipDoctor = tipDoctoriRepository.findBynumeSpecializare(oldName);
        List<Doctori> DoctorsWithThisType = doctoriRepository.findBytipDoctor(toBeUpdatedTipDoctor);
        for(Doctori currDoctor : DoctorsWithThisType){
            List<CabineteDoctori> currentCabinetsForCurrentDoctor = cabineteRepository.findByDoctori(currDoctor);
            currDoctor.setCabineteDoctoriList(currentCabinetsForCurrentDoctor);
            List<Retete> currentReteteForCurrentDoctor = retetaRepository.findBydoctorRetete(currDoctor);
            currDoctor.setReteteDoctoriList(currentReteteForCurrentDoctor);
            for(Retete reteteIT : currDoctor.getReteteDoctoriList()){
                List<ReteteCuMedicamentele> currenDoctorReteteCuMedicamentele = reteteCuMedicamenteleRepository.findByReteta(reteteIT);
                reteteIT.setRetetaCuMedicamentul(currenDoctorReteteCuMedicamentele);
            }
        }
        toBeUpdatedTipDoctor.setDoctori(DoctorsWithThisType);
        if(toBeUpdatedTipDoctor != null){
            TipDoctori updatedTipDoctor = new TipDoctori(toBeUpdatedTipDoctor);
            
            updatedTipDoctor.setNumeSpecializare(tipDoctor.getNumeSpecializare());
            
            tipDoctoriRepository.save(updatedTipDoctor);
            String ts = LocalDateTime.now().toString();
            CsvService.writeNext(new String[]{TableName , "UPDATE",ts});
        }
        return toBeUpdatedTipDoctor;
    }

    @Override
    public TipDoctori findBynumeSpecializare(String numeSpecialiazare) {
        return tipDoctoriRepository.findBynumeSpecializare(numeSpecialiazare);
    }
    
}
