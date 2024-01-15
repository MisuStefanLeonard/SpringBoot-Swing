/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.VIEWModels.CabineteDoctoriVIEW;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface CabineteDoctoriViewService {
    public List<CabineteDoctoriVIEW> findAll();
    public CabineteDoctoriVIEW add(CabineteDoctoriVIEW cabDoctori);
    public void delete(CabineteDoctoriVIEW cabDoctori);
    public CabineteDoctoriVIEW update(CabineteDoctoriVIEW cabDoctori , Integer cabinetID);
    public CabineteDoctoriVIEW findById(Integer cabinetID);
}
