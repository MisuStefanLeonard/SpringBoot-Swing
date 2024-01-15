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
    public List<TipDoctori> findAll();
    public TipDoctori save(TipDoctori tipDoctor);
    public TipDoctori delete(TipDoctori tipDoctor);
    public TipDoctori update(TipDoctori tipDoctor , String oldName);
    public TipDoctori findBynumeSpecializare(String numeSpecialiazare);
}
