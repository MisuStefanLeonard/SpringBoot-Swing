/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.repository;

import com.dbapplication.models.TipDoctori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author misustefan
 */
@Repository
public interface TipDoctoriRepository extends JpaRepository<TipDoctori, Integer>{
    TipDoctori findBynumeSpecializare(String numeSpecialiazare);
}
