package com.exemplo.iara_apimongo.services;

import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<E, ID, Req, Res> {

    protected final MongoRepository<E, ID> repository;
    private final String entityName;

    protected abstract E toEntity(Req request);
    protected abstract Res toResponse(E entity);
    protected abstract void updateEntity(E entity, Req request);

    public List<Res> findAll() {
        Instant start = Instant.now();
        log.info("[{}Service] findAll - started", entityName);

        List<Res> results = repository.findAll().stream()
                .map(this::toResponse)
                .toList();

        log.info("[{}Service] findAll - {} results in {}ms",
                entityName, results.size(), Duration.between(start, Instant.now()).toMillis());

        return results;
    }

    public Res findById(ID id) {
        Instant start = Instant.now();
        log.info("[{}Service] findById id={}", entityName, id);

        E entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + " with ID " + id + " not found."));

        log.info("[{}Service] findById id={} - found in {}ms",
                entityName, id, Duration.between(start, Instant.now()).toMillis());

        return toResponse(entity);
    }

    @Transactional
    public Res create(Req request) {
        Instant start = Instant.now();
        log.info("[{}Service] create - request={}", entityName, summarize(request));

        E entity = toEntity(request);
        Res response = toResponse(repository.save(entity));

        log.info("[{}Service] create - done in {}ms", entityName,
                Duration.between(start, Instant.now()).toMillis());
        return response;
    }

    @Transactional
    public Res update(ID id, Req request) {
        Instant start = Instant.now();
        log.info("[{}Service] update id={} - request={}", entityName, id, summarize(request));

        E entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName + " with ID " + id + " not found."));

        updateEntity(entity, request);
        Res response = toResponse(repository.save(entity));

        log.info("[{}Service] update id={} - done in {}ms", entityName, id,
                Duration.between(start, Instant.now()).toMillis());
        return response;
    }

    @Transactional
    public void delete(ID id) {
        log.info("[{}Service] delete id={}", entityName, id);
        repository.deleteById(id);
        log.info("[{}Service] delete id={} - done", entityName, id);
    }

    private String summarize(Object obj) {
        if (obj == null) return "null";
        String s = obj.toString();
        return s.length() > 200 ? s.substring(0, 200) + "..." : s;
    }
}
