/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.repository.ReadOnlyRepository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
/**
 *
 * @author misustefan
 */
@NoRepositoryBean
public interface ReadOnlyRepository<T , ID extends Serializable> extends Repository<T,ID>{
    List<T> findAll();
    
    T findBypacientCnp(String pacientCnp);
}
