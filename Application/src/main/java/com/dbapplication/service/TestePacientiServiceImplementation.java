/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Pacienti;
import com.dbapplication.models.TesteLaborator;
import com.dbapplication.models.TestePacienti;
import com.dbapplication.repository.PacientiRepository;
import com.dbapplication.repository.TesteLaboratorRepository;
import com.dbapplication.repository.TestePacientiRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
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
@Service("TestePacientiService")
@Transactional
public class TestePacientiServiceImplementation implements TestePacientiService{

    private final TestePacientiRepository testePacientiRepository;
    private final PacientiRepository pacientiRepository;
    private final TesteLaboratorRepository testeLaboratorRepository;
    private final String TableName = "teste_pacienti";
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
    public TestePacientiServiceImplementation(TestePacientiRepository testePacientiRepository, PacientiRepository pacientiRepository, TesteLaboratorRepository testeLaboratorRepository) {
        this.testePacientiRepository = testePacientiRepository;
        this.pacientiRepository = pacientiRepository;
        this.testeLaboratorRepository = testeLaboratorRepository;
    }
    
    @Override
    public List<TestePacienti> findAll() {
        return testePacientiRepository.findAll();
    }

    @Override
    public TestePacienti save(TestePacienti testePacienti) {
        testePacientiRepository.save(testePacienti);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "INSERT",ts});
        return testePacienti;
    }

    @Override
    public TestePacienti remove(TestePacienti testePacienti) {
        testePacientiRepository.delete(testePacienti);
        String ts = LocalDateTime.now().toString();
        CsvService.writeNext(new String[]{TableName , "DELETE",ts});
        return testePacienti;
    }

    @Override
    public TestePacienti update(TestePacienti testePacienti,String CNP , String test_Name, Integer ID_TEST) {
        TestePacienti toBeUpdatedTestePacienti  = testePacientiRepository.findById(ID_TEST).orElse(null);
        Pacienti toBeSetToTestePacienti = pacientiRepository.findById(CNP).orElse(null);
        TesteLaborator toBeSetToTestePacientiTestLaborator = testeLaboratorRepository.findByNumeTest(test_Name);
        if(toBeUpdatedTestePacienti != null){
            TestePacienti newUpdatedTestePacienti = new TestePacienti(toBeUpdatedTestePacienti);
            newUpdatedTestePacienti.setDataEmitere(testePacienti.getDataEmitere());
            newUpdatedTestePacienti.setRezultate(testePacienti.getRezultate());
            newUpdatedTestePacienti.setValoareTest(testePacienti.getValoareTest());
            newUpdatedTestePacienti.setPacienti(toBeSetToTestePacienti);
            newUpdatedTestePacienti.setTesteLaborator(toBeSetToTestePacientiTestLaborator);
            testePacientiRepository.save(newUpdatedTestePacienti);
            String ts = LocalDateTime.now().toString();
            CsvService.writeNext(new String[]{TableName , "UPDATE",ts});
        }
        return testePacienti;
    }

    @Override
    public TestePacienti findById(Integer pacientiTestId) {
        return testePacientiRepository.findById(pacientiTestId).orElse(null);
    }

    @Override
    public List<TestePacienti> findBypacienti(Pacienti pacienti) {
        return testePacientiRepository.findBypacienti(pacienti);
    }

    @Override
    public List<TestePacienti> findBytesteLaborator(TesteLaborator testeLaborator) {
        return testePacientiRepository.findBytesteLaborator(testeLaborator);
    }
}
