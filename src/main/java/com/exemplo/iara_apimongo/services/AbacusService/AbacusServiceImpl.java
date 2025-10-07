package com.exemplo.iara_apimongo.services.AbacusService;

import com.exemplo.iara_apimongo.dto.abacus.AbacusRequestDTO;
import com.exemplo.iara_apimongo.dto.abacus.AbacusResponseDTO;
import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.mapper.AbacusMapper;
import com.exemplo.iara_apimongo.model.Abacus;
import com.exemplo.iara_apimongo.repository.AbacusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AbacusServiceImpl implements AbacusService {

    @Autowired
    private AbacusRepository repository;

    @Autowired
    private AbacusMapper mapper;

    @Override
    @Transactional
    public AbacusResponseDTO create(AbacusRequestDTO dto) {
        log.info("Criando novo ábaco: {}", dto.getName());
        Abacus abacus = mapper.toEntity(dto);
        Abacus saved = repository.save(abacus);
        log.info("Ábaco criado com ID: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    @Override
    public AbacusResponseDTO findById(String id) {
        log.debug("Buscando ábaco por ID: {}", id);
        Abacus abacus = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Abacus não encontrado com id: " + id));
        return mapper.toResponse(abacus);
    }

    @Override
    public List<AbacusResponseDTO> findAll() {
        log.debug("Listando todos os ábacos");
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AbacusResponseDTO update(String id, AbacusRequestDTO dto) {
        log.info("Atualizando ábaco ID: {}", id);
        Abacus abacus = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Abacus não encontrado com id: " + id));
        mapper.updateEntityFromDto(dto, abacus);
        Abacus updated = repository.save(abacus);
        log.info("Ábaco atualizado com sucesso ID: {}", updated.getId());
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(String id) {
        log.warn("Removendo ábaco ID: {}", id);
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Abacus não encontrado com id: " + id);
        }
        repository.deleteById(id);
        log.info("Ábaco excluído com sucesso ID: {}", id);
    }
}
