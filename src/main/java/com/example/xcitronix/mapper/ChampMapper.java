package com.example.xcitronix.mapper;

import com.example.xcitronix.DTOs.ChampDTO;
import com.example.xcitronix.Entity.Champ;
import com.example.xcitronix.VM.ChampVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChampMapper {

    @Mapping(source = "ferme.id", target = "fermeId")
    ChampVM toViewModel(Champ entity);

    @Mapping(source = "fermeId", target = "ferme.id")
    Champ toEntity(ChampDTO dto);
}
