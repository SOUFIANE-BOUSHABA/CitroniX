package com.example.xcitronix.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenteDTO {
    private Long id;
    private LocalDate date;
    private double prixUnitaire;

    @Min(value = 1, message = " at least 1")
    private double quantite;
    private String client;
    private Long recolteId;
}