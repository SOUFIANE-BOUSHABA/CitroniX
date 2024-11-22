package com.example.xcitronix.repository;

import com.example.xcitronix.Entity.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long> {

    @Query("SELECT f FROM Ferme f WHERE (:nom IS NULL OR f.nom LIKE %:nom%) AND (:localisation IS NULL OR f.localisation LIKE %:localisation%) AND (:superficie IS NULL OR f.superficie = :superficie)")
    List<Ferme> findByCriteria(@Param("nom") String nom, @Param("localisation") String localisation, @Param("superficie") Double superficie);

    boolean existsByNom(String nom);
}