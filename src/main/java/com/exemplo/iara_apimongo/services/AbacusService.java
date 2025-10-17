package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.dto.abacusDTOs.AbacusRequestDTO;
import com.exemplo.iara_apimongo.dto.abacusDTOs.AbacusResponseDTO;
import com.exemplo.iara_apimongo.model.Abacus;
import com.exemplo.iara_apimongo.repository.AbacusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AbacusService extends BaseService<Abacus, String, AbacusRequestDTO, AbacusResponseDTO> {

    public AbacusService(AbacusRepository repository) {
        super(repository, "Abacus");
    }

    @Override
    protected Abacus toEntity(AbacusRequestDTO dto) {
        return Abacus.builder()
                .factoryId(dto.getFactoryId())
                .name(dto.getName())
                .description(dto.getDescription())
                .lines(dto.getLines())
                .columns(dto.getColumns())
                .build();
    }

    @Override
    protected AbacusResponseDTO toResponse(Abacus entity) {
        return AbacusResponseDTO.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .name(entity.getName())
                .description(entity.getDescription())
                .lines(entity.getLines())
                .columns(entity.getColumns())
                .build();
    }

    @Override
    protected void updateEntity(Abacus entity, AbacusRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLines(dto.getLines());
        entity.setColumns(dto.getColumns());
    }
}
