/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dbapplication.repository;

import com.dbapplication.models.Pacienti;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author misustefan
 */
@Repository
public interface PacientiRepository extends JpaRepository<Pacienti, String>{
    /* 
    SELECT *
    FROM pacienti p join retete r on p.pacient_cnp = r.pacient_cnp
                    join retete_cu_medicamentele rm on r.reteta_id = rm.reteta_id
    WHERE medicament_id = (SELECT medicament_id
			   FROM medicamente
                           WHERE medicament_nume = [name]);
    */
    
                                                                     
}
