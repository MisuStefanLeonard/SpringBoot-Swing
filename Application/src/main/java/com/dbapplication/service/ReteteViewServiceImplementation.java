/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.VIEWModels.ReteteVIEW;
import com.dbapplication.repository.ReteteViewRepository;
import org.springframework.stereotype.Service;
import com.dbapplication.service.ReteteViewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author misustefan
 */
@Service("ReteteViewService")
public class ReteteViewServiceImplementation implements ReteteViewService{

    private final ReteteViewRepository VIEWreteteRepository;
    
    @Autowired
    public ReteteViewServiceImplementation(ReteteViewRepository reteteRepositoryReadOnly) {
        this.VIEWreteteRepository = reteteRepositoryReadOnly;
    }
    
    @Override
    public List<ReteteVIEW> findAll() {
        return VIEWreteteRepository.findAll();
    }

    @Override
    public ReteteVIEW findByPacientCnp(String pacientCnp) {
        return VIEWreteteRepository.findBypacientCnp(pacientCnp);
    }

    @Override
    public ReteteVIEW save(ReteteVIEW retetaView) {
        return VIEWreteteRepository.save(retetaView);
    }

    @Override
    public List<ReteteVIEW> currentPacientsCNP(String input) {
        return VIEWreteteRepository.currentPacients(input);
    }
    

}
