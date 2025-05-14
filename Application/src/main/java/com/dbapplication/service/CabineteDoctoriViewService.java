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
     List<CabineteDoctoriVIEW> findAll();
     CabineteDoctoriVIEW add(CabineteDoctoriVIEW cabDoctori);
     void delete(CabineteDoctoriVIEW cabDoctori);
     CabineteDoctoriVIEW update(CabineteDoctoriVIEW cabDoctori , Integer cabinetID);
     CabineteDoctoriVIEW findById(Integer cabinetID);
}
