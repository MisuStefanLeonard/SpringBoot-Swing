/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.repository;

import com.dbapplication.models.Pacienti;
import com.dbapplication.models.TesteLaborator;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author misustefan
 */
@Repository
public interface TesteLaboratorRepository extends JpaRepository<TesteLaborator, Integer>{
    @Query("SELECT tl FROM TesteLaborator tl WHERE tl.numeTest = :name")
    TesteLaborator findByNumeTest(@Param("name") String name);
    
}
