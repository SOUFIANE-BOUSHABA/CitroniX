package com.example.xcitronix.mapper;


import com.example.xcitronix.DTOs.ArbreDTO;
import com.example.xcitronix.Entity.Arbre;
import com.example.xcitronix.VM.ArbreVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArbreMapper {

    @Mapping(source = "champ.id", target = "champId")
    ArbreVM toViewModel(Arbre entity);

    @Mapping(source = "champId", target = "champ.id")
    Arbre toEntity(ArbreDTO dto);
}
