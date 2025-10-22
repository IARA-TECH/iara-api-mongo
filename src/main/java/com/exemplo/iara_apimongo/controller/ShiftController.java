package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.dto.shiftDTOs.ShiftRequestDTO;
import com.exemplo.iara_apimongo.dto.shiftDTOs.ShiftResponseDTO;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.ShiftService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/shifts")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Shifts", description = "Operations related to shifts")
public class ShiftController {

    private final ShiftService service;

    @Operation(summary = "Create a new shift")
    @PostMapping
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> create(@Valid @RequestBody ShiftRequestDTO dto) {
        ShiftResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Shift created successfully", HttpStatus.CREATED.value(), created));
    }

    @Operation(summary = "Find a shift by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> findById(@PathVariable String id) {
        ShiftResponseDTO found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Shift found", HttpStatus.OK.value(), found));
    }

    @Operation(summary = "List all shifts")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ShiftResponseDTO>>> findAll() {
        List<ShiftResponseDTO> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All shifts retrieved", HttpStatus.OK.value(), all));
    }

    @Operation(summary = "Update an existing shift")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> update(@PathVariable String id,
                                                                @Valid @RequestBody ShiftRequestDTO dto) {
        ShiftResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Shift updated successfully", HttpStatus.OK.value(), updated));
    }

    @Operation(summary = "Delete a shift by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Shift deleted successfully", HttpStatus.OK.value(), null));
    }
}
