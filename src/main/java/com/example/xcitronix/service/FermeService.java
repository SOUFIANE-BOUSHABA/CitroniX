package com.example.xcitronix.service;


import com.example.xcitronix.DTOs.FermeDTO;
import com.example.xcitronix.VM.FermeVM;
import java.util.List;

public interface FermeService {
    FermeVM createFerme(FermeDTO dto);
    FermeVM updateFerme(Long id, FermeDTO dto);
    void deleteFerme(Long id);
    FermeVM getFermeById(Long id);
    List<FermeVM> getAllFermes();
    List<FermeVM> searchFermes(String nom, String localisation, Double superficie);

}