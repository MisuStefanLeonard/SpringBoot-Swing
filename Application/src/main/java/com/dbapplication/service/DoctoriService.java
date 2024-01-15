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
    
    public List<Doctori> findAll();
    public Doctori save(Doctori doctori);
    public void delete(Doctori doctori);
    public Doctori update(Doctori doctori , Integer oldCUI, String specializationName);
    public Doctori findById(Integer doctorCUI);
    public List<DoctoriDTO> getEveryDoctorsReteteLessThanACount(Integer reteteCount);
}
