/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.dbapplication;

import com.dbapplication.models.CabineteDoctori;
import com.dbapplication.models.VIEWModels.CabineteDoctoriVIEW;
import com.dbapplication.repository.CabineteDoctoriViewRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author misustefan
 */
@SpringBootTest
public class viewTest {
    
    @Autowired
    private CabineteDoctoriViewRepository viewRepo;
    
    @Test
    public void updateThroughView(){
        // de implementat
        CabineteDoctoriVIEW cabinetFromView = viewRepo.findById(15).orElse(null);
        //CabineteDoctori cabinetThatHasBeenInsertedInParentTable = cabineteRepository.findById(cabinetID).orElse(null);
        if(cabinetFromView != null){
            CabineteDoctoriVIEW updatedCabinet = new CabineteDoctoriVIEW(cabinetFromView);
            updatedCabinet.setCabinetNrStrada(10);
            updatedCabinet.setCabinetNume("update");
            updatedCabinet.setCabinetOras("update");
            updatedCabinet.setCodPostal("142000");
            viewRepo.save(updatedCabinet);
        }
    
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
