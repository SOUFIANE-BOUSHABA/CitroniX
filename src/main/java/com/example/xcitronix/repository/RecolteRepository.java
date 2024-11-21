package com.example.xcitronix.repository;

import com.example.xcitronix.Entity.Recolte;
import com.example.xcitronix.Entity.Enum.Saison;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecolteRepository extends JpaRepository<Recolte, Long> {

}