package com.example.xcitronix.Entity;


import com.example.xcitronix.exciption.ArbreException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "arbre")
public class Arbre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate datePlantation;


    @ManyToOne
    @JoinColumn(name = "champ_id", nullable = false)
    private Champ champ;

    public int getAge() {
        if (datePlantation != null) {
            return Period.between(datePlantation, LocalDate.now()).getYears();
        }
        return 0;
    }

    public double getProductivite() {
        int age = getAge();
        if (age > 20) {
            throw new ArbreException("L'arbre > l age 20 ans.");
        }
        if (age < 3) {
            return 2.5;
        } else if (age >= 3 && age <= 10) {
            return 12;
        } else {
            return 20;
        }
    }
}
