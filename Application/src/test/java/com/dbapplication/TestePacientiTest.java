///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package com.dbapplication;
//
//import com.dbapplication.models.Doctori;
//import com.dbapplication.models.Pacienti;
//import com.dbapplication.models.Retete;
//import com.dbapplication.models.ReteteCuMedicamentele;
//import com.dbapplication.models.TesteLaborator;
//import com.dbapplication.models.TestePacienti;
//import com.dbapplication.repository.DoctoriRepository;
//import com.dbapplication.repository.PacientiRepository;
//import com.dbapplication.repository.ReteteCuMedicamenteleRepository;
//import com.dbapplication.repository.ReteteRepository;
//import com.dbapplication.repository.TesteLaboratorRepository;
//import com.dbapplication.repository.TestePacientiRepository;
//import com.dbapplication.service.PacientiServiceImplementation;
//import jakarta.persistence.EntityManager;
//import java.math.BigDecimal;
//import java.sql.Date;
//import static java.sql.JDBCType.NULL;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//
///**
// *
// * @author misustefan
// */
//@SpringBootTest
//public class TestePacientiTest {
//    @Autowired
//    private DoctoriRepository doctorRepository;
//    @Autowired
//    private PacientiRepository pacientiRepository;
//    @Autowired
//    private TesteLaboratorRepository testeLaboratorRepository;
//    @Autowired
//    private TestePacientiRepository testePacientiRepository;
//    @Autowired
//    private ReteteRepository reteteRepo;
//    @Autowired
//    private ReteteCuMedicamenteleRepository reteteCuMedicamenteleRepository;
//    @Autowired
//    private PacientiServiceImplementation pacientiService;
//    
//    @Test
//    //@Transactional
//    void updatePacient(){
//        Pacienti oldPacient = pacientiRepository.findById("1850415515342").orElse(null);
//        List<Retete> oldRetetePacient = reteteRepo.findBypacientRetete(oldPacient);
//        List<TestePacienti> oldTestePacient = testePacientiRepository.findBypacienti(oldPacient);
//        List<ReteteCuMedicamentele> oldPacientReteteCuMedicamentele = new LinkedList<ReteteCuMedicamentele>();
//        for(Retete rIterator : oldRetetePacient){
//            List<ReteteCuMedicamentele> oldPacientCurrentListForReteta = reteteCuMedicamenteleRepository.findByReteta(rIterator);
//            rIterator.setRetetaCuMedicamentul(oldPacientCurrentListForReteta);
//            oldPacientReteteCuMedicamentele.addAll(oldPacientCurrentListForReteta);
//        }
//        oldPacient.setRetetePacienti(oldRetetePacient);
//        oldPacient.setTestePacienti(oldTestePacient);
//        LocalDate newDate = LocalDate.of(1971, 12, 27);
//        String newCNP = "1711227503012";
//        if(oldPacient != null){
//            // cloning newPacient
//            Pacienti newPacient = new Pacienti(oldPacient);
//            
//            // updating
//            newPacient.setPacientDataNastere(Date.valueOf(newDate));
//            newPacient.setPacientCnp(newCNP);
//            newPacient.setPacientNume("TEST2");
//            newPacient.setPacientPrenume("TEST2");
//            newPacient.setPacientOrasNastere("TEST2");
//            newPacient.setPacientPolitaMedicala("Nu");
//            // cloning it s relations
//            List<Retete> reteteClone = oldPacient.getRetetePacienti().stream()
//                                       .map(retete -> {
//                                           Retete newReteta = new Retete(retete);
//                                           newReteta.setDoctorRetete(retete.getDoctorRetete());
//                                           newReteta.setPacientRetete(newPacient);
//                                           return newReteta;
//                                       }).collect(Collectors.toList());
//            List<TestePacienti> testePacientiClone = oldPacient.getTestePacienti().stream()
//                                                     .map(testePacient -> {
//                                                         TestePacienti newTest = new TestePacienti(testePacient);
//                                                         newTest.setPacienti(newPacient);
//                                                         newTest.setTesteLaborator(testePacient.getTesteLaborator());
//                                                         return newTest;
//                                                     }).collect(Collectors.toList());
//            //setting new modified child entities to it s parent
//            newPacient.setRetetePacienti(reteteClone);
//            newPacient.setTestePacienti(testePacientiClone);
//            // updating
//            pacientiRepository.saveAndFlush(newPacient);
//            reteteRepo.saveAll(reteteClone);
//            List<Retete> updatedPacientRetete = reteteRepo.findBypacientRetete(newPacient);
//            List<ReteteCuMedicamentele> updatedReteteCuMedicamentele = new LinkedList<>();
//            for (int i = 0; i < Math.min(updatedPacientRetete.size(), oldRetetePacient.size()); i++) {
//                Retete rIterator = updatedPacientRetete.get(i);
//                Retete rIteratorOld = oldRetetePacient.get(i);
//
//                List<ReteteCuMedicamentele> currentOldPacientReteteCuMedicamentele = reteteCuMedicamenteleRepository.findByReteta(rIteratorOld);
//
//                for (ReteteCuMedicamentele IT : currentOldPacientReteteCuMedicamentele) {
//                    IT.setReteta(rIterator);
//                }
//
//                updatedReteteCuMedicamentele.addAll(currentOldPacientReteteCuMedicamentele);
//            }
//            reteteCuMedicamenteleRepository.saveAll(updatedReteteCuMedicamentele);
//            testePacientiRepository.saveAll(testePacientiClone);
//            
//            
//        }
//    }
//    
//    @Test
//    void saveTeste(){
//        TestePacienti newtest = new TestePacienti();
//        TesteLaborator testeLab = testeLaboratorRepository.findByNumeTest("HCG");
//        Pacienti pacient = pacientiRepository.findById("5031230340925").orElse(null);
//        if(pacient != null && testeLab != null){
//            try{
//                newtest.setTesteLaborator(testeLab);
//                newtest.setValoareTest(BigDecimal.valueOf(85));
//                newtest.setPacienti(pacient);
//                String data = "2023-03-28";
//                java.util.Date date = new SimpleDateFormat("yyyy-mm-dd").parse(data); 
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                newtest.setDataEmitere(sqlDate);
//                newtest.setRezultate("prost");
//                testePacientiRepository.save(newtest);
//            }catch(ParseException e){
//               e.printStackTrace();
//            }
//        }
//        
//        System.out.println("saved " + newtest);
//        
//    }
//    @Test
//     void saveReteta(){
//        Retete newReteta = new Retete();
//        Doctori doctor = doctorRepository.findById(10447120).orElse(null);
//        Pacienti pacient = pacientiRepository.findById("1801113874609").orElse(null);
//        if(pacient != null && doctor != null){
//            try{
//                newReteta.setDoctorRetete(doctor);
//                newReteta.setPacientRetete(pacient);
//                String data = "2023-03-28";
//                java.util.Date date = new SimpleDateFormat("yyyy-mm-dd").parse(data); 
//                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                newReteta.setRetetaDataEmitere(sqlDate);
//                newReteta.setRetetaDataExpirare(null);
//                newReteta.setTipReteta("permanent");
//                reteteRepo.save(newReteta);
//            }catch(ParseException e){
//               e.printStackTrace();
//            }
//        }
//        
//        System.out.println("saved " + newReteta.getRetetaDataExpirare());
//        
//    }
//    
//    @Test 
//    void showtestelab(){
//        List<Retete> reteteFind = reteteRepo.findAll();
//        for(Retete reteteIT : reteteFind){
//            System.out.println("test:"+ reteteIT.getPacientRetete().getPacientCnp());
//        }
//    }
//    @Test
//    void join(){
//       List<Pacienti> joinRes = pacientiService.pacientsWhoHaveOnPrescriptionMedicamentWithName("PARACETAMOL_325");
//       for(Pacienti it : joinRes){
//           System.out.println("name " + it.getPacientNume());
//       }
//    }
//    
//}
