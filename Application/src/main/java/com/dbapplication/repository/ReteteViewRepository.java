/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.repository;
import com.dbapplication.repository.ReadOnlyRepository.ReadOnlyRepository;
import com.dbapplication.models.VIEWModels.ReteteVIEW;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
/**
 *
 * @author misustefan
 */
public interface ReteteViewRepository extends JpaRepository<ReteteVIEW, String>{
    public ReteteVIEW findBypacientCnp(String pacientCnp);
    @Query(value = "SELECT * FROM retete_view rv WHERE rv.pacient_cnp LIKE ?1%", nativeQuery = true)
    public List<ReteteVIEW> currentPacients(String input);
}
