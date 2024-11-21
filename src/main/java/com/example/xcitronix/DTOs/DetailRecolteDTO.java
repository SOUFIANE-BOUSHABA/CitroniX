package com.example.xcitronix.DTOs;

import com.example.xcitronix.Entity.Arbre;
import com.example.xcitronix.Entity.Recolte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailRecolteDTO {

    private double quantite;
    private Recolte recolte;
    private Arbre arbre;

}
