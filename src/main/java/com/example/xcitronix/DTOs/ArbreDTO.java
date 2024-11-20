package com.example.xcitronix.DTOs;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ArbreDTO {
    private LocalDate datePlantation;
    private Long champId;

}
