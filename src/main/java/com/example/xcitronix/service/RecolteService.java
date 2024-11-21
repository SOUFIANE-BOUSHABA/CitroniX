package com.example.xcitronix.service;

import com.example.xcitronix.DTOs.RecolteDTO;
import com.example.xcitronix.VM.RecolteVM;

import java.util.List;

public interface RecolteService {
    RecolteVM addRecolte(RecolteDTO recolteDTO);

    List<RecolteVM> getAllRecoltes();

    RecolteVM getRecolteById(Long id);

    void deleteRecolte(Long id);
}