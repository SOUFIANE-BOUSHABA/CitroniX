package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.FermeDTO;
import com.example.xcitronix.Entity.Ferme;
import com.example.xcitronix.VM.FermeVM;
import com.example.xcitronix.mapper.FermeMapper;
import com.example.xcitronix.repository.FermeRepository;
import com.example.xcitronix.service.FermeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FermeServiceImpl implements FermeService {

    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;

    public FermeServiceImpl(FermeRepository fermeRepository, FermeMapper fermeMapper) {
        this.fermeRepository = fermeRepository;
        this.fermeMapper = fermeMapper;
    }

    @Override
    public FermeVM createFerme(FermeDTO dto) {
        boolean uniqueNom = fermeRepository.existsByNom(dto.getNom());
        if (uniqueNom) {
            throw new RuntimeException("Nom de ferme déjà existant.");
        }
        Ferme ferme = fermeMapper.toEntity(dto);
        ferme = fermeRepository.save(ferme);
        return fermeMapper.toViewModel(ferme);
    }

    @Override
    public FermeVM updateFerme(Long id, FermeDTO dto) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Ferme introuvable."));
        ferme.setNom(dto.getNom());
        ferme.setLocalisation(dto.getLocalisation());
        ferme.setSuperficie(dto.getSuperficie());
        ferme.setDateCreation(dto.getDateCreation());
        ferme = fermeRepository.save(ferme);
        return fermeMapper.toViewModel(ferme);
    }

    @Override
    public void deleteFerme(Long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Ferme introuvable."));
        fermeRepository.delete(ferme);
    }

    @Override
    public FermeVM getFermeById(Long id) {
        Ferme ferme = fermeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Ferme introuvable."));
        return fermeMapper.toViewModel(ferme);
    }

    @Override
    public List<FermeVM> getAllFermes() {
        return fermeRepository.findAll()
                .stream()
                .map(fermeMapper::toViewModel)
                .collect(Collectors.toList());
    }


    @Override
    public List<FermeVM> searchFermes(String nom, String localisation, Double superficie) {
        return fermeRepository.findByCriteria(nom, localisation, superficie)
                .stream()
                .map(fermeMapper::toViewModel)
                .collect(Collectors.toList());
    }
}