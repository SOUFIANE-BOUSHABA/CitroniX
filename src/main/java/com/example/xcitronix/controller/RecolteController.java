package com.example.xcitronix.controller;

import com.example.xcitronix.DTOs.RecolteDTO;
import com.example.xcitronix.VM.RecolteVM;
import com.example.xcitronix.service.RecolteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recoltes")
public class RecolteController {

    private final RecolteService recolteService;

    public RecolteController(RecolteService recolteService) {
        this.recolteService = recolteService;
    }

    @PostMapping
    public ResponseEntity<RecolteVM> addRecolte(@RequestParam(required = false) Long champId, @RequestBody RecolteDTO recolteDTO) {
        return ResponseEntity.ok(recolteService.addRecolte(champId, recolteDTO));
    }

    @GetMapping
    public ResponseEntity<List<RecolteVM>> getAllRecoltes() {
        return ResponseEntity.ok(recolteService.getAllRecoltes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecolteVM> getRecolteById(@PathVariable Long id) {
        return ResponseEntity.ok(recolteService.getRecolteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecolte(@PathVariable Long id) {
        recolteService.deleteRecolte(id);
        return ResponseEntity.noContent().build();
    }
}