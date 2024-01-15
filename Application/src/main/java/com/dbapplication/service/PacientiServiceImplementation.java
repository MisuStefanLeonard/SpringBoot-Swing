/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Pacienti;
import com.dbapplication.models.Retete;
import com.dbapplication.models.ReteteCuMedicamentele;
import com.dbapplication.models.TestePacienti;
import com.dbapplication.repository.PacientiRepository;
import com.dbapplication.repository.ReteteCuMedicamenteleRepository;
import com.dbapplication.repository.ReteteRepository;
import com.dbapplication.repository.TestePacientiRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
@Service("PacientiService")
@Transactional
public class PacientiServiceImplementation implements PacientiService{
    
    private final PacientiRepository pacientiRepository;
    private final ReteteRepository reteteRepo;
    private final TestePacientiRepository testePacientiRepository;
    private final ReteteCuMedicamenteleRepository reteteCuMedicamenteleRepository;
    private final EntityManager entityManagerPacienti;
    
    @Autowired
    public PacientiServiceImplementation(PacientiRepository pacientiRepository, ReteteRepository reteteRepo, TestePacientiRepository testePacientiRepository, ReteteCuMedicamenteleRepository reteteCuMedicamenteleRepository, EntityManager entityManagerPacienti) {
        this.pacientiRepository = pacientiRepository;
        this.reteteRepo = reteteRepo;
        this.testePacientiRepository = testePacientiRepository;
        this.reteteCuMedicamenteleRepository = reteteCuMedicamenteleRepository;
        this.entityManagerPacienti = entityManagerPacienti;
    }
    
    @Override
    public List<Pacienti> findAll(){
        return pacientiRepository.findAll();
    }

    @Override
    public Pacienti save(Pacienti pacienti) {
        pacientiRepository.save(pacienti);
        return pacienti;
    }

    @Override
     public Pacienti delete(Pacienti pacienti) {
        pacientiRepository.delete(pacienti);
        return pacienti;
    }

    @Override
    public Pacienti update(Pacienti pacienti, String oldCNP) {
        Pacienti oldPacient = pacientiRepository.findById(oldCNP).orElse(null);
        List<Retete> oldRetetePacient = reteteRepo.findBypacientRetete(oldPacient);
        List<TestePacienti> oldTestePacient = testePacientiRepository.findBypacienti(oldPacient);
        List<ReteteCuMedicamentele> oldPacientReteteCuMedicamentele = new LinkedList<ReteteCuMedicamentele>();
        for(Retete rIterator : oldRetetePacient){
            List<ReteteCuMedicamentele> oldPacientCurrentListForReteta = reteteCuMedicamenteleRepository.findByReteta(rIterator);
            rIterator.setRetetaCuMedicamentul(oldPacientCurrentListForReteta);
            oldPacientReteteCuMedicamentele.addAll(oldPacientCurrentListForReteta);
        }
        oldPacient.setRetetePacienti(oldRetetePacient);
        oldPacient.setTestePacienti(oldTestePacient);
        if(oldPacient != null){
            // cloning newPacient
            Pacienti newPacient = new Pacienti(oldPacient);
            
            // updating
            newPacient.setPacientDataNastere(pacienti.getPacientDataNastere());
            newPacient.setPacientCnp(pacienti.getPacientCnp());
            newPacient.setPacientNume(pacienti.getPacientNume());
            newPacient.setPacientPrenume(pacienti.getPacientPrenume());
            newPacient.setPacientOrasNastere(pacienti.getPacientOrasNastere());
            newPacient.setPacientPolitaMedicala(pacienti.getPacientPolitaMedicala());
            // cloning it s relations
            List<Retete> reteteClone = oldPacient.getRetetePacienti().stream()
                                       .map(retete -> {
                                           Retete newReteta = new Retete(retete);
                                           newReteta.setDoctorRetete(retete.getDoctorRetete());
                                           newReteta.setPacientRetete(newPacient);
                                           return newReteta;
                                       }).collect(Collectors.toList());
            List<TestePacienti> testePacientiClone = oldPacient.getTestePacienti().stream()
                                                     .map(testePacient -> {
                                                         TestePacienti newTest = new TestePacienti(testePacient);
                                                         newTest.setPacienti(newPacient);
                                                         newTest.setTesteLaborator(testePacient.getTesteLaborator());
                                                         return newTest;
                                                     }).collect(Collectors.toList());
            //setting new modified child entities to it s parent
            newPacient.setRetetePacienti(reteteClone);
            newPacient.setTestePacienti(testePacientiClone);
            // updating
            pacientiRepository.saveAndFlush(newPacient);
            reteteRepo.saveAll(reteteClone);
            List<Retete> updatedPacientRetete = reteteRepo.findBypacientRetete(newPacient);
            List<ReteteCuMedicamentele> updatedReteteCuMedicamentele = new LinkedList<>();
            for (int i = 0; i < Math.min(updatedPacientRetete.size(), oldRetetePacient.size()); i++) {
                Retete rIterator = updatedPacientRetete.get(i);
                Retete rIteratorOld = oldRetetePacient.get(i);

                List<ReteteCuMedicamentele> currentOldPacientReteteCuMedicamentele = reteteCuMedicamenteleRepository.findByReteta(rIteratorOld);

                for (ReteteCuMedicamentele IT : currentOldPacientReteteCuMedicamentele) {
                    IT.setReteta(rIterator);
                }

                updatedReteteCuMedicamentele.addAll(currentOldPacientReteteCuMedicamentele);
            }
            reteteCuMedicamenteleRepository.saveAll(updatedReteteCuMedicamentele);
            testePacientiRepository.saveAll(testePacientiClone);
                 
        }
        return oldPacient;
    }

    @Override
    public Pacienti findById(String pacientCnp) {
        return pacientiRepository.findById(pacientCnp).orElse(null);
    }

    @Override
    public List<Pacienti> pacientsWhoHaveOnPrescriptionMedicamentWithNameAndWithTestName(String medicamentName , String numeTest) {
         String queryName = "Pacienti.pacientsWhoHaveOnPrescriptionMedicamentWithNameAndWithTestName";
         TypedQuery<Pacienti> query = entityManagerPacienti.createNamedQuery(queryName, Pacienti.class);
         query.setParameter("medicament_name",medicamentName);
         query.setParameter("test_name", numeTest);
         return query.getResultList();
    }
}

