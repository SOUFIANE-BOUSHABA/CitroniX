package com.example.xcitronix.repository;

import com.example.xcitronix.Entity.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long> {
}
