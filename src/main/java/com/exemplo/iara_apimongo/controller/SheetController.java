package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.dto.sheetDTOs.SheetRequestDTO;
import com.exemplo.iara_apimongo.dto.sheetDTOs.SheetResponseDTO;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.SheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/sheets")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Sheets", description = "Operações relacionadas a folhas de produção")
public class SheetController {

    private final SheetService service;

    @Operation(summary = "Cria uma nova folha de produção")
    @PostMapping
    public ResponseEntity<ApiResponse<SheetResponseDTO>> create(@Valid @RequestBody SheetRequestDTO dto) {
        SheetResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Sheet created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Busca uma folha por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SheetResponseDTO>> findById(@PathVariable String id) {
        SheetResponseDTO found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Sheet found", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "Lista todas as folhas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SheetResponseDTO>>> findAll() {
        List<SheetResponseDTO> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All sheets retrieved", HttpStatus.OK.value(), all));
    }

    @Operation(summary = "Atualiza uma folha existente")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SheetResponseDTO>> update(@PathVariable String id,
                                                                @Valid @RequestBody SheetRequestDTO dto) {
        SheetResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Sheet updated successfully", HttpStatus.OK.value(), updated));
    }

    @Operation(summary = "Remove uma folha por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Sheet deleted successfully", HttpStatus.OK.value(), null));
    }
}
