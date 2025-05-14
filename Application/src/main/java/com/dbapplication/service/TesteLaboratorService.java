/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.TesteLaborator;
import java.util.List;

/**
 *
 * @author misustefan
 */

public interface TesteLaboratorService {
     List<TesteLaborator> findAll();
     TesteLaborator save(TesteLaborator testeLaborator);
     TesteLaborator delete(TesteLaborator testeLaborator);
     TesteLaborator update(TesteLaborator testeLaborator , String oldName);
     TesteLaborator findById(Integer idTest);
     TesteLaborator findByNumeTest(String NumeTest);
}
