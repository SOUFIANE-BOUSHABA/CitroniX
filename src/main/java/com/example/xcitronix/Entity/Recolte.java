package com.example.xcitronix.Entity;


import com.example.xcitronix.Entity.Enum.Saison;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recolte")
public class Recolte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Saison saison;

    private LocalDate dateRecolte;

    private double quantiteTotale;


    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailRecolte> detailsRecolte;

    @OneToMany(mappedBy = "recolte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vente> ventes;
}
