package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.model.database.Abacus;
import com.exemplo.iara_apimongo.model.dto.request.AbacusRequest;
import com.exemplo.iara_apimongo.model.dto.response.AbacusResponse;
import com.exemplo.iara_apimongo.repository.AbacusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AbacusService extends BaseService<Abacus, String, AbacusRequest, AbacusResponse> {

    public AbacusService(AbacusRepository repository) {
        super(repository, "Abacus");
    }

    @Override
    protected Abacus toEntity(AbacusRequest dto) {
        return Abacus.builder()
                .factoryId(dto.getFactoryId())
                .name(dto.getName())
                .description(dto.getDescription())
                .lines(dto.getLines()) // agora é List<AbacusLine>
                .columns(dto.getColumns())
                .build();
    }

    @Override
    protected AbacusResponse toResponse(Abacus entity) {
        return AbacusResponse.builder()
                .id(entity.getId())
                .factoryId(entity.getFactoryId())
                .name(entity.getName())
                .description(entity.getDescription())
                .lines(entity.getLines())
                .columns(entity.getColumns())
                .build();
    }

    @Override
    protected void updateEntity(Abacus entity, AbacusRequest dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLines(dto.getLines());
        entity.setColumns(dto.getColumns());
    }
}
