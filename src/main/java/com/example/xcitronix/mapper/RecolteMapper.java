package com.example.xcitronix.mapper;

import com.example.xcitronix.DTOs.RecolteDTO;
import com.example.xcitronix.Entity.Recolte;
import com.example.xcitronix.VM.RecolteVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecolteMapper {


    Recolte toEntity(RecolteDTO dto);

    RecolteVM toVM(Recolte entity);
}
