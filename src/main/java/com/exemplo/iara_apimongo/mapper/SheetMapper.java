package com.exemplo.iara_apimongo.mapper;

import com.exemplo.iara_apimongo.dto.sheet.SheetRequestDTO;
import com.exemplo.iara_apimongo.dto.sheet.SheetResponseDTO;
import com.exemplo.iara_apimongo.model.Sheet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SheetMapper {

    SheetMapper INSTANCE = Mappers.getMapper(SheetMapper.class);

    Sheet toEntity(SheetRequestDTO dto);

    SheetResponseDTO toResponse(Sheet entity);

    void updateEntityFromDto(SheetRequestDTO dto, @MappingTarget Sheet entity);
}
