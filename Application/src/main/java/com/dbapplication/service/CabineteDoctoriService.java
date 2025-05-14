/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.CabineteDoctori;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface CabineteDoctoriService {
     List<CabineteDoctori> findAll();
     CabineteDoctori save(CabineteDoctori cabinetDoctori);
     CabineteDoctori delete(CabineteDoctori cabinetDoctori);
     CabineteDoctori update(CabineteDoctori cabineteDoctori , Integer cabinetID);
     CabineteDoctori findById(Integer ID );
     CabineteDoctori findByPhoneNumber(String phoneNumber);
}
