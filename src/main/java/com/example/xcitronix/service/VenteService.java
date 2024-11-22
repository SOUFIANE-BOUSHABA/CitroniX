package com.example.xcitronix.service;

import com.example.xcitronix.DTOs.VenteDTO;
import com.example.xcitronix.VM.VenteVM;

import java.util.List;

public interface VenteService {

    VenteVM addVente(VenteDTO venteDTO);

    List<VenteVM> getAllVentes();

    VenteVM getVenteById(Long id);

    VenteVM updateVente(Long id, VenteDTO venteDTO);

    void deleteVente(Long id);
}
