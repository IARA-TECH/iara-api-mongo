package com.exemplo.iara_apimongo.controller;

import com.exemplo.iara_apimongo.dto.shift.ShiftRequestDTO;
import com.exemplo.iara_apimongo.dto.shift.ShiftResponseDTO;
import com.exemplo.iara_apimongo.exception.ApiResponse;
import com.exemplo.iara_apimongo.services.ShiftService.ShiftService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/iara/api/shifts")
@CrossOrigin("*")
public class ShiftController {

    @Autowired
    private ShiftService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> create(@Valid @RequestBody ShiftRequestDTO dto) {
        ShiftResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.of("Shift created successfully", HttpStatus.CREATED.value(), created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> findById(@PathVariable String id) {
        ShiftResponseDTO found = service.findById(id);
        return ResponseEntity.ok(ApiResponse.of("Shift found", HttpStatus.OK.value(), found));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ShiftResponseDTO>>> findAll() {
        List<ShiftResponseDTO> all = service.findAll();
        return ResponseEntity.ok(ApiResponse.of("All shifts retrieved", HttpStatus.OK.value(), all));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ShiftResponseDTO>> update(@PathVariable String id,
                                                                @Valid @RequestBody ShiftRequestDTO dto) {
        ShiftResponseDTO updated = service.update(id, dto);
        return ResponseEntity.ok(ApiResponse.of("Shift updated successfully", HttpStatus.OK.value(), updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.of("Shift deleted successfully", HttpStatus.OK.value(), null));
    }
}
