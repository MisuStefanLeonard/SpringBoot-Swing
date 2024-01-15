/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.repository;

import com.dbapplication.models.CabineteDoctori;
import com.dbapplication.models.Doctori;
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
public interface CabineteDoctoriRepository extends JpaRepository<CabineteDoctori, Integer>{
    List<CabineteDoctori> findByDoctori(Doctori doctori);
    CabineteDoctori findBycabinetNrTelefon(String findBycabinetNrTelefon);
}
