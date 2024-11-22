package com.example.xcitronix.VM;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VenteVM {
    private Long id;
    private LocalDate date;
    private double prixUnitaire;
    private double quantite;
    private String client;
    private Long recolteId;
}



