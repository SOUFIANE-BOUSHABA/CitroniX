package com.example.xcitronix.repository;

import com.example.xcitronix.Entity.DetailRecolte;
import com.example.xcitronix.Entity.Enum.Saison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRecolteRepository extends JpaRepository<DetailRecolte, Long> {

    boolean existsByArbreIdAndRecolteSaison(Long arbreId, Saison saison);
}
