package com.example.xcitronix.controller;

import com.example.xcitronix.DTOs.VenteDTO;
import com.example.xcitronix.VM.VenteVM;
import com.example.xcitronix.service.VenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventes")
public class VenteController {

    private final VenteService venteService;

    @Autowired
    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @PostMapping
    public ResponseEntity<VenteVM> addVente(@Valid @RequestBody VenteDTO venteDTO) {
        VenteVM venteVM = venteService.addVente(venteDTO);
        return ResponseEntity.ok(venteVM);
    }

    @GetMapping
    public ResponseEntity<List<VenteVM>> getAllVentes() {
        List<VenteVM> ventes = venteService.getAllVentes();
        return ResponseEntity.ok(ventes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenteVM> getVenteById(@PathVariable Long id) {
        VenteVM venteVM = venteService.getVenteById(id);
        return ResponseEntity.ok(venteVM);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenteVM> updateVente(@PathVariable Long id, @RequestBody VenteDTO venteDTO) {
        VenteVM updatedVente = venteService.updateVente(id, venteDTO);
        return ResponseEntity.ok(updatedVente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
        return ResponseEntity.noContent().build();
    }
}