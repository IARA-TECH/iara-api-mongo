package com.exemplo.iara_apimongo.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<E, ID, Req, Res> {

    protected final MongoRepository<E, ID> repository;
    private final String entityName;

    protected abstract E toEntity(Req request);
    protected abstract Res toResponse(E entity);

    @Transactional(readOnly = true)
    public List<Res> findAll() {
        log.info("Finding all {}", entityName);
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Res findById(ID id) {
        log.info("Finding {} by id {}", entityName, id);
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(entityName + " not found with id: " + id));
        return toResponse(entity);
    }

    @Transactional
    public Res create(Req request) {
        log.info("Creating new {}", entityName);
        E entity = toEntity(request);
        E saved = repository.save(entity);
        return toResponse(saved);
    }

    @Transactional
    public Res update(ID id, Req request) {
        log.info("Updating {} with id {}", entityName, id);
        E entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(entityName + " not found with id: " + id));
        try {
            updateEntity(entity, request);
        } catch (Exception e) {
            return null;
        }        E saved = repository.save(entity);
        return toResponse(saved);
    }

    @Transactional
    public void delete(ID id) {
        log.info("Deleting {} with id {}", entityName, id);
        if (!repository.existsById(id)) {
            throw new RuntimeException(entityName + " not found with id: " + id);
        }
        repository.deleteById(id);
    }

    protected void updateEntity(E entity, Req request) {
        throw new UnsupportedOperationException(entityName + " update is not supported by default.");
    }
}
