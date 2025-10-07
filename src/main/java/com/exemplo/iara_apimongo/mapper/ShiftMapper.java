package com.exemplo.iara_apimongo.mapper;

import com.exemplo.iara_apimongo.dto.shift.ShiftRequestDTO;
import com.exemplo.iara_apimongo.dto.shift.ShiftResponseDTO;
import com.exemplo.iara_apimongo.model.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ShiftMapper {

    ShiftMapper INSTANCE = Mappers.getMapper(ShiftMapper.class);

    Shift toEntity(ShiftRequestDTO dto);

    ShiftResponseDTO toResponse(Shift entity);

    void updateEntityFromDto(ShiftRequestDTO dto, @MappingTarget Shift entity);
}
