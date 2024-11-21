package com.example.xcitronix.mapper;

import com.example.xcitronix.DTOs.DetailRecolteDTO;
import com.example.xcitronix.Entity.DetailRecolte;
import com.example.xcitronix.VM.DetailRecolteVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailRecolteMapper {

    DetailRecolteVM toViewModel(DetailRecolte entity);

    DetailRecolte toEntity(DetailRecolteDTO dto);
}