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
    public List<TesteLaborator> findAll();
    public TesteLaborator save(TesteLaborator testeLaborator);
    public TesteLaborator delete(TesteLaborator testeLaborator);
    public TesteLaborator update(TesteLaborator testeLaborator , String oldName);
    public TesteLaborator findById(Integer idTest);
    public TesteLaborator findByNumeTest(String NumeTest);
}
