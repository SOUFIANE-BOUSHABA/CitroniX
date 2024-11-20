package com.example.xcitronix.repository;

import com.example.xcitronix.Entity.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArbreRepository extends JpaRepository<Arbre, Long> {
    long countArbresByChampId(Long champId);

}
