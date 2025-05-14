/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.repository;

import com.dbapplication.models.Doctori;
import com.dbapplication.models.TipDoctori;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author misustefan
 */
@Repository
public interface DoctoriRepository extends JpaRepository<Doctori, Integer>{ 
     List<Doctori> findBytipDoctor(TipDoctori tipDoctor);
}
