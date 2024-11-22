package com.example.xcitronix.repository;

import com.example.xcitronix.Entity.DetailRecolte;
import com.example.xcitronix.Entity.Enum.Saison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DetailRecolteRepository extends JpaRepository<DetailRecolte, Long> {

    boolean existsByArbreIdAndRecolteSaisonAndRecolteDateRecolte(Long arbreId, Saison saison, LocalDate dateRecolte);
}
