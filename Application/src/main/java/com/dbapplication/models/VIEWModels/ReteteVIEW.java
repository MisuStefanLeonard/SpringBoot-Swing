/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.models.VIEWModels;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

/**
 *
 * @author misustefan
 */
@Entity
@Table(name = "retete_view")
@Getter
@Setter
@Immutable
public class ReteteVIEW {
    @Id
    private String pacientCnp;
    private Integer nrRetete;
}
