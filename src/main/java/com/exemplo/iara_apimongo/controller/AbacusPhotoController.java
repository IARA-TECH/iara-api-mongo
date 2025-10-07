package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.dto.abacusPhoto.AbacusPhotoRequestDTO;
import com.exemplo.iara_apimongo.dto.abacusPhoto.AbacusPhotoResponseDTO;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.AbacusPhotosService.AbacusPhotoService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/abacus-photos")
@CrossOrigin("*")
public class AbacusPhotoController {

    @Autowired
    private AbacusPhotoService service;

    @PostMapping
    public ResponseEntity<ApiResponse<AbacusPhotoResponseDTO>> create(@Valid @RequestBody AbacusPhotoRequestDTO dto) {
        AbacusPhotoResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Abacus photo created successfully", HttpStatus.CREATED.value(), created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusPhotoResponseDTO>> findById(@PathVariable String id) {
        AbacusPhotoResponseDTO found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo found", HttpStatus.OK.value(), found));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AbacusPhotoResponseDTO>>> findAll() {
        List<AbacusPhotoResponseDTO> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All abacus photos retrieved", HttpStatus.OK.value(), all));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusPhotoResponseDTO>> update(@PathVariable String id,
                                                                      @Valid @RequestBody AbacusPhotoRequestDTO dto) {
        AbacusPhotoResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo updated successfully", HttpStatus.OK.value(), updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo deleted successfully", HttpStatus.OK.value(), null));
    }
}
