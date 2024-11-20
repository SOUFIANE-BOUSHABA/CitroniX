package com.example.xcitronix.mapper;


import com.example.xcitronix.DTOs.FermeDTO;
import com.example.xcitronix.Entity.Ferme;
import com.example.xcitronix.VM.FermeVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FermeMapper {
    Ferme toEntity(FermeDTO dto);
    FermeVM toViewModel(Ferme entity);
}