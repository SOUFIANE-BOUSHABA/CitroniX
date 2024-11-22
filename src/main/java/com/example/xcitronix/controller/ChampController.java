package com.example.xcitronix.controller;

import com.example.xcitronix.DTOs.ChampDTO;
import com.example.xcitronix.VM.ChampVM;
import com.example.xcitronix.service.ChampService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/champs")
public class ChampController {

    private final ChampService champService;

    public ChampController(ChampService champService) {
        this.champService = champService;
    }

    @PostMapping
    public ResponseEntity<ChampVM> addChampToFerme(@Valid @RequestBody ChampDTO champDTO) {
        return ResponseEntity.ok(champService.addChampToFerme(champDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampVM> getChampById(@PathVariable Long id) {
        return ResponseEntity.ok(champService.getChampById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChampVM>> getAllChamps() {
        return ResponseEntity.ok(champService.getAllChamps());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampVM> updateChamp(@Valid @PathVariable Long id, @RequestBody ChampDTO champDTO) {
        return ResponseEntity.ok(champService.updateChamp(id, champDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChamp(@PathVariable Long id) {
        champService.deleteChamp(id);
        return ResponseEntity.noContent().build();
    }
}