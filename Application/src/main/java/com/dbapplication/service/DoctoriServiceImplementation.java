/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.DTOmodels.DoctoriDTO;
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
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author misustefan
 */
@Service("DoctoriService")
@Transactional
public class DoctoriServiceImplementation implements DoctoriService{
    private final DoctoriRepository doctoriRepository;
    private final TipDoctoriRepository tipDoctoriRepository;
    private final CabineteDoctoriRepository cabineteRepository;
    private final ReteteRepository reteteRepository;
    private final ReteteCuMedicamenteleRepository RCMRepository;
    private final EntityManager doctorEntityManager;
    private final String TableName = "doctori";
    @Autowired
    public DoctoriServiceImplementation(DoctoriRepository doctoriRepository, TipDoctoriRepository tipDoctoriRepository, CabineteDoctoriRepository cabineteRepository, ReteteRepository reteteRepository, ReteteCuMedicamenteleRepository RCMRepository, EntityManager doctorEntityManager) {
        this.doctoriRepository = doctoriRepository;
        this.tipDoctoriRepository = tipDoctoriRepository;
        this.cabineteRepository = cabineteRepository;
        this.reteteRepository = reteteRepository;
        this.RCMRepository = RCMRepository;
        this.doctorEntityManager = doctorEntityManager;
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
    public List<Doctori> findAll(){
        return doctoriRepository.findAll();
    }
    @Override
    public Doctori save(Doctori doctori){
        doctoriRepository.save(doctori);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "INSERT",ts});
        return doctori;
    }
    @Override
    public void delete(Doctori doctori){
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "DELETE",ts});
        doctoriRepository.delete(doctori);
    }
    
    @Override
    public Doctori update(Doctori doctori,Integer oldCUI ,String specializationName){
        Doctori oldDoctor = doctoriRepository.findById(oldCUI).orElse(null);
        TipDoctori tipDoctor = tipDoctoriRepository.findBynumeSpecializare(specializationName);
        List<CabineteDoctori> oldDoctorCabinete = cabineteRepository.findByDoctori(oldDoctor);
        oldDoctor.setCabineteDoctoriList(oldDoctorCabinete);
        List<Retete> oldDoctorRetete = reteteRepository.findBydoctorRetete(oldDoctor);
        List<ReteteCuMedicamentele> oldDoctorReteteCuMedicamentele = new LinkedList<ReteteCuMedicamentele>();
        for(Retete rIterator : oldDoctorRetete){
            List<ReteteCuMedicamentele> oldDoctorCurrentListForReteta = RCMRepository.findByReteta(rIterator);
            rIterator.setRetetaCuMedicamentul(oldDoctorReteteCuMedicamentele);
            oldDoctorReteteCuMedicamentele.addAll(oldDoctorCurrentListForReteta);
        }
        oldDoctor.setReteteDoctoriList(oldDoctorRetete);
        
        if(tipDoctor != null){
            Doctori updatedDoctor = new Doctori(oldDoctor);
            updatedDoctor.setDoctorCUI(doctori.getDoctorCUI());
            updatedDoctor.setDoctorDataNastere(doctori.getDoctorDataNastere());
            updatedDoctor.setDoctorNume(doctori.getDoctorNume());
            updatedDoctor.setDoctorPrenume(doctori.getDoctorPrenume());
            updatedDoctor.setTipDoctor(tipDoctor);
            List<CabineteDoctori> cabineteClone = oldDoctor.getCabineteDoctoriList().stream()
                                                  .map(cabinete -> {
                                                    CabineteDoctori cabinetClone = new CabineteDoctori(cabinete);
                                                    cabinetClone.setDoctori(updatedDoctor);
                                                    return cabinetClone;
                                                   }) 
                                                  .collect(Collectors.toList());
           
            List<Retete> reteteClone = oldDoctor.getReteteDoctoriList().stream()
                                                  .map(retete -> {
                                                    Retete retetaClone = new Retete(retete);
                                                    retetaClone.setDoctorRetete(updatedDoctor);
                                                    retetaClone.setPacientRetete(retete.getPacientRetete());
                                                    return retetaClone;
                                                  }) 
                                                  .collect(Collectors.toList());
           updatedDoctor.setCabineteDoctoriList(cabineteClone);
           updatedDoctor.setReteteDoctoriList(reteteClone);
           doctoriRepository.saveAndFlush(updatedDoctor);
           cabineteRepository.saveAll(cabineteClone);
           reteteRepository.saveAll(reteteClone);
           List<Retete> updatedDoctorRetete = reteteRepository.findBydoctorRetete(updatedDoctor);
           List<ReteteCuMedicamentele> updatedReteteCuMedicamentele = new LinkedList<>();
           for (int i = 0; i < Math.min(updatedDoctorRetete.size(), oldDoctorRetete.size()); i++) {
               Retete rIterator = updatedDoctorRetete.get(i);
               Retete rIteratorOld = oldDoctorRetete.get(i);

               List<ReteteCuMedicamentele> currentOldDoctorReteteCuMedicamentele = RCMRepository.findByReteta(rIteratorOld);

               for (ReteteCuMedicamentele IT : currentOldDoctorReteteCuMedicamentele) {
                   IT.setReteta(rIterator);
               }

               updatedReteteCuMedicamentele.addAll(currentOldDoctorReteteCuMedicamentele);
           }
           doctoriRepository.delete(oldDoctor);
           RCMRepository.saveAll(updatedReteteCuMedicamentele);
            String ts = LocalDateTime.now().toString();
            CsvService.writeNext(new String[]{TableName , "UPDATE",ts});
        }else{
            throw new Error("Doctor not found!");
        }
        return doctori;
    }
        
  
    // de adaugat metodele pt save,delete,update
    @Override
    public Doctori findById(Integer doctorCUI) {
        return doctoriRepository.findById(doctorCUI).orElse(null);
    }

    @Override
    public List<DoctoriDTO> getEveryDoctorsReteteLessThanACount(Integer reteteCount) {
        String queryName = "Doctori.getEveryDoctorsReteteLessThanACount";
        TypedQuery<DoctoriDTO> query = doctorEntityManager.createNamedQuery(queryName , DoctoriDTO.class);
        query.setParameter("reteteCount", reteteCount);
        return query.getResultList();
    }
}
