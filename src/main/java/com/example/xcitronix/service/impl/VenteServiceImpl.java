package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.VenteDTO;
import com.example.xcitronix.Entity.Recolte;
import com.example.xcitronix.Entity.Vente;
import com.example.xcitronix.VM.VenteVM;
import com.example.xcitronix.exciption.RecolteException;
import com.example.xcitronix.exciption.VenteException;
import com.example.xcitronix.mapper.VenteMapper;
import com.example.xcitronix.repository.RecolteRepository;
import com.example.xcitronix.repository.VenteRepository;
import com.example.xcitronix.service.VenteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VenteServiceImpl  implements VenteService {

    private final VenteRepository venteRepository;
    private final RecolteRepository recolteRepository;
    private final VenteMapper venteMapper;

    public VenteServiceImpl(VenteRepository venteRepository , VenteMapper venteMapper , RecolteRepository recolteRepository) {
        this.venteRepository = venteRepository;
        this.venteMapper = venteMapper;
        this.recolteRepository = recolteRepository;
    }


    @Override
    public VenteVM addVente(VenteDTO venteDTO) {

        Recolte recolte = recolteRepository.findById(venteDTO.getRecolteId())
                .orElseThrow(() -> new RecolteException("Recolte non trouvée"));

        double totalVenteQuantite = venteRepository.findByRecolteId(venteDTO.getRecolteId())
                .stream()
                .mapToDouble(Vente::getQuantite)
                .sum();

        if (totalVenteQuantite + venteDTO.getQuantite() > recolte.getQuantiteTotale()) {
            throw new VenteException("La quantité totale des ventes dépasse la quantité de la récolte");
        }

        Vente vente = venteMapper.toEntity(venteDTO);
        double revenu =  venteDTO.getPrixUnitaire() * venteDTO.getQuantite();
        vente.setRevenu(revenu);
        vente.setRecolte(recolte);
        vente = venteRepository.save(vente);

        return venteMapper.toViewModel(vente);
    }

    @Override
    public List<VenteVM> getAllVentes() {
        return venteRepository.findAll().stream()
                .map(venteMapper::toViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public VenteVM getVenteById(Long id){
        return venteRepository.findById(id)
                .map(venteMapper::toViewModel)
                .orElseThrow(() -> new VenteException("Vente non trouvée"));
    }

    @Override
    public VenteVM updateVente(Long id , VenteDTO venteDTO){
        Vente vente = venteRepository.findById(id)
                .orElseThrow(()->new VenteException("Vente non trouvée"));

        Recolte recolte = recolteRepository.findById(venteDTO.getRecolteId())
                .orElseThrow(() -> new RecolteException("Recolte non trouvée"));


        double totalVenteQuantite = venteRepository.findByRecolteId(venteDTO.getRecolteId())
                .stream()
                .mapToDouble(Vente::getQuantite)
                .sum();

        if (totalVenteQuantite + venteDTO.getQuantite() > recolte.getQuantiteTotale()) {
            throw new VenteException("La quantité totale des ventes dépasse la quantité de la récolte");
        }

        vente.setQuantite(venteDTO.getQuantite());
        vente.setClient(venteDTO.getClient());
        vente.setDate(venteDTO.getDate());
        vente.setRecolte(recolte);
        vente.setPrixUnitaire(venteDTO.getPrixUnitaire());
        double revenu = venteDTO.getPrixUnitaire() * venteDTO.getQuantite();
        vente.setRevenu(revenu);

        vente = venteRepository.save(vente);

        return  venteMapper.toViewModel(vente);
    }

    @Override
    public void deleteVente(Long id){
        Vente vente = venteRepository.findById(id)
                .orElseThrow(()->new VenteException("Vente non trouvée"));
        venteRepository.deleteById(id);
    }






}
