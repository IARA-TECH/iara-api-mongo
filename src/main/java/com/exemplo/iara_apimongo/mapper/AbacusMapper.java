package com.exemplo.iara_apimongo.mapper;

import com.exemplo.iara_apimongo.dto.abacus.AbacusRequestDTO;
import com.exemplo.iara_apimongo.dto.abacus.AbacusResponseDTO;
import com.exemplo.iara_apimongo.model.Abacus;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AbacusMapper {

    AbacusMapper INSTANCE = Mappers.getMapper(AbacusMapper.class);

    Abacus toEntity(AbacusRequestDTO dto);

    AbacusResponseDTO toResponse(Abacus entity);

    void updateEntityFromDto(AbacusRequestDTO dto, @MappingTarget Abacus entity);
}
