/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.repository.TesteLaboratorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dbapplication.models.TesteLaborator;
import com.dbapplication.models.TestePacienti;
import com.dbapplication.repository.TestePacientiRepository;
import java.math.BigDecimal;
import java.util.stream.Collectors;

/**
 *
 * @author misustefan
 */
@Service("TesteLaborator")
@Transactional
public class TesteLaboratorServiceImplementation implements  TesteLaboratorService{
    private final TesteLaboratorRepository testeLaboratorRepository;
    private final TestePacientiRepository testePacientiRepository;

    public TesteLaboratorServiceImplementation(TesteLaboratorRepository testeLaboratorRepository, TestePacientiRepository testePacientiRepository) {
        this.testeLaboratorRepository = testeLaboratorRepository;
        this.testePacientiRepository = testePacientiRepository;
    }
    
    @Override
    public List<TesteLaborator> findAll() {
        return testeLaboratorRepository.findAll();
    }
    
    @Override
    public TesteLaborator save(TesteLaborator testeLaborator) {
        testeLaboratorRepository.save(testeLaborator);
        return testeLaborator;
    }

    @Override
    public TesteLaborator delete(TesteLaborator testeLaborator) {
        testeLaboratorRepository.delete(testeLaborator);
        return testeLaborator;    
    }

    @Override
    public TesteLaborator update(TesteLaborator testeLaborator , String oldName) {
        TesteLaborator toBeUpdatedTestLaborator = testeLaboratorRepository.findByNumeTest(oldName);
        List<TestePacienti> correspondingTestePacientiForTestLaborator = testePacientiRepository.findBytesteLaborator(toBeUpdatedTestLaborator);
        toBeUpdatedTestLaborator.setTestePacientiLaborator(correspondingTestePacientiForTestLaborator);
        if(toBeUpdatedTestLaborator != null){
            for(TestePacienti testePacienti : toBeUpdatedTestLaborator.getTestePacientiLaborator()){
                testePacienti.setTesteLaborator(null);
            }
            TesteLaborator newUpdatedTest = new TesteLaborator(toBeUpdatedTestLaborator);
            newUpdatedTest.setAcuaratete(testeLaborator.getAcuaratete());
            newUpdatedTest.setValoareMaxima(testeLaborator.getValoareMaxima());
            newUpdatedTest.setNumeTest(testeLaborator.getNumeTest());
            newUpdatedTest.setValoareMinima(testeLaborator.getValoareMinima());
            
            List<TestePacienti> newTestePacienti = toBeUpdatedTestLaborator.getTestePacientiLaborator().stream()
                                                    .map(testePacienti -> {
                                                        TestePacienti newTests = new TestePacienti(testePacienti);
                                                        newTests.setPacienti(testePacienti.getPacienti());
                                                        newTests.setTesteLaborator(newUpdatedTest);
                                                        return newTests;
                                                    }).collect(Collectors.toList());
            
            testePacientiRepository.saveAll(newTestePacienti);
            newUpdatedTest.setTestePacientiLaborator(newTestePacienti);
            testeLaboratorRepository.saveAndFlush(newUpdatedTest);
            testePacientiRepository.saveAll(newTestePacienti);
        }
        return toBeUpdatedTestLaborator;
    }

    @Override
    public TesteLaborator findById(Integer idTest) {
        return testeLaboratorRepository.findById(idTest).orElse(null);
    }

    @Override
    public TesteLaborator findByNumeTest(String NumeTest) {
        return testeLaboratorRepository.findByNumeTest(NumeTest);
    }
    
}
