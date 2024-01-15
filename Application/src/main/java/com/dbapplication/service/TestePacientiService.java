/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.Pacienti;
import com.dbapplication.models.TesteLaborator;
import com.dbapplication.models.TestePacienti;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface TestePacientiService {
    public List<TestePacienti> findAll();
    public TestePacienti save(TestePacienti testePacienti);
    public TestePacienti remove(TestePacienti testePacienti);
    public TestePacienti update(TestePacienti testePacienti , String CNP , String test_name , Integer ID_TEST);
    public TestePacienti findById(Integer pacientiTestId);
    public List<TestePacienti> findBypacienti(Pacienti pacienti);
    public List<TestePacienti> findBytesteLaborator(TesteLaborator testeLaborator);
}
