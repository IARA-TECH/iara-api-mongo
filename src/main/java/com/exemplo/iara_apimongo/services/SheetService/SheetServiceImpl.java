package com.exemplo.iara_apimongo.services.SheetService;

import com.exemplo.iara_apimongo.dto.sheet.SheetRequestDTO;
import com.exemplo.iara_apimongo.dto.sheet.SheetResponseDTO;
import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.mapper.SheetMapper;
import com.exemplo.iara_apimongo.model.Sheet;
import com.exemplo.iara_apimongo.repository.SheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SheetServiceImpl implements SheetService {

    @Autowired
    private SheetRepository repository;

    @Autowired
    private SheetMapper mapper;

    @Override
    @Transactional
    public SheetResponseDTO create(SheetRequestDTO dto) {
        log.info("Criando nova folha de produção");
        Sheet sheet = mapper.toEntity(dto);
        Sheet saved = repository.save(sheet);
        log.info("Folha de produção criada com ID: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    @Override
    public SheetResponseDTO findById(String id) {
        log.debug("Buscando folha ID: {}", id);
        Sheet sheet = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sheet não encontrada com id: " + id));
        return mapper.toResponse(sheet);
    }

    @Override
    public List<SheetResponseDTO> findAll() {
        log.debug("Listando todas as folhas de produção");
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public SheetResponseDTO update(String id, SheetRequestDTO dto) {
        log.info("Atualizando folha ID: {}", id);
        Sheet sheet = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sheet não encontrada com id: " + id));
        mapper.updateEntityFromDto(dto, sheet);
        Sheet updated = repository.save(sheet);
        log.info("Folha de produção atualizada com sucesso ID: {}", updated.getId());
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(String id) {
        log.warn("Removendo folha ID: {}", id);
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Sheet não encontrada com id: " + id);
        }
        repository.deleteById(id);
        log.info("Folha de produção removida com sucesso ID: {}", id);
    }
}
