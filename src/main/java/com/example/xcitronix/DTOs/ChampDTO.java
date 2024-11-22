package com.example.xcitronix.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ChampDTO {
    @NotNull
    private double superficie;
    @NotNull
    private Long fermeId;

}
