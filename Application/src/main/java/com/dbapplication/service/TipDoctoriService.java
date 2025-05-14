/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.TipDoctori;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface TipDoctoriService {
     List<TipDoctori> findAll();
     TipDoctori save(TipDoctori tipDoctor);
     TipDoctori delete(TipDoctori tipDoctor);
     TipDoctori update(TipDoctori tipDoctor , String oldName);
     TipDoctori findBynumeSpecializare(String numeSpecialiazare);
}
