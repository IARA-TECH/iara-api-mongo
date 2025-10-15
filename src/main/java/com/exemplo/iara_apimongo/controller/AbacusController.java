package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.dto.abacusDTOs.AbacusRequestDTO;
import com.exemplo.iara_apimongo.dto.abacusDTOs.AbacusResponseDTO;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.AbacusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/abacuses")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Abacuses", description = "Operações relacionadas a ábacos")
public class AbacusController {

    private final AbacusService service;

    @Operation(summary = "Cria um novo ábaco")
    @PostMapping
    public ResponseEntity<ApiResponse<AbacusResponseDTO>> create(@Valid @RequestBody AbacusRequestDTO dto) {
        AbacusResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Abacus created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Busca um ábaco por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusResponseDTO>> findById(@PathVariable String id) {
        AbacusResponseDTO found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus found", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "Lista todos os ábacos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<AbacusResponseDTO>>> findAll() {
        List<AbacusResponseDTO> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All abacuses retrieved", HttpStatus.OK.value(), all));
    }

    @Operation(summary = "Atualiza um ábaco existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusResponseDTO>> update(@PathVariable String id,
                                                                 @Valid @RequestBody AbacusRequestDTO dto) {
        AbacusResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Abacus updated successfully", HttpStatus.OK.value(), updated));
    }

    @Operation(summary = "Remove um ábaco por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus deleted successfully", HttpStatus.OK.value(), null));
    }
}
