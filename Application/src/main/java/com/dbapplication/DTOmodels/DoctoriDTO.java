/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dbapplication.DTOmodels;

/**
 *
 * @author misustefan
 */

public class DoctoriDTO {
    
    private Long reteteCount;
    private Integer doctorCUI;

    public DoctoriDTO(Long reteteCount, Integer doctorCUI) {
        this.reteteCount = reteteCount;
        this.doctorCUI = doctorCUI;
    }

    public Long getReteteCount() {
        return reteteCount;
    }

    public Integer getDoctorCUI() {
        return doctorCUI;
    }
    
}
