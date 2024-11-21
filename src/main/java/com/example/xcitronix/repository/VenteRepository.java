package com.example.xcitronix.repository;

import com.example.xcitronix.Entity.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VenteRepository extends JpaRepository<Vente , Long> {

    List<Vente> findByRecolteId(Long id);

}
