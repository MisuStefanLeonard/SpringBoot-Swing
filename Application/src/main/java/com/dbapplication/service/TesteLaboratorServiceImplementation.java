/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.repository.TesteLaboratorRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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
    private final String TableName = "teste_laborator";

    public TesteLaboratorServiceImplementation(TesteLaboratorRepository testeLaboratorRepository, TestePacientiRepository testePacientiRepository) {
        this.testeLaboratorRepository = testeLaboratorRepository;
        this.testePacientiRepository = testePacientiRepository;
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
    public List<TesteLaborator> findAll() {
        return testeLaboratorRepository.findAll();
    }
    
    @Override
    public TesteLaborator save(TesteLaborator testeLaborator) {
        testeLaboratorRepository.save(testeLaborator);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "INSERT",ts});
        return testeLaborator;
    }

    @Override
    public TesteLaborator delete(TesteLaborator testeLaborator) {
        testeLaboratorRepository.delete(testeLaborator);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "DELETE",ts});
        return testeLaborator;    
    }

    @Override
    public TesteLaborator update(TesteLaborator testeLaborator , String oldName) {
        TesteLaborator toBeUpdatedTestLaborator = testeLaboratorRepository.findByNumeTest(oldName);
        List<TestePacienti> correspondingTestePacientiForTestLaborator = testePacientiRepository.findBytesteLaborator(toBeUpdatedTestLaborator);
        toBeUpdatedTestLaborator.setTestePacientiLaborator(correspondingTestePacientiForTestLaborator);
        if(toBeUpdatedTestLaborator != null){
            TesteLaborator newUpdatedTest = new TesteLaborator(toBeUpdatedTestLaborator);
            newUpdatedTest.setAcuaratete(testeLaborator.getAcuaratete());
            newUpdatedTest.setValoareMaxima(testeLaborator.getValoareMaxima());
            newUpdatedTest.setNumeTest(testeLaborator.getNumeTest());
            newUpdatedTest.setValoareMinima(testeLaborator.getValoareMinima());
            testePacientiRepository.deleteAllBytesteLaborator(toBeUpdatedTestLaborator);
            List<TestePacienti> newTestePacienti = toBeUpdatedTestLaborator.getTestePacientiLaborator().stream()
                                                    .map(testePacienti -> {
                                                        TestePacienti newTests = new TestePacienti(testePacienti);
                                                        newTests.setPacienti(testePacienti.getPacienti());
                                                        newTests.setTesteLaborator(newUpdatedTest);
                                                        return newTests;
                                                    }).collect(Collectors.toList());
            testeLaboratorRepository.delete(toBeUpdatedTestLaborator);
            newUpdatedTest.setTestePacientiLaborator(newTestePacienti);
            testeLaboratorRepository.saveAndFlush(newUpdatedTest);
            testePacientiRepository.saveAll(newTestePacienti);
            String ts = LocalDateTime.now().toString();
            CsvService.writeNext(new String[]{TableName , "UPDATE",ts});
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
