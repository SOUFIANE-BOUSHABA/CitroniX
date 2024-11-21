package com.example.xcitronix.mapper;


import com.example.xcitronix.DTOs.VenteDTO;
import com.example.xcitronix.Entity.Vente;
import com.example.xcitronix.VM.VenteVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VenteMapper {
    VenteVM  toViewModel(Vente entity);
    Vente toEntity(VenteDTO dto);

}
