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
    public List<CabineteDoctori> findAll();
    public CabineteDoctori save(CabineteDoctori cabinetDoctori);
    public CabineteDoctori delete(CabineteDoctori cabinetDoctori);
    public CabineteDoctori update(CabineteDoctori cabineteDoctori , Integer cabinetID);
    public CabineteDoctori findById(Integer ID );
    public CabineteDoctori findByPhoneNumber(String phoneNumber);
}
