package com.example.xcitronix.service;

import com.example.xcitronix.DTOs.ChampDTO;
import com.example.xcitronix.VM.ChampVM;

import java.util.List;

public interface ChampService {
    ChampVM addChampToFerme(ChampDTO champDTO);
    ChampVM getChampById(Long id);
    List<ChampVM> getAllChamps();
    ChampVM updateChamp(Long id, ChampDTO champDTO);
    void deleteChamp(Long id);
}