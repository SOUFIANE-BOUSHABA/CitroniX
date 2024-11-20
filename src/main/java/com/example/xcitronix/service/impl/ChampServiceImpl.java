package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.ChampDTO;
import com.example.xcitronix.Entity.Champ;
import com.example.xcitronix.Entity.Ferme;
import com.example.xcitronix.VM.ChampVM;
import com.example.xcitronix.exciption.SuperficierException;
import com.example.xcitronix.mapper.ChampMapper;
import com.example.xcitronix.repository.ChampRepository;
import com.example.xcitronix.repository.FermeRepository;
import com.example.xcitronix.service.ChampService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampServiceImpl implements ChampService {

    private final ChampRepository champRepository;
    private final FermeRepository fermeRepository;
    private final ChampMapper champMapper;

    public ChampServiceImpl(ChampRepository champRepository, FermeRepository fermeRepository, ChampMapper champMapper) {
        this.champRepository = champRepository;
        this.fermeRepository = fermeRepository;
        this.champMapper = champMapper;
    }

    @Override
    public ChampVM addChampToFerme(ChampDTO champDTO) {
        Ferme ferme = fermeRepository.findById(champDTO.getFermeId())
                .orElseThrow(() -> new RuntimeException("Ferme introuvable."));

        double totalSuperf = champRepository.findByFermeId(champDTO.getFermeId())
                .stream()
                .mapToDouble(Champ::getSuperficie)
                .sum();

        if(totalSuperf + champDTO.getSuperficie() > ferme.getSuperficie()){
            throw new SuperficierException("Superficie totale des champs dépasse la superficie de la ferme.");
        }

        Champ champ = champMapper.toEntity(champDTO);
        champ.setFerme(ferme);
        champ = champRepository.save(champ);
        return champMapper.toViewModel(champ);
    }

    @Override
    public ChampVM getChampById(Long id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champ introuvable."));
        return champMapper.toViewModel(champ);
    }

    @Override
    public List<ChampVM> getAllChamps() {
        return champRepository.findAll()
                .stream()
                .map(champMapper::toViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public ChampVM updateChamp(Long id, ChampDTO champDTO) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champ introuvable."));
        champ.setSuperficie(champDTO.getSuperficie());
        champ = champRepository.save(champ);
        return champMapper.toViewModel(champ);
    }

    @Override
    public void deleteChamp(Long id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champ introuvable."));
        champRepository.delete(champ);
    }
}