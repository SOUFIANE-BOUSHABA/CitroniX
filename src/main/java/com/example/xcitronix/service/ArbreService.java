package com.example.xcitronix.service;

import com.example.xcitronix.DTOs.ArbreDTO;
import com.example.xcitronix.VM.ArbreVM;

import java.util.List;

public interface ArbreService {

    ArbreVM plantArbre(ArbreDTO arbreDTO);
    ArbreVM getArbreById(Long id);
    List<ArbreVM> getAllArbres();
    ArbreVM updateArbre(Long id, ArbreDTO arbreDTO);
    void deleteArbre(Long id);


}
