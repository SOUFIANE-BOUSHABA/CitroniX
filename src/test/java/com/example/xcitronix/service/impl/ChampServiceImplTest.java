package com.example.xcitronix.service.impl;

import com.example.xcitronix.DTOs.ChampDTO;
import com.example.xcitronix.Entity.Champ;
import com.example.xcitronix.Entity.Ferme;
import com.example.xcitronix.VM.ChampVM;
import com.example.xcitronix.exciption.ChampException;
import com.example.xcitronix.exciption.SuperficierException;
import com.example.xcitronix.mapper.ChampMapper;
import com.example.xcitronix.repository.ChampRepository;
import com.example.xcitronix.repository.FermeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChampServiceImplTest {

    @InjectMocks
    private ChampServiceImpl champService;

    @Mock
    private ChampRepository champRepository;

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private ChampMapper champMapper;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addChampToFerme_shouldAdded_validInputs() {
        ChampDTO champDTO = new ChampDTO();
        champDTO.setFermeId(1L);
        champDTO.setSuperficie(1.0);

        Ferme ferme = new Ferme();
        ferme.setId(1L);
        ferme.setSuperficie(10);

        Champ champ = new Champ();
        ChampVM champVM = new ChampVM();

        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(champRepository.findByFermeId(1L)).thenReturn(new ArrayList<>());
        when(champMapper.toEntity(champDTO)).thenReturn(champ);
        when(champRepository.save(champ)).thenReturn(champ);
        when(champMapper.toViewModel(champ)).thenReturn(champVM);

        ChampVM result = champService.addChampToFerme(champDTO);

        assertNotNull(result);
        verify(champRepository, times(1)).save(champ);

    }

    @Test
    void addChampToFerme_shouldTrowException_whenChampSeperficierGrandofFermeSuperficier() {
        ChampDTO champDTO = new ChampDTO();
        champDTO.setFermeId(1L);
        champDTO.setSuperficie(11);

        Ferme ferme = new Ferme();
        ferme.setId(1L);
        ferme.setSuperficie(10);


        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(champRepository.findByFermeId(1L)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(SuperficierException.class , () -> {
            champService.addChampToFerme(champDTO);
        });

        assertEquals("Superficie totale des champs dépasse la superficie de la ferme.", exception.getMessage());

    }


    @Test
    void addChampToFerme_shouldTrowException_whenChampSeperficierGrandofHalfFermeSuperficier() {
        ChampDTO champDTO = new ChampDTO();
        champDTO.setFermeId(1L);
        champDTO.setSuperficie(6);

        Ferme ferme = new Ferme();
        ferme.setId(1L);
        ferme.setSuperficie(10);


        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(champRepository.findByFermeId(1L)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(SuperficierException.class , () -> {
            champService.addChampToFerme(champDTO);
        });

        assertEquals("Superficie du champ dépasse 50% de la superficie de la ferme.", exception.getMessage());

    }



    @Test
    void addChampToFerme_shouldTrowException_whenChampSeperficierGrandOf1000m() {
        ChampDTO champDTO = new ChampDTO();
        champDTO.setFermeId(1L);
        champDTO.setSuperficie(0.09);

        Ferme ferme = new Ferme();
        ferme.setId(1L);
        ferme.setSuperficie(10);


        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(champRepository.findByFermeId(1L)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(SuperficierException.class , () -> {
            champService.addChampToFerme(champDTO);
        });

        assertEquals(" ou min  champ est de 0.1 hectare (1 000 m)", exception.getMessage());

    }

    @Test
    void addChampToFerme_shouldTrowException_whenFermeAttender10champ() {

        ChampDTO champDTO = new ChampDTO();
        champDTO.setFermeId(1L);
        champDTO.setSuperficie(1.0);

        Ferme ferme = new Ferme();
        ferme.setId(1L);
        ferme.setSuperficie(10);

        Champ champ = new Champ();
        ChampVM champVM = new ChampVM();

        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(champRepository.findByFermeId(1L)).thenReturn(new ArrayList<>());
        when(champMapper.toEntity(champDTO)).thenReturn(champ);
        when(champRepository.save(champ)).thenReturn(champ);
        when(champMapper.toViewModel(champ)).thenReturn(champVM);
        when(champRepository.countByFermeId(1L)).thenReturn(10L);

        Exception exception = assertThrows(SuperficierException.class , () -> {
            champService.addChampToFerme(champDTO);
        });

        assertEquals("Le nombre maximum de champs est atteint.", exception.getMessage());

    }



    @Test
    void getChampById_shouldReturnChamp_whenValidId() {
        Champ champ = new Champ();
        ChampVM champVM = new ChampVM();

        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));
        when(champMapper.toViewModel(champ)).thenReturn(champVM);

        ChampVM result = champService.getChampById(1L);

        assertNotNull(result);
        verify(champRepository, times(1)).findById(1L);
    }


    @Test
    void getChampById_shouldReturnException_whenNotValidId() {
        Champ champ = new Champ();
        ChampVM champVM = new ChampVM();

        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));
        when(champMapper.toViewModel(champ)).thenReturn(champVM);

        Exception exception = assertThrows(ChampException.class , () -> {
            champService.getChampById(2L);
        });

        assertEquals("Champ introuvable.", exception.getMessage());
    }



    @Test
    void deletChamp_whenIdValid(){
        Champ  champ = new Champ();

        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));

        champService.deleteChamp(1L);

        verify(champRepository, times(1)).delete(champ);
    }



    @Test
    void deletChampException_whenIdNotValid(){
        Champ  champ = new Champ();

        when(champRepository.findById(1L)).thenReturn(Optional.of(champ));

        Exception exception = assertThrows(ChampException.class , () -> {
            champService.deleteChamp(2L);
        });

        assertEquals("Champ introuvable.", exception.getMessage());
    }











}