package com.example.xcitronix.controller;


import com.example.xcitronix.DTOs.ArbreDTO;
import com.example.xcitronix.VM.ArbreVM;
import com.example.xcitronix.service.ArbreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arbres")
public class ArbreController {

    private final ArbreService arbreService;

    public ArbreController(ArbreService arbreService) {
        this.arbreService = arbreService;
    }

    @PostMapping
    public ResponseEntity<ArbreVM> plantArbre(@Valid @RequestBody ArbreDTO arbreDTO) {
        return ResponseEntity.ok(arbreService.plantArbre(arbreDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArbreVM> getArbreById(@PathVariable Long id) {
        return ResponseEntity.ok(arbreService.getArbreById(id));
    }

    @GetMapping
    public ResponseEntity<List<ArbreVM>> getAllArbres() {
        return ResponseEntity.ok(arbreService.getAllArbres());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArbreVM> updateArbre( @Valid @PathVariable Long id, @RequestBody ArbreDTO arbreDTO) {
        return ResponseEntity.ok(arbreService.updateArbre(id, arbreDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArbre(@PathVariable Long id) {
        arbreService.deleteArbre(id);
        return ResponseEntity.noContent().build();
    }
}
