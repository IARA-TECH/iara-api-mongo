package com.exemplo.iara_apimongo.mapper;

import com.exemplo.iara_apimongo.dto.abacusPhoto.AbacusPhotoRequestDTO;
import com.exemplo.iara_apimongo.dto.abacusPhoto.AbacusPhotoResponseDTO;
import com.exemplo.iara_apimongo.model.AbacusPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AbacusPhotoMapper {

    AbacusPhotoMapper INSTANCE = Mappers.getMapper(AbacusPhotoMapper.class);

    AbacusPhoto toEntity(AbacusPhotoRequestDTO dto);

    AbacusPhotoResponseDTO toResponse(AbacusPhoto entity);

    void updateEntityFromDto(AbacusPhotoRequestDTO dto, @MappingTarget AbacusPhoto entity);
}
