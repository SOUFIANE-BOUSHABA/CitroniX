package com.example.xcitronix.service;

import com.example.xcitronix.DTOs.DetailRecolteDTO;
import com.example.xcitronix.VM.DetailRecolteVM;

import java.util.List;

public interface DetailRecolteService {
    DetailRecolteVM addDetailRecolte(DetailRecolteDTO detailRecolteDTO);

    List<DetailRecolteVM> getAllDetailsRecolte();

    DetailRecolteVM getDetailRecolteById(Long id);

    void deleteDetailRecolte(Long id);
}