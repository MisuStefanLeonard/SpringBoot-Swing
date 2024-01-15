/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dbapplication.models.VIEWModels.CabineteDoctoriVIEW;
import org.springframework.stereotype.Repository;

/**
 *
 * @author misustefan
 */
@Repository
public interface CabineteDoctoriViewRepository extends JpaRepository<CabineteDoctoriVIEW, Integer>{
    
}
