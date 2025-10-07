package com.exemplo.iara_apimongo.services.AbacusService;

import com.exemplo.iara_apimongo.dto.abacusPhoto.AbacusPhotoRequestDTO;
import com.exemplo.iara_apimongo.dto.abacusPhoto.AbacusPhotoResponseDTO;
import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.mapper.AbacusPhotoMapper;
import com.exemplo.iara_apimongo.model.AbacusPhoto;
import com.exemplo.iara_apimongo.repository.AbacusPhotoRepository;
import com.exemplo.iara_apimongo.services.AbacusPhotosService.AbacusPhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AbacusPhotoServiceImpl implements AbacusPhotoService {

    @Autowired
    private AbacusPhotoRepository repository;

    @Autowired
    private AbacusPhotoMapper mapper;

    @Override
    @Transactional
    public AbacusPhotoResponseDTO create(AbacusPhotoRequestDTO dto) {
        log.info("Criando nova foto de ábaco");
        AbacusPhoto photo = mapper.toEntity(dto);
        AbacusPhoto saved = repository.save(photo);
        log.info("Foto de ábaco criada com ID: {}", saved.getId());
        return mapper.toResponse(saved);
    }

    @Override
    public AbacusPhotoResponseDTO findById(String id) {
        log.debug("Buscando foto de ábaco ID: {}", id);
        AbacusPhoto photo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AbacusPhoto não encontrada com id: " + id));
        return mapper.toResponse(photo);
    }

    @Override
    public List<AbacusPhotoResponseDTO> findAll() {
        log.debug("Listando todas as fotos de ábacos");
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public AbacusPhotoResponseDTO update(String id, AbacusPhotoRequestDTO dto) {
        log.info("Atualizando foto de ábaco ID: {}", id);
        AbacusPhoto photo = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AbacusPhoto não encontrada com id: " + id));
        mapper.updateEntityFromDto(dto, photo);
        AbacusPhoto updated = repository.save(photo);
        log.info("Foto de ábaco atualizada com sucesso ID: {}", updated.getId());
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(String id) {
        log.warn("Removendo foto de ábaco ID: {}", id);
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("AbacusPhoto não encontrada com id: " + id);
        }
        repository.deleteById(id);
        log.info("Foto de ábaco excluída com sucesso ID: {}", id);
    }
}
