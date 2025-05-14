/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.VIEWModels.ReteteVIEW;
import java.util.List;

/**
 *
 * @author misustefan
 */
public interface ReteteViewService {
    List<ReteteVIEW> findAll();
    ReteteVIEW findByPacientCnp(String pacientCnp);
     ReteteVIEW save(ReteteVIEW retetaView);
    List<ReteteVIEW> currentPacientsCNP(String input);
}
