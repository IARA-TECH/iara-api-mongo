package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.dto.sheet.SheetRequestDTO;
import com.exemplo.iara_apimongo.dto.sheet.SheetResponseDTO;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.SheetService.SheetService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/sheets")
@CrossOrigin("*")
public class SheetController {

    @Autowired
    private SheetService service;

    @PostMapping
    public ResponseEntity<ApiResponse<SheetResponseDTO>> create(@Valid @RequestBody SheetRequestDTO dto) {
        SheetResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Sheet created successfully", HttpStatus.CREATED.value(), created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SheetResponseDTO>> findById(@PathVariable String id) {
        SheetResponseDTO found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Sheet found", HttpStatus.OK.value(), found));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SheetResponseDTO>>> findAll() {
        List<SheetResponseDTO> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All sheets retrieved", HttpStatus.OK.value(), all));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SheetResponseDTO>> update(@PathVariable String id,
                                                                @Valid @RequestBody SheetRequestDTO dto) {
        SheetResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Sheet updated successfully", HttpStatus.OK.value(), updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Sheet deleted successfully", HttpStatus.OK.value(), null));
    }
}
