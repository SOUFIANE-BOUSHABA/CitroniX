package com.example.xcitronix.VM;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FermeVM {
    private Long id;
    private String nom;
    private String localisation;
    private double superficie;
    private LocalDate dateCreation;
}