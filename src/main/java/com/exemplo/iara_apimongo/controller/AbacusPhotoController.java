package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.model.dto.request.AbacusPhotoRequest;
import com.exemplo.iara_apimongo.model.dto.response.AbacusPhotoResponse;
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
@Tag(name = "Abacus Photos", description = "Operations related to abacus photos")
public class AbacusPhotoController {

    private final AbacusPhotoService service;

    @Operation(summary = "Create a new abacus photo")
    @PostMapping
    public ResponseEntity<ApiResponse<AbacusPhotoResponse>> create(@Valid @RequestBody AbacusPhotoRequest dto) {
        AbacusPhotoResponse created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Abacus photo created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Find an abacus photo by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AbacusPhotoResponse>> findById(@PathVariable String id) {
        AbacusPhotoResponse found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo found", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "List all abacus photos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<AbacusPhotoResponse>>> findAll() {
        List<AbacusPhotoResponse> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All abacus photos retrieved", HttpStatus.OK.value(), all));
    }

    @Operation(summary = "Delete an abacus photo by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Abacus photo deleted successfully", HttpStatus.OK.value(), null));
    }
}
