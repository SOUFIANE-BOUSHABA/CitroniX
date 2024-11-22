package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.RecolteDTO;
import com.example.xcitronix.Entity.Arbre;
import com.example.xcitronix.Entity.Enum.Saison;
import com.example.xcitronix.Entity.Champ;
import com.example.xcitronix.Entity.DetailRecolte;
import com.example.xcitronix.Entity.Recolte;
import com.example.xcitronix.VM.RecolteVM;
import com.example.xcitronix.exciption.RecolteException;
import com.example.xcitronix.mapper.RecolteMapper;
import com.example.xcitronix.repository.ChampRepository;
import com.example.xcitronix.repository.DetailRecolteRepository;
import com.example.xcitronix.repository.RecolteRepository;
import com.example.xcitronix.service.RecolteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecolteServiceImpl implements RecolteService {

    private final RecolteRepository recolteRepository;
    private final ChampRepository champRepository;
    private final DetailRecolteRepository detailRecolteRepository;
    private final RecolteMapper recolteMapper;

    public RecolteServiceImpl(RecolteRepository recolteRepository, ChampRepository champRepository, DetailRecolteRepository detailRecolteRepository, RecolteMapper recolteMapper) {
        this.recolteRepository = recolteRepository;
        this.champRepository = champRepository;
        this.detailRecolteRepository = detailRecolteRepository;
        this.recolteMapper = recolteMapper;
    }

    @Override
    public RecolteVM addRecolte(Long champId, RecolteDTO recolteDTO) {
        Saison saison = getSaisonFromDate(recolteDTO.getDateRecolte());
        recolteDTO.setSaison(saison);

        if (champId == null) {
            if (recolteRepository.existsBySaisonAndDateRecolte(recolteDTO.getSaison(), recolteDTO.getDateRecolte())) {
                throw new RecolteException("Récolte déjà existante pour cette saison et date");
            }
            Recolte recolte = recolteMapper.toEntity(recolteDTO);
            recolte.setQuantiteTotale(0);
            recolte = recolteRepository.save(recolte);
            return recolteMapper.toVM(recolte);
        }

        Champ champ = champRepository.findById(champId)
                .orElseThrow(() -> new RecolteException("Champ non trouvé"));

        if (recolteRepository.existsBySaisonAndDateRecolte(recolteDTO.getSaison(), recolteDTO.getDateRecolte())) {
            throw new RecolteException("Récolte déjà existante pour cette saison et date");
        }

        Recolte recolte = recolteMapper.toEntity(recolteDTO);
        recolte.setQuantiteTotale(0);
        recolte = recolteRepository.save(recolte);

        double totalProductivite = 0;
        for (Arbre arbre : champ.getArbres()) {
            boolean existsDetailRecolteForArbreAndSeasonAndDate = detailRecolteRepository.existsByArbreIdAndRecolteSaisonAndRecolteDateRecolte(
                    arbre.getId(), recolteDTO.getSaison(), recolteDTO.getDateRecolte());
            if (existsDetailRecolteForArbreAndSeasonAndDate) {
                recolteRepository.delete(recolte);
                throw new RecolteException("L'arbre est déjà inclus dans une récolte pour cette saison et date");
            }

            double productivite = arbre.getProductivite();
            DetailRecolte detailRecolte = new DetailRecolte();
            detailRecolte.setQuantite(productivite);
            detailRecolte.setRecolte(recolte);
            detailRecolte.setArbre(arbre);
            detailRecolteRepository.save(detailRecolte);
            totalProductivite += productivite;
        }

        recolte.setQuantiteTotale(totalProductivite);
        recolte = recolteRepository.save(recolte);

        return recolteMapper.toVM(recolte);
    }



    public Saison getSaisonFromDate(LocalDate date) {
        int month = date.getMonthValue();
        return switch (month) {
            case 1, 2, 3 -> Saison.HIVER;
            case 4, 5, 6 -> Saison.PRINTEMPS;
            case 7, 8, 9 -> Saison.ETE;
            case 10, 11, 12 -> Saison.AUTOMNE;
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
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