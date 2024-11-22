package com.example.xcitronix.DTOs;


import com.example.xcitronix.Entity.Champ;
import com.example.xcitronix.Entity.Enum.Saison;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecolteDTO {
    private Saison saison;
    private LocalDate dateRecolte;
    private double quantiteTotale;
}
