package com.example.xcitronix.controller;

import com.example.xcitronix.DTOs.DetailRecolteDTO;
import com.example.xcitronix.VM.DetailRecolteVM;
import com.example.xcitronix.service.DetailRecolteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/details-recolte")
public class DetailRecolteController {

    private final DetailRecolteService detailRecolteService;

    public DetailRecolteController(DetailRecolteService detailRecolteService) {
        this.detailRecolteService = detailRecolteService;
    }

    @PostMapping
    public ResponseEntity<DetailRecolteVM> addDetailRecolte(@RequestBody DetailRecolteDTO detailRecolteDTO) {
        return ResponseEntity.ok(detailRecolteService.addDetailRecolte(detailRecolteDTO));
    }

    @GetMapping
    public ResponseEntity<List<DetailRecolteVM>> getAllDetailsRecolte() {
        return ResponseEntity.ok(detailRecolteService.getAllDetailsRecolte());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailRecolteVM> getDetailRecolteById(@PathVariable Long id) {
        return ResponseEntity.ok(detailRecolteService.getDetailRecolteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDetailRecolte(@PathVariable Long id) {
        detailRecolteService.deleteDetailRecolte(id);
        return ResponseEntity.noContent().build();
    }
}