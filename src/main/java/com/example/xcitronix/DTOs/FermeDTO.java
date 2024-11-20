package com.example.xcitronix.DTOs;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FermeDTO {
    private String nom;
    private String localisation;
    private double superficie;
    private LocalDate dateCreation;
}