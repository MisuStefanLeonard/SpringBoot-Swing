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
     List<TestePacienti> findAll();
     TestePacienti save(TestePacienti testePacienti);
     TestePacienti remove(TestePacienti testePacienti);
     TestePacienti update(TestePacienti testePacienti , String CNP , String test_name , Integer ID_TEST);
     TestePacienti findById(Integer pacientiTestId);
     List<TestePacienti> findBypacienti(Pacienti pacienti);
     List<TestePacienti> findBytesteLaborator(TesteLaborator testeLaborator);
}
