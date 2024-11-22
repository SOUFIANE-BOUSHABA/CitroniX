package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.ArbreDTO;
import com.example.xcitronix.Entity.Arbre;
import com.example.xcitronix.Entity.Champ;
import com.example.xcitronix.VM.ArbreVM;
import com.example.xcitronix.exciption.ArbreException;
import com.example.xcitronix.exciption.ChampException;
import com.example.xcitronix.mapper.ArbreMapper;
import com.example.xcitronix.repository.ArbreRepository;
import com.example.xcitronix.repository.ChampRepository;
import com.example.xcitronix.service.ArbreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArbreServiceImpl implements ArbreService {

    private final ArbreRepository arbreRepository;
    private final ChampRepository champRepository;
    private final ArbreMapper arbreMapper;

    public ArbreServiceImpl(ArbreRepository arbreRepository, ChampRepository champRepository, ArbreMapper arbreMapper) {
        this.arbreRepository = arbreRepository;
        this.champRepository = champRepository;
        this.arbreMapper = arbreMapper;
    }

    @Override
    public ArbreVM plantArbre(ArbreDTO arbreDTO) {
        Champ champ = champRepository.findById(arbreDTO.getChampId())
                .orElseThrow(() -> new ChampException("Champ introuvable."));


        LocalDate datePlantation = arbreDTO.getDatePlantation();
        int monthValue = datePlantation.getMonthValue();
        if (monthValue < 3 || monthValue > 5) {
            throw new ArbreException("La date de plantation  doit  être entre mars et mai.");
        }

        long arbrecount = arbreRepository.countArbresByChampId(champ.getId());
        double maxArbres = champ.getSuperficie() * 100 ;

        if(arbrecount >= maxArbres){
            throw new ArbreException("Le nombre maximum d'arbres et attender.");
        }

        Arbre arbre = arbreMapper.toEntity(arbreDTO);
        arbre.setChamp(champ);
        arbre = arbreRepository.save(arbre);
        return arbreMapper.toViewModel(arbre);
    }

    @Override
    public ArbreVM getArbreById(Long id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ArbreException("Arbre introuvable."));
        return arbreMapper.toViewModel(arbre);
    }

    @Override
    public List<ArbreVM> getAllArbres() {
        return arbreRepository.findAll()
                .stream()
                .map(arbreMapper::toViewModel)
                .collect(Collectors.toList());

    }

    @Override
    public ArbreVM updateArbre(Long id, ArbreDTO arbreDTO) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ArbreException("Arbre introuvable."));

        arbre.setDatePlantation(arbreDTO.getDatePlantation());
        arbre = arbreRepository.save(arbre);
        return arbreMapper.toViewModel(arbre);
    }

    @Override
    public void deleteArbre(Long id) {

        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ArbreException("Arbre introuvable."));
        arbreRepository.delete(arbre);

    }


}
