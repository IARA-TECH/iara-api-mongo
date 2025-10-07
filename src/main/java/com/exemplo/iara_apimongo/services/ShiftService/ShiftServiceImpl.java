package com.exemplo.iara_apimongo.services.ShiftService;

import com.exemplo.iara_apimongo.dto.shift.ShiftRequestDTO;
import com.exemplo.iara_apimongo.dto.shift.ShiftResponseDTO;
import com.exemplo.iara_apimongo.exception.ResourceNotFoundException;
import com.exemplo.iara_apimongo.model.Shift;
import com.exemplo.iara_apimongo.repository.ShiftRepository;
import com.exemplo.iara_apimongo.services.ShiftService.ShiftService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository repository;

    @Override
    @Transactional
    public ShiftResponseDTO create(ShiftRequestDTO dto) {
        log.info("Creating shift: {}", dto.getName());

        Shift shift = Shift.builder()
                .name(dto.getName())
                .startsAt(dto.getStartsAt())
                .endsAt(dto.getEndsAt())
                .createdAt(Instant.now())
                .build();

        Shift saved = repository.save(shift);
        log.info("Shift created successfully with ID {}", saved.getId());
        return mapToDTO(saved);
    }

    @Override
    public ShiftResponseDTO findById(String id) {
        log.debug("Fetching shift with ID {}", id);

        Shift shift = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + id));

        return mapToDTO(shift);
    }

    @Override
    public List<ShiftResponseDTO> findAll() {
        log.debug("Fetching all shifts");

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ShiftResponseDTO update(String id, ShiftRequestDTO dto) {
        log.info("Updating shift with ID {}", id);

        Shift shift = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + id));

        updateEntityFromDto(shift, dto);

        Shift updated = repository.save(shift);
        log.info("Shift updated successfully with ID {}", id);

        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public void delete(String id) {
        log.warn("Deleting shift with ID {}", id);

        Shift shift = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shift not found with id: " + id));

        repository.delete(shift);
        log.info("Shift deleted successfully with ID {}", id);
    }

    // 🔹 Mapeia entidade → DTO
    private ShiftResponseDTO mapToDTO(Shift shift) {
        return ShiftResponseDTO.builder()
                .id(shift.getId())
                .name(shift.getName())
                .startsAt(shift.getStartsAt())
                .endsAt(shift.getEndsAt())
                .createdAt(shift.getCreatedAt())
                .build();
    }

    // 🔹 Atualiza os campos da entidade a partir do DTO
    private void updateEntityFromDto(Shift shift, ShiftRequestDTO dto) {
        shift.setName(dto.getName());
        shift.setStartsAt(dto.getStartsAt());
        shift.setEndsAt(dto.getEndsAt());
    }
}
