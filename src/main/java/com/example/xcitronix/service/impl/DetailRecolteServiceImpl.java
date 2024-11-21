package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.DetailRecolteDTO;
import com.example.xcitronix.Entity.DetailRecolte;
import com.example.xcitronix.VM.DetailRecolteVM;
import com.example.xcitronix.exciption.RecolteException;
import com.example.xcitronix.mapper.DetailRecolteMapper;
import com.example.xcitronix.repository.DetailRecolteRepository;
import com.example.xcitronix.service.DetailRecolteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailRecolteServiceImpl implements DetailRecolteService {

    private final DetailRecolteRepository detailRecolteRepository;
    private final DetailRecolteMapper detailRecolteMapper;

    public DetailRecolteServiceImpl(DetailRecolteRepository detailRecolteRepository, DetailRecolteMapper detailRecolteMapper) {
        this.detailRecolteRepository = detailRecolteRepository;
        this.detailRecolteMapper = detailRecolteMapper;
    }

    @Override
    public DetailRecolteVM addDetailRecolte(DetailRecolteDTO detailRecolteDTO) {

        if (detailRecolteRepository.existsByArbreIdAndRecolteSaison(detailRecolteDTO.getArbre().getId() , detailRecolteDTO.getRecolte().getSaison())) {
            throw new RecolteException("Détail de récolte déjà existant");
        }

        DetailRecolte detailRecolte = detailRecolteMapper.toEntity(detailRecolteDTO);
        return detailRecolteMapper.toViewModel(detailRecolteRepository.save(detailRecolte));
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