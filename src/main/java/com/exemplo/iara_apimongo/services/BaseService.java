package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<E, ID, Req, Res> {

    protected final MongoRepository<E, ID> repository;
    private final String entityName;

    protected abstract E toEntity(Req request);
    protected abstract Res toResponse(E entity);

    public List<Res> findAll() {
        log.info("[{}Service] findAll", entityName);
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Res findById(ID id) {
        log.info("[{}Service] findById id={}", entityName, id);
        E entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + " with ID " + id + " not found."));
        return toResponse(entity);
    }

    @Transactional
    public Res create(Req request) {
        log.info("[{}Service] create", entityName);
        E entity = toEntity(request);
        return toResponse(repository.save(entity));
    }

    @Transactional
    public Res update(ID id, Req request) {
        log.info("[{}Service] update id={}", entityName, id);
        E entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + " with ID " + id + " not found."));
        try {
            updateEntity(entity, request);
        } catch (Exception e) {
            return null;
        }
        return toResponse(repository.save(entity));
    }

    @Transactional
    public void delete(ID id) {
        log.info("[{}Service] delete id={}", entityName, id);
        repository.deleteById(id);
    }

    protected void updateEntity(E entity, Req request){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
