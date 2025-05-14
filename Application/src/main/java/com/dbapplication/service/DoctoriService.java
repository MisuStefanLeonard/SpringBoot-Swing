/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.DTOmodels.DoctoriDTO;
import com.dbapplication.models.Doctori;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author misustefan
 */
public interface DoctoriService {
    
     List<Doctori> findAll();
     Doctori save(Doctori doctori);
     void delete(Doctori doctori);
     Doctori update(Doctori doctori , Integer oldCUI, String specializationName);
     Doctori findById(Integer doctorCUI);
     List<DoctoriDTO> getEveryDoctorsReteteLessThanACount(Integer reteteCount);
}
