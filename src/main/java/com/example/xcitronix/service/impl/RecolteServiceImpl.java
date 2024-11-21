package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.RecolteDTO;
import com.example.xcitronix.Entity.Recolte;
import com.example.xcitronix.VM.RecolteVM;
import com.example.xcitronix.exciption.RecolteException;
import com.example.xcitronix.mapper.RecolteMapper;
import com.example.xcitronix.repository.RecolteRepository;
import com.example.xcitronix.service.RecolteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecolteServiceImpl implements RecolteService {

    private final RecolteRepository recolteRepository;
    private final RecolteMapper recolteMapper;

    public RecolteServiceImpl(RecolteRepository recolteRepository, RecolteMapper recolteMapper) {
        this.recolteRepository = recolteRepository;
        this.recolteMapper = recolteMapper;
    }

    @Override
    public RecolteVM addRecolte(RecolteDTO recolteDTO) {
        Recolte recolte = recolteMapper.toEntity(recolteDTO);
        return recolteMapper.toVM(recolteRepository.save(recolte));
    }


    @Override
    public List<RecolteVM> getAllRecoltes() {
        return recolteRepository.findAll().stream()
                .map(recolteMapper::toVM)
                .collect(Collectors.toList());
    }

    @Override
    public RecolteVM getRecolteById(Long id) {
        return recolteRepository.findById(id)
                .map(recolteMapper::toVM)
                .orElseThrow(() -> new RecolteException("Récolte non trouvée"));
    }

    @Override
    public void deleteRecolte(Long id) {
        recolteRepository.deleteById(id);
    }
}