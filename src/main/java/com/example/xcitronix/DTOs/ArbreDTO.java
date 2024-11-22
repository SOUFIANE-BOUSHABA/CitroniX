package com.example.xcitronix.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ArbreDTO {
    @NotNull
    @Past(message = "Date must be in the past")
    private LocalDate datePlantation;

    @NotNull
    private Long champId;

}
