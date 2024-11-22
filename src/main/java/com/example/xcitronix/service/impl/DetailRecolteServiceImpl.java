package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.DetailRecolteDTO;
import com.example.xcitronix.Entity.Arbre;
import com.example.xcitronix.Entity.DetailRecolte;
import com.example.xcitronix.Entity.Enum.Saison;
import com.example.xcitronix.Entity.Recolte;
import com.example.xcitronix.VM.DetailRecolteVM;
import com.example.xcitronix.exciption.RecolteException;
import com.example.xcitronix.mapper.DetailRecolteMapper;
import com.example.xcitronix.repository.ArbreRepository;
import com.example.xcitronix.repository.DetailRecolteRepository;
import com.example.xcitronix.repository.RecolteRepository;
import com.example.xcitronix.service.DetailRecolteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailRecolteServiceImpl implements DetailRecolteService {

    private final DetailRecolteRepository detailRecolteRepository;
    private final DetailRecolteMapper detailRecolteMapper;
    private final RecolteRepository recolteRepository;
    private final ArbreRepository arbreRepository;

    public DetailRecolteServiceImpl(DetailRecolteRepository detailRecolteRepository, DetailRecolteMapper detailRecolteMapper, RecolteRepository recolteRepository , ArbreRepository arbreRepository) {
        this.detailRecolteRepository = detailRecolteRepository;
        this.detailRecolteMapper = detailRecolteMapper;
        this.recolteRepository = recolteRepository;
        this.arbreRepository= arbreRepository;
    }

    @Override
    public DetailRecolteVM addDetailRecolte(DetailRecolteDTO detailRecolteDTO) {

        if (detailRecolteRepository.existsByArbreIdAndRecolteSaisonAndRecolteDateRecolte(detailRecolteDTO.getArbre().getId(), detailRecolteDTO.getRecolte().getSaison(), detailRecolteDTO.getRecolte().getDateRecolte())) {
            throw new RecolteException("Détail de récolte déjà existant");
        }
        Arbre arbre = arbreRepository.findById(detailRecolteDTO.getArbre().getId())
                .orElseThrow(() -> new RecolteException("Arbre non trouvé"));

        double productivite =arbre.getProductivite();

        Recolte recolte = recolteRepository.findById(detailRecolteDTO.getRecolte().getId())
                .orElseThrow(() -> new RecolteException("Récolte non trouvée"));
        recolte.setQuantiteTotale(recolte.getQuantiteTotale() + productivite);

        recolte.setDateRecolte( recolte.getDateRecolte());
        recolte.setSaison(recolte.getSaison());


        DetailRecolte detailRecolte = detailRecolteMapper.toEntity(detailRecolteDTO);
        detailRecolte.setQuantite(productivite);
        detailRecolte.setRecolte(recolte);

        recolte = recolteRepository.save(recolte);
        detailRecolte = detailRecolteRepository.save(detailRecolte);

        return detailRecolteMapper.toViewModel(detailRecolte);
    }

    @Override
    public List<DetailRecolteVM> getAllDetailsRecolte() {
        return detailRecolteRepository.findAll().stream()
                .map(detailRecolteMapper::toViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public DetailRecolteVM getDetailRecolteById(Long id) {
        return detailRecolteRepository.findById(id)
                .map(detailRecolteMapper::toViewModel)
                .orElseThrow(() -> new RecolteException("Détail de récolte non trouvé"));
    }

    @Override
    public void deleteDetailRecolte(Long id) {
        detailRecolteRepository.deleteById(id);
    }
}