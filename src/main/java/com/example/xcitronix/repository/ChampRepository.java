package com.example.xcitronix.repository;

import com.example.xcitronix.Entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long> {

    List<Champ> findByFermeId(Long fermeId);
}
