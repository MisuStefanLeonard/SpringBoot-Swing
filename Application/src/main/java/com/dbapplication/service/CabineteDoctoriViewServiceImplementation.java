/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.service;

import com.dbapplication.models.VIEWModels.CabineteDoctoriVIEW;
import com.dbapplication.repository.CabineteDoctoriViewRepository;
import com.dbapplication.service.CabineteDoctoriViewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author misustefan
 */
@Service("CabineteDoctoriViewService")
@Transactional
public class CabineteDoctoriViewServiceImplementation implements CabineteDoctoriViewService{

    private final CabineteDoctoriViewRepository viewRepositoryForCabinete;
    
    @Autowired
    public CabineteDoctoriViewServiceImplementation(CabineteDoctoriViewRepository viewRepositoryForCabinete) {
        this.viewRepositoryForCabinete = viewRepositoryForCabinete;
      
    }
    
    @Override
    public CabineteDoctoriVIEW add(CabineteDoctoriVIEW cabDoctori) {
        return viewRepositoryForCabinete.save(cabDoctori);
    }

    @Override
    public void delete(CabineteDoctoriVIEW cabDoctori) {
       viewRepositoryForCabinete.delete(cabDoctori);
    }

    @Override
    public CabineteDoctoriVIEW update(CabineteDoctoriVIEW cabDoctori , Integer cabinetID) {
        CabineteDoctoriVIEW cabinetFromView = viewRepositoryForCabinete.findById(cabinetID).orElse(null);
        if(cabinetFromView != null){
            CabineteDoctoriVIEW updatedCabinet = new CabineteDoctoriVIEW(cabinetFromView);
            updatedCabinet.setCabinetNrStrada(cabDoctori.getCabinetNrStrada());
            updatedCabinet.setCabinetNume(cabDoctori.getCabinetNume());
            updatedCabinet.setCabinetOras(cabDoctori.getCabinetOras());
            updatedCabinet.setCodPostal(cabDoctori.getCodPostal());
            viewRepositoryForCabinete.save(updatedCabinet);
        }
        return cabDoctori;
    }

    @Override
    public List<CabineteDoctoriVIEW> findAll() {
        return viewRepositoryForCabinete.findAll();
    }

    @Override
    public CabineteDoctoriVIEW findById(Integer cabinetID) {
        return viewRepositoryForCabinete.findById(cabinetID).orElse(null);
    }
    
}
