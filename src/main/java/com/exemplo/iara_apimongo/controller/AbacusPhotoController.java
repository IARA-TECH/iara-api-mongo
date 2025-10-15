package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.dto.abacusPhotoDTOs.AbacusPhotoRequestDTO;
import com.exemplo.iara_apimongo.dto.abacusPhotoDTOs.AbacusPhotoResponseDTO;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.AbacusPhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/abacus-photos")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Abacus Photos", description = "Operações relacionadas a fotos de ábacos")
public class AbacusPhotoController {

    private final AbacusPhotoService service;

    @Operation(summary = "Cria uma nova foto de ábaco")
    @PostMapping
    public ResponseEntity<ApiResponse<AbacusPhotoResponseDTO>> create(@Valid @RequestBody AbacusPhotoRequestDTO dto) {
        AbacusPhotoResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Abacus photo created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Busca uma foto de ábaco por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusPhotoResponseDTO>> findById(@PathVariable String id) {
        AbacusPhotoResponseDTO found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo found", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "Lista todas as fotos de ábacos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<AbacusPhotoResponseDTO>>> findAll() {
        List<AbacusPhotoResponseDTO> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All abacus photos retrieved", HttpStatus.OK.value(), all));
    }

    @Operation(summary = "Atualiza uma foto de ábaco existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusPhotoResponseDTO>> update(@PathVariable String id,
                                                                      @Valid @RequestBody AbacusPhotoRequestDTO dto) {
        AbacusPhotoResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo updated successfully", HttpStatus.OK.value(), updated));
    }

    @Operation(summary = "Remove uma foto de ábaco por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo deleted successfully", HttpStatus.OK.value(), null));
    }
}
