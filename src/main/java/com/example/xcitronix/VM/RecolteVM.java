package com.example.xcitronix.VM;


import com.example.xcitronix.Entity.Enum.Saison;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecolteVM {

    private Long id;
    private Saison saison;
    private LocalDate dateRecolte;
    private double quantiteTotale;
}
