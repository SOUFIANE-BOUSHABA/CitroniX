package com.example.xcitronix.controller;


import com.example.xcitronix.DTOs.FermeDTO;
import com.example.xcitronix.VM.FermeVM;
import com.example.xcitronix.service.FermeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fermes")
public class FermeController {

    private final FermeService fermeService;

    public FermeController(FermeService fermeService) {
        this.fermeService = fermeService;
    }

    @PostMapping
    public ResponseEntity<FermeVM> createFerme( @RequestBody FermeDTO dto) {
        return ResponseEntity.ok(fermeService.createFerme(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FermeVM> updateFerme(@PathVariable Long id, @RequestBody FermeDTO dto) {
        return ResponseEntity.ok(fermeService.updateFerme(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFerme(@PathVariable Long id) {
        fermeService.deleteFerme(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermeVM> getFermeById(@PathVariable Long id) {
        return ResponseEntity.ok(fermeService.getFermeById(id));
    }

    @GetMapping
    public ResponseEntity<List<FermeVM>> getAllFermes() {
        return ResponseEntity.ok(fermeService.getAllFermes());
    }


    @GetMapping("/search")
    public ResponseEntity<List<FermeVM>> searchFermes(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) String localisation,
            @RequestParam(required = false) Double superficie) {
        return ResponseEntity.ok(fermeService.searchFermes(nom, localisation, superficie));
    }
}