package com.example.xcitronix.DTOs;

import java.time.LocalDate;
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
    private double quantite;
    private double revenu;
    private String client;
    private Long recolteId;
}