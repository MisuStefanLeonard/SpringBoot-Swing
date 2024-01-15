package com.dbapplication;


import com.dbapplication.models.CabineteDoctori;
import com.dbapplication.models.Doctori;
import com.dbapplication.models.Medicamente;
import com.dbapplication.models.Pacienti;
import com.dbapplication.models.Retete;
import com.dbapplication.models.ReteteCuMedicamentele;
import com.dbapplication.models.TesteLaborator;
import com.dbapplication.models.TestePacienti;
import com.dbapplication.models.TipDoctori;
import com.dbapplication.repository.CabineteDoctoriRepository;
import com.dbapplication.repository.DoctoriRepository;
import com.dbapplication.repository.MedicamenteRepository;
import com.dbapplication.repository.PacientiRepository;
import com.dbapplication.repository.ReteteCuMedicamenteleRepository;
import com.dbapplication.repository.ReteteRepository;
import com.dbapplication.repository.TesteLaboratorRepository;
import com.dbapplication.repository.TestePacientiRepository;
import com.dbapplication.repository.TipDoctoriRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//
//
//
///**
// *
// * @author misustefan
// */
//
@SpringBootTest
public class DoctoriRepositoryTets {
    
    
//    @Autowired
//    private EntityManager entityManager;
//    @Autowired
//    private DoctoriRepository doctoriRepository;
    @Autowired
    private TipDoctoriRepository TipDoctoriRepository;
    @Autowired
    private CabineteDoctoriRepository cabineteRepository;
    @Autowired
    private ReteteRepository retetaRepository;
    @Autowired
    private MedicamenteRepository medicamentRepository;
    @Autowired
    private PacientiRepository pacientiRepository;
    @Autowired
    private ReteteCuMedicamenteleRepository reteteCuMedicamenteleRepository;
    @Autowired
    private TesteLaboratorRepository testeLaboratorRepository;
    @Autowired
    private TestePacientiRepository testePacientiRepository;
    
//        @Test
//        void tse(){
//           List<Doctori> doctori  = doctoriRepository.findAll();
//           for(Doctori d : doctori){
//               System.out.println(d.getDoctorNume());
//           }
//        }
//    @Test
//    void saveM(){
//        Doctori doctor = new Doctori();
//        Doctori saved = new Doctori();
//        doctor.setDoctorCUI(20193845);
//        String birthDate = "1987-11-15";
//        try {
//        java.util.Date date = new SimpleDateFormat("yyyy-mm-dd").parse(birthDate);
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//        doctor.setDoctorDataNastere(sqlDate);
//        doctor.setDoctorNume("Mihaela");
//        doctor.setDoctorPrenume("Daniela");
//        
//        saved = doctoriRepository.save(doctor);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//        System.out.println(saved.getDoctorCUI());
//    }
//    @Test
//    void test() {
//    System.out.println("END FIND1");
//    Doctori oldDoctor = doctoriRepository.findById(10492345).orElse(null);
//    System.out.println("END FIND1");
//    if (oldDoctor != null) {
//        System.out.println("END FIND2");
//            List<Retete> oldDoctorRetete = retetaRepository.findBydoctorRetete(oldDoctor);
//             System.out.println("END FIND2");
//            oldDoctor.setReteteDoctoriList(oldDoctorRetete);
//            System.out.println("----------");
//            for (Retete reteteIT : oldDoctorRetete) {
//                System.out.println("retete id  " + reteteIT.getRetetaId());
//            }
//             System.out.println("----------");
//            if (!oldDoctorRetete.isEmpty()) {
//                List<ReteteCuMedicamentele> oldDoctorReteteCuMedicamentele = new LinkedList<ReteteCuMedicamentele>();
//                for(Retete reteteIT : oldDoctorRetete){
//                    List<ReteteCuMedicamentele> oldDoctorCurrentListForReteta = reteteCuMedicamenteleRepository.findByReteta(reteteIT);
//                    oldDoctorReteteCuMedicamentele.addAll(oldDoctorCurrentListForReteta);
//                }
//                for(ReteteCuMedicamentele reteteIT : oldDoctorReteteCuMedicamentele){
//                    System.out.println("reteta id " + reteteIT.getReteta().getRetetaId());
//                }
//             }//else{
////                System.out.println("EMPTY");
////            }
//        }
//    }
//
//    
//    @Test
//    void updateM(){
//        Doctori oldDoctor = doctoriRepository.findById(10492355).orElse(null);
//        TipDoctori foundTipDoctor = TipDoctoriRepository.findById(3).orElse(null);
//        List<CabineteDoctori> oldDoctorCabinete = cabineteRepository.findByDoctori(oldDoctor);
//        oldDoctor.setCabineteDoctoriList(oldDoctorCabinete);
//        List<Retete> oldDoctorRetete = retetaRepository.findBydoctorRetete(oldDoctor);
//        List<ReteteCuMedicamentele> oldDoctorReteteCuMedicamentele = new LinkedList<ReteteCuMedicamentele>();
//        for(Retete rIterator : oldDoctorRetete){
//            List<ReteteCuMedicamentele> oldDoctorCurrentListForReteta = reteteCuMedicamenteleRepository.findByReteta(rIterator);
//            rIterator.setRetetaCuMedicamentul(oldDoctorReteteCuMedicamentele);
//            oldDoctorReteteCuMedicamentele.addAll(oldDoctorCurrentListForReteta);
//        }
//        oldDoctor.setReteteDoctoriList(oldDoctorRetete);
//        Integer newCUI = 19401234;
//        if(foundTipDoctor != null){
//          
//            Doctori updatedDoctor = new Doctori(oldDoctor);
//            
//            updatedDoctor.setDoctorCUI(newCUI);
//            
//            Date date = new Date(1978-12-28);
//            updatedDoctor.setDoctorDataNastere(date);
//            updatedDoctor.setDoctorNume("Mihaela");
//            updatedDoctor.setDoctorPrenume("Daniela");
//            updatedDoctor.setTipDoctor(foundTipDoctor);
//            System.out.println("----------");
//            List<CabineteDoctori> cabineteClone = oldDoctor.getCabineteDoctoriList().stream()
//                                                  .map(cabinete -> {
//                                                    CabineteDoctori cabinetClone = new CabineteDoctori(cabinete);
//                                                    cabinetClone.setDoctori(updatedDoctor);
//                                                    return cabinetClone;
//                                                   }) 
//                                                  .collect(Collectors.toList());
//            List<Retete> reteteClone = oldDoctor.getReteteDoctoriList().stream()
//                                                  .map(retete -> {
//                                                    Retete retetaClone = new Retete(retete);
//                                                    retetaClone.setDoctorRetete(updatedDoctor);
//                                                    retetaClone.setPacientRetete(retete.getPacientRetete());
//                                                    return retetaClone;
//                                                  }) 
//                                                  .collect(Collectors.toList());
//           
//            
//            updatedDoctor.setCabineteDoctoriList(cabineteClone);
//            updatedDoctor.setReteteDoctoriList(reteteClone);
//           
//            doctoriRepository.saveAndFlush(updatedDoctor);
//            cabineteRepository.saveAll(cabineteClone);
//            retetaRepository.saveAllAndFlush(reteteClone);
//            List<Retete> updatedDoctorRetete = retetaRepository.findBydoctorRetete(updatedDoctor);
//            List<ReteteCuMedicamentele> updatedReteteCuMedicamentele = new LinkedList<>();
//            for (int i = 0; i < Math.min(updatedDoctorRetete.size(), oldDoctorRetete.size()); i++) {
//                Retete rIterator = updatedDoctorRetete.get(i);
//                Retete rIteratorOld = oldDoctorRetete.get(i);
//
//                List<ReteteCuMedicamentele> currentOldDoctorReteteCuMedicamentele = reteteCuMedicamenteleRepository.findByReteta(rIteratorOld);
//
//                for (ReteteCuMedicamentele IT : currentOldDoctorReteteCuMedicamentele) {
//                    IT.setReteta(rIterator);
//                }
//
//                updatedReteteCuMedicamentele.addAll(currentOldDoctorReteteCuMedicamentele);
//            }
//            
//            if(updatedDoctorRetete.isEmpty()){
//                System.out.println("EMPTY LIST !");
//            }else{
//                for(Retete it: updatedDoctorRetete){
//                System.out.println("ID reteta " + it.getRetetaId());
//                System.out.println("" + it.getDoctorRetete().getDoctorCUI());
//                }
//                // am id-uri noilor retete
//                // cum le leg cu vechile ??? dar am nevoie de cantitatea si medicamentul pt fiecare... hmm
//
//                doctoriRepository.delete(oldDoctor);
//                // dupa ce salvez retetele ar trebuie sa le trag din baza de date cu noile id-uri
//                  System.out.println("--------------");
//                System.out.println("FOURTH TRANSACTION END");
//                  System.out.println("--------------");
//                    System.out.println("--------------");
//                System.out.println("FIFTH TRANSACTION END");
//
//                reteteCuMedicamenteleRepository.saveAll(updatedReteteCuMedicamentele);
//                System.out.println("FIFTH TRANSACTION END");
//                
//            }
//            
//        }else{
//            throw new Error("Doctor not found!");
//        }
//        
//        Doctori foundAfter = doctoriRepository.findById(newCUI).orElse(null);
//    }
//    @Test
//    void deleteM(){
//        Doctori doctor = doctoriRepository.findById(20120905).orElse(null);
//        if(doctor == null){
//            System.out.println("doctor " + 20120905 + " not found");
//        }else
//            doctoriRepository.delete(doctor);
//        
//    }
//    
//    @Test
//    void findAllM(){
//        List<Doctori> docList = new LinkedList<Doctori>();
//        docList=doctoriRepository.findAll();
//        for(Doctori docIt : docList){
//            System.out.println(docIt.getDoctorCUI());
//        }
//    }
//      @Test
//    void getCabinete(){
//        List<CabineteDoctori> cabineteList = new LinkedList<CabineteDoctori>();
//        cabineteList = cabineteRepository.findAll();
//        
//    }
//    @Test
//    void findCabinete(){
//        List<Doctori> docListnew = new LinkedList<Doctori>();
//        docListnew=doctoriRepository.findAll();
//        for(Doctori docIt : docListnew){
//            System.out.println(docIt.getDoctorCUI());
//            for(CabineteDoctori cabIT: docIt.getCabineteDoctoriList()){
//                System.out.println("CABINET doctor CUI :" + cabIT.getDoctori().getDoctorCUI());
//            }
//            System.out.println("");
//            for(Retete retetIT: docIt.getReteteDoctoriList()){
//                System.out.println("Retete doctor CUI:" + retetIT.getDoctorRetete().getDoctorCUI())
//;            }
//            System.out.println("");
//        }
//        
//    }
//    
//    @Test
//    void updateCabinete(){
//        CabineteDoctori cabinetToBeUpdated = cabineteRepository.findById(14).orElse(null);
//        Integer doctorCUI = cabinetToBeUpdated.getDoctori().getDoctorCUI();
//        Doctori doctorCUIIsPresent = doctoriRepository.findById(19401234).orElse(null);
//        if(doctorCUIIsPresent != null && Objects.equals(doctorCUI, doctorCUIIsPresent.getDoctorCUI())){
//            if(cabinetToBeUpdated != null){
//                cabinetToBeUpdated.setCabinetCodPostal("182345");
//                cabinetToBeUpdated.setCabinetNrTelefon("0234543143");
//                cabinetToBeUpdated.setCabinetNumarStrada(2);
//                cabinetToBeUpdated.setCabinetNume("C.M.I MIHA DINA");
//                cabinetToBeUpdated.setCabinetOras("Brasov");
//                cabinetToBeUpdated.setCabinetStrada("Aleea Calatorului");
//                cabinetToBeUpdated.setDoctori(doctorCUIIsPresent);
//
//                cabineteRepository.save(cabinetToBeUpdated);
//            }else{
//                throw new Error("CABINET NOT FOUND!");
//            }
//        }else{
//            throw new Error("DOCTOR CUI NOT FOUND!");
//        }
//    }
//    @Test
//    void updateMedicament(){
//        Medicamente medicamentToBeUpdated = medicamentRepository.findById(23).orElse(null);
//        if(medicamentToBeUpdated != null){
//            medicamentToBeUpdated.setMedicamentNume("KUKOXIN");
//            medicamentToBeUpdated.setMedicamentGramajMg(500);
//            medicamentToBeUpdated.setTipMedicament("dizolvabil");
//            
//            medicamentRepository.save(medicamentToBeUpdated);
//        }else{
//            throw new Error("Medicament not found.Cannot update!");
//        }
//    }
//    
//    @Test
//    void updateRetete(){
//        Retete toBeUpdatedReteta = retetaRepository.findById(24).orElse(null);
//        Doctori isPresentDoctor = doctoriRepository.findById(12183299).orElse(null);
//        Pacienti isPresentPacient = pacientiRepository.findById("1871223765432").orElse(null);
//        List<ReteteCuMedicamentele> ReteteCuMedicamenteleForReteta = reteteCuMedicamenteleRepository.findByReteta(toBeUpdatedReteta);
//        toBeUpdatedReteta.setRetetaCuMedicamentul(ReteteCuMedicamenteleForReteta);
//        if(toBeUpdatedReteta != null && isPresentDoctor != null && isPresentPacient != null){
//            Retete newReteta = new Retete(toBeUpdatedReteta);
//            LocalDate newDateInitial = LocalDate.of(2023 , 10 , 25);
//            newReteta.setRetetaDataEmitere(Date.valueOf(newDateInitial));
//            LocalDate newDateExpirare = LocalDate.of(2023, 11, 25);
//            newReteta.setRetetaDataExpirare(Date.valueOf(newDateExpirare));
//            newReteta.setDoctorRetete(isPresentDoctor);
//            newReteta.setPacientRetete(isPresentPacient);
//            
//            retetaRepository.save(newReteta);
//        }
//    }
//    
//    @Test
//    void updateReteteCuMedicamentele(){
//        ReteteCuMedicamentele toBeUpdatedReteteCuMedicamentul = reteteCuMedicamenteleRepository.findById(25).orElse(null);
//        Medicamente toBeReplacedMedicament = medicamentRepository.findById(4).orElse(null);
//        Retete toBeReplacedReteta = retetaRepository.findById(23).orElse(null);
//        if(toBeUpdatedReteteCuMedicamentul != null){
//            ReteteCuMedicamentele newUpdatedRetetaCuMedicamentul = new ReteteCuMedicamentele(toBeUpdatedReteteCuMedicamentul);
//            
//            newUpdatedRetetaCuMedicamentul.setCantitate(2);
//            newUpdatedRetetaCuMedicamentul.setMedicament(toBeReplacedMedicament);
//            newUpdatedRetetaCuMedicamentul.setReteta(toBeReplacedReteta);
//            
//            reteteCuMedicamenteleRepository.save(newUpdatedRetetaCuMedicamentul);
//        }
//    }
//    
//    @Test
//    
//    void updateTesteLaborator(){
//        TesteLaborator toBeUpdatedTestLaborator = testeLaboratorRepository.findByNumeTest("TEST5");
//        List<TestePacienti> correspondingTestePacientiForTestLaborator = testePacientiRepository.findBytesteLaborator(toBeUpdatedTestLaborator);
//        toBeUpdatedTestLaborator.setTestePacientiLaborator(correspondingTestePacientiForTestLaborator);
//        if(toBeUpdatedTestLaborator != null){
//            for(TestePacienti testePacienti : toBeUpdatedTestLaborator.getTestePacientiLaborator()){
//                testePacienti.setTesteLaborator(null);
//            }
//            TesteLaborator newUpdatedTest = new TesteLaborator(toBeUpdatedTestLaborator);
//            newUpdatedTest.setAcuaratete(BigDecimal.valueOf(0.85));
//            newUpdatedTest.setValoareMaxima(50);
//            newUpdatedTest.setNumeTest("TEST3");
//            newUpdatedTest.setValoareMinima(40);
//            
//            List<TestePacienti> newTestePacienti = toBeUpdatedTestLaborator.getTestePacientiLaborator().stream()
//                                                    .map(testePacienti -> {
//                                                        TestePacienti newTests = new TestePacienti(testePacienti);
//                                                        newTests.setPacienti(testePacienti.getPacienti());
//                                                        newTests.setTesteLaborator(newUpdatedTest);
//                                                        return newTests;
//                                                    }).collect(Collectors.toList());
//            
//            testePacientiRepository.saveAll(newTestePacienti);
//            newUpdatedTest.setTestePacientiLaborator(newTestePacienti);
//            testeLaboratorRepository.saveAndFlush(newUpdatedTest);
//            testePacientiRepository.saveAll(newTestePacienti);
//        }
//    }
//    
    @Test
    void updateTestPacienti(){
        TestePacienti toBeUpdatedTestePacienti  = testePacientiRepository.findById(16).orElse(null);
        Pacienti toBeSetToTestePacienti = pacientiRepository.findById("5031230340925").orElse(null);
        TesteLaborator toBeSetToTestePacientiTestLaborator = testeLaboratorRepository.findByNumeTest("HCG");
        if(toBeUpdatedTestePacienti != null){
            TestePacienti newUpdatedTestePacienti = new TestePacienti(toBeUpdatedTestePacienti);
            LocalDate newDateEmitere = LocalDate.of(2023, 10, 25);
            newUpdatedTestePacienti.setDataEmitere(Date.valueOf(newDateEmitere));
            newUpdatedTestePacienti.setRezultate("bune");
            newUpdatedTestePacienti.setValoareTest(BigDecimal.valueOf(10));
            newUpdatedTestePacienti.setPacienti(toBeSetToTestePacienti);
            newUpdatedTestePacienti.setTesteLaborator(toBeSetToTestePacientiTestLaborator);
            
            testePacientiRepository.save(newUpdatedTestePacienti);
        }
    }
//    
//    @Test
//    void updateTipDoctor(){
//        TipDoctori toBeUpdatedTipDoctor = TipDoctoriRepository.findBynumeSpecializare("Cardiolog");
//        List<Doctori> DoctorsWithThisType = doctoriRepository.findBytipDoctor(toBeUpdatedTipDoctor);
//        for(Doctori currDoctor : DoctorsWithThisType){
//            List<CabineteDoctori> currentCabinetsForCurrentDoctor = cabineteRepository.findByDoctori(currDoctor);
//            currDoctor.setCabineteDoctoriList(currentCabinetsForCurrentDoctor);
//            List<Retete> currentReteteForCurrentDoctor = retetaRepository.findBydoctorRetete(currDoctor);
//            currDoctor.setReteteDoctoriList(currentReteteForCurrentDoctor);
//            for(Retete reteteIT : currDoctor.getReteteDoctoriList()){
//                List<ReteteCuMedicamentele> currenDoctorReteteCuMedicamentele = reteteCuMedicamenteleRepository.findByReteta(reteteIT);
//                reteteIT.setRetetaCuMedicamentul(currenDoctorReteteCuMedicamentele);
//            }
//        }
//        toBeUpdatedTipDoctor.setDoctori(DoctorsWithThisType);
//        if(toBeUpdatedTipDoctor != null){
//            TipDoctori updatedTipDoctor = new TipDoctori(toBeUpdatedTipDoctor);
//            
//            updatedTipDoctor.setNumeSpecializare("Cardiolog1");
//            
//            TipDoctoriRepository.save(updatedTipDoctor);
//        }
//    }
////    @Test
////    void updatePacient(){
////        Pacienti toBeUpdated = pacientiRepository.findById("1850415515342").orElse(null);
////        if(toBeUpdated != null){
////        
////        }
}

